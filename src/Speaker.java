import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Speaker extends Entity {
    private BufferedImage image;

    public Speaker(String name, Vector2D position, State state, Common common, String s) throws IOException {
        super(name, position, state, common);
        image = ImageIO.read(new File(s));
    }

    @Override
    public void draw(Graphics2D g2d) { //copied instructor's code (analogy of Students class w/o drawing shapes) and a little bit modified
        Font font = g2d.getFont();
        g2d.setFont(new Font("Sans Serif", Font.BOLD, 14));
        FontMetrics fontMetrics = g2d.getFontMetrics();
        AffineTransform transform = g2d.getTransform();
        g2d.translate((int)this.position.x, (int)this.position.y);
        g2d.drawImage(this.image, -this.image.getWidth() / 8, -this.image.getHeight() / 8, this.image.getWidth() / 4, this.image.getHeight() / 4, null);
        String a = this.name;
        g2d.setPaint(Color.BLACK);
        g2d.drawString(a, (int)(-fontMetrics.stringWidth(a) / 2.0) + 1, -this.image.getHeight() / 8 - 2);
        g2d.setTransform(transform);
        g2d.setFont(font);
    }

}
