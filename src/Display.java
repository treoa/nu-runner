import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;

class Display extends JPanel {
    public Common common;

    public Display(Common common) throws IOException {
        this.common = common;
        setBackground(Color.GRAY);
    }

    @Override
    public final Dimension getPreferredSize() {
        return new Dimension(800, 400);
    }

    @Override
    public final void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        common.map.draw(g2d);
        common.drawAllEntities(g2d);
    }
}
