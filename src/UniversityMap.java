import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

class UniversityMap extends Entity {

    BufferedImage image;

    public UniversityMap(String name, Vector2D position, State state, Common common, String path) throws IOException {
        super(name, position, state, common);
        this.image = ImageIO.read(new File(path));
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, 0, 0, 800, 400, null);
    }
}
