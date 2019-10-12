import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

class Common {
    public Vector2D v2d;
    public int windowHeight;
    public int windowWidth;
    public UniversityMap map;
    public boolean implemented = false;
    public State state;
    public ArrayList<Academician> academicians;
    public ArrayList<Speaker> speakers;
    public ArrayList<Student> students;
    public ArrayList<Assessment> assessments;
    public ArrayList<AssessmentFactory> assessmentFactories;
    private Random random;

    private String [] csci235StudentNames =
            {
                    "Abay" , "Abilda" , "Abilkhaiyr" , "Ablan" , "Abylaikhan" , "Adil" , "Adilzhan" , "Adlet" , "Aidana" , "Aidyn" , "Aigerim" , "Aisana" ,
                    "Akhmed" , "Akmyrza" , "Alan" , "Aldamzhar" , "Alexandra" , "Ali" , "Alibek" , "Alim" , "Alisher" , "Allan" , "Altair" , "Altynay" ,
                    "Altynbek" , "Amangeldy" , "Amina" , "Anel" , "Angsar" , "Anuar" , "Ardan" , "Arman" , "Askhat" , "Assanali" , "Assem" , "Ayazhan" ,
                    "Azamat" , "Azizkhan" , "Bagdat" , "Baglan" , "Bakdaulet" , "Bakdauren" , "Bakyt" , "Batyrbek" , "Batyrkhan" , "Bauyrzhan" , "Beibarys" ,
                    "Bekzat" , "Bota" , "Damir" , "Dana" , "Danel" , "Daniyar" , "Darina" , "Dastan" , "Daulet" , "Dauren" , "Dnislam" , "Dulat" , "Eldar" ,
                    "Emir" , "Galym" , "Gulnaz" , "Islam" , "Kamila" , "Kamilla" , "Karim" , "Kassym" , "Khadisha" , "Khafiz" , "Kuanysh" , "Kyran" , "Madi" ,
                    "Madiyar" , "Magzhan" , "Makhambet" , "Mansur" , "Margulan" , "Maxim" , "Medet" , "Meirzhan" , "Miras" , "Mokhira" , "Murat" , "Nargiza" ,
                    "Nartay" , "Nuradil" , "Nurbolat" , "Nurdaulet" , "Nurlan" , "Nursultan" , "Nurtileu" , "Olzhas" , "Rabbani" , "Raiymbek" , "Rakhat" ,
                    "Ramazan" , "Ramilya" , "Rauan" , "Rollan" , "Rustem" , "Sabyr" , "Sagi" , "Saidgaffor" , "Saken" , "Salavat" , "Sandugash" , "Sanzhar" ,
                    "Shapagat" , "Sherkhan" , "Shynggys" , "Shyngys" , "Tatyana" , "Temirlan" , "Temirzhan" , "Timur" , "Togzhan" , "Tomiris" , "Turgankhan" ,
                    "Vladislav" , "Yeldos" , "Yerkali" , "Yerkhan" , "Yermek" , "Yernar" , "Yerzhan" , "Yussup" , "Zarina" , "Zhalgas" , "Zhanarys" , "Zhandos" ,
                    "Zhangeldi" , "Zhannur" , "Zhansaya" , "Zhassulan" , "Zhibek" , "Zhuldyz"
            } ;

    public Common() throws IOException {
        this.windowWidth = 800;
        this.windowHeight = 400;
        random = new Random();

        this.map = new UniversityMap("NU Campus", new Vector2D(0.0, 0.0), new Stationary(), this, "./Assets/NUMap-Faded.jpg");

        academicians = new ArrayList<>();
        speakers = new ArrayList<>();
        students = new ArrayList<>();
        assessments = new ArrayList<>();
        assessmentFactories = new ArrayList<>();

        assessmentFactories.add(new LabFactory(this));
        assessmentFactories.add(new QuizFactory(this));
        assessmentFactories.add(new HomeworkFactory(this));

        Academician president = new Academician("Shigeo", MaprandomPoint(40), new GotoXY(this.MaprandomPoint(1), randomInt(2, 4)), this, "./Assets/ShigeoKatsu.gif");
        Academician someone = new Academician("Vasiliyi", MaprandomPoint(40), new ZigZag(randomInt(40, 80), randomInt(2, 4), new Vector2D(randomInt(-20, 20), randomInt(-20, 20))), this, "./Assets/VassiliosTourassis.gif");
        Academician prof = new Academician("Nivelle", MaprandomPoint(40), new GotoXY(MaprandomPoint(1), randomInt(2, 5)), this, "./Assets/HansDeNivelle.gif");
        Academician ass_prof = new Academician("Temizer", MaprandomPoint(40), new ZigZag(randomInt(40, 80), randomInt(2, 4), new Vector2D(randomInt(-20, 20), randomInt(-20, 20))), this, "./Assets/SelimTemizer.gif");
        academicians.add(president);
        academicians.add(someone);
        academicians.add(prof);
        academicians.add(ass_prof); //ass_prof stands for Assistant Prof
        speakers.add(new Speaker("Tokayev", MaprandomPoint(2), state, this, "./Assets/KassymJomartTokayev.gif"));
        speakers.add(new Speaker("Nazarbayev", MaprandomPoint(2), state, this, "./Assets/NursultanNazarbayev.gif"));

        for (int i = 0; i < 16; i++){
            students.add(new Student(csci235StudentNames[randomInt(1, csci235StudentNames.length - 1)], MaprandomPoint(10), new GotoXY(this.MaprandomPoint(3), randomInt(3, 4)), this));
        }
    }

