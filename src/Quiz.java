import java.awt.*;
import java.awt.geom.AffineTransform;

class Quiz extends Assessment {


    public Quiz(String name, Vector2D position, State state, Common common, int points) {
        super(name, position, state, common, points);
    }

    @Override
    public void draw(Graphics2D g2d) {
        Font font = g2d.getFont();
        g2d.setFont(new Font("Sans Serif", 1, 14));
        FontMetrics fontMetrics = g2d.getFontMetrics();
        AffineTransform affineTransform = g2d.getTransform();

        g2d.translate((int) position.x, (int) position.y);
        g2d.setPaint(new Color (150, 150, 250));
        g2d.fillOval(-9, -9, 18, 18);
        String temp = "" + this.points;
        g2d.setPaint(Color.BLACK);
        g2d.drawString(temp, (int) ((double) (-fontMetrics.stringWidth(temp)) / 2.0D) + 1, 6);
        g2d.setTransform(affineTransform);
        g2d.setFont(font);
    }
}
