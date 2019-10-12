import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Academician extends Entity {

    private BufferedImage image;
    int im_width;
    int im_height;

    public Academician(String name, Vector2D position, State state, Common common, String path) throws IOException {
        super(name, position, state, common);
        this.image = ImageIO.read(new File(path));
        im_width = image.getWidth() / 4;
        im_height = image.getHeight() / 4;
    }

    public Assessment createAssessment() {
        return null;
    }

    @Override
    public void draw(Graphics2D g2d) { //copied instructor's code (analogy of Students class w/o drawing shapes) and a little bit modified
        Font font = g2d.getFont();
        g2d.setFont(new Font("Sans Serif", 1, 14));
        FontMetrics fontMetrics = g2d.getFontMetrics();
        AffineTransform transform = g2d.getTransform();
        g2d.translate((int)this.position.x, (int)this.position.y);
        g2d.drawImage(image, this.image.getWidth() / 8, this.image.getHeight() / 8, this.image.getWidth() / 4, this.image.getHeight() / 4, null);
        String name = this.name;
        g2d.setPaint(Color.BLACK);
        g2d.drawString(name, (int)(-fontMetrics.stringWidth(name) / 2.0D) + 1, 6);
        String string = state.toString();
        g2d.setPaint(Color.BLACK);
        g2d.drawString(string, (int)(-fontMetrics.stringWidth(string) / 2.0D) + 1, 30);
        g2d.setTransform(transform);
        g2d.setFont(font);
    }
}
