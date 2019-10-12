import java.awt.*;
import java.awt.geom.AffineTransform;

public class Student extends Entity {
    public int grade;

    public Student(String name, Vector2D position, State state, Common common) {
        super(name, position, state, common);
    }

    @Override
    public void draw ( Graphics2D g2d ) //copied instructor's code and a little bit modified
    {
        Font font = g2d.getFont();  // Save the default font of the g2d object
        FontMetrics fm = g2d.getFontMetrics();  // Get a font metrics object that will help us calculate the width of strings in pixels
        AffineTransform tOriginal = g2d.getTransform();  // Save the default affine transformation of the g2d object (default coordinate axes)

        g2d.setFont(new Font("Sans Serif" ,Font.PLAIN, 14));  // Change the font of the g2d object to some nice, bold, larger font

        g2d.translate( (int) position.x , (int) position.y ) ;  // Modify (translate) the default coordinate axes, origin is now at the center of the Student object

        // Set drawing color
        if   (!(grade < 100)) g2d.setPaint(Color.MAGENTA);
        else g2d.setPaint(Color.CYAN);

        g2d.fillOval(-15, -15, 30, 30);  // Draw the filled oval (body of student)

        g2d.setPaint(Color.BLACK);
        g2d.drawOval(-15, -15, 30, 30);  // Draw the black contour around the filled oval

        // Just some string reference variable
        String str = name;
        g2d.setPaint(Color.BLACK);
        g2d.drawString(str, (int) (-fm.stringWidth(str) / 2) + 1,-18);

        str = grade + "";
        g2d.setPaint(Color.BLACK);
        g2d.drawString(str, (int) (-fm.stringWidth(str) / 2) + 1, 6);

        str = state.toString();
        g2d.setPaint(Color.BLACK);
        g2d.drawString(str, (int) (-fm.stringWidth(str) / 2) + 1, 30);

        g2d.setTransform(tOriginal);  // Restore the saved affine transformation (restore default coordinate axes)
        g2d.setFont(font);  // Restore the default font
    }
}
