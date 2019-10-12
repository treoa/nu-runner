import java.awt.*;
import java.io.IOException;

abstract class Entity {
    public String name = "";
    public Vector2D position = null;
    public State state = null;
    public Common common = null;

    public Entity(String name, Vector2D position, State state, Common common) {
        this.name = name;
        this.position = position;
        this.state = state;
        this.common = common;
    }


    public abstract void draw(Graphics2D g2d); //unspecified type

    public void step() {
        this.state.step(this);
    }
}