    private Vector2D MaprandomPoint(int some_int) {
        return new Vector2D(randomInt(some_int, windowWidth - some_int), randomInt(some_int, windowHeight - some_int));
    }

    public int randomInt(int from, int to) {
        return this.random.nextInt(to - from + 1) + from;
    }

    public void stepAllEntities() {
        for (Academician academician : academicians) {
            academician.step();

            if (randomInt(1, 8) == 1)
                if (!(academician.state instanceof Rest) && !(academician.state instanceof Stationary)) {
                    AssessmentFactory assessmentFactory = assessmentFactories.get(randomInt(0, assessmentFactories.size() - 1));

                    int ass_x;
                    if (randomInt(0, 1) != 0) ass_x = (int) (academician.position.x + 1 * randomInt(8, 16));
                    else ass_x = (int) (academician.position.x + -1 * randomInt(8, 16));

                    int ass_y;
                    if (randomInt(0, 1) != 0) ass_y = (int) academician.position.y + 1 * randomInt(8, 16);
                    else ass_y = (int) academician.position.y + -1 * randomInt(8, 16);

                    if (!(ass_x > 0)) {
                        ass_x = 1;
                    }

                    if (!(windowWidth > ass_x)) {
                        ass_x = windowWidth - 1;
                    }

                    if (ass_y <= 0) {
                        ass_y = 1;
                    }

                    if (windowHeight <= ass_y) {
                        ass_y = windowHeight - 1;
                    }

                    assessments.add(assessmentFactory.createAssessment(new Vector2D(ass_x, ass_y)));
                }

            if (academician.state.isOver) {
                academician.state = new GotoXY(this.MaprandomPoint(3), randomInt(3, 4));
            }
        }

        for (Student student : students) {
            student.step();

            if (student.grade < 100) {
                IntStream.iterate(assessments.size() - 1, i -> i >= 0, i -> i - 1).forEach(i -> {
                    Assessment assessment = assessments.get(i);
                    if (student.position.distanceTo(assessment.position) <= 10.0) {
                        student.grade += assessment.points;
                        this.assessments.remove(i);
                    }
                });
            }

            if (student.state.isOver) {
                if (student.grade < 100)
                    student.state = new ZigZag(randomInt(30, 50), randomInt(2, 5), new Vector2D(randomInt(-20, 20), randomInt(-20, 20)));
                else if (student.position.distanceTo(v2d) <= 25.0) student.state = this.state;
                else
                    student.state = new GotoXY(v2d.plus(new Vector2D(randomInt(-10, 10), randomInt(-10, 10))), randomInt(2, 5));
            }
        }

        boolean stud_implemented = true;

        for (Student student : students) {
            if (student.grade < 100) {
                stud_implemented = false;
                break;
            }
        }

        if (!implemented && stud_implemented) {
            IntStream.range(0, academicians.size()).forEach(i -> {
                Academician academician = academicians.get(i);
                academician.position = v2d.plus(new Vector2D(-160 + i * 80, -80.0D));
                academician.state = state;
            });
        }
        implemented = stud_implemented;
    }

    public void drawAllEntities(Graphics2D g2d) {
        if (implemented) {
            for (Speaker speaker : speakers) {
                speaker.draw(g2d);
            }

            Font font = g2d.getFont();
            g2d.setFont(new Font("Sans Serif", Font.BOLD, 14));
            FontMetrics fontMetrics = g2d.getFontMetrics();
            AffineTransform affineTransform = g2d.getTransform();
            String gradCeremony = "NUGrad";
            g2d.translate((int) v2d.x, (int) v2d.y + 70);
            g2d.setPaint(Color.BLACK);
            g2d.drawString(gradCeremony, (int) ((double) (-fontMetrics.stringWidth(gradCeremony)) / 2.0D), 0);
            g2d.setTransform(affineTransform);
            g2d.setFont(font);
        } else {
            for (Assessment assessment : assessments) {
                assessment.draw(g2d);
            }
        }

        for (Student student : students){
            student.draw(g2d);
        }
        for (Academician academician : academicians){
            academician.draw(g2d);
        }

    }
}
