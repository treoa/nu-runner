public class Invisible extends State {

    public Invisible() {
        super(false, false);
    }

    @Override
    public void step(Entity e) {
        return;
    }
}
