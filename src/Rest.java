public class Rest extends State {
    private int duration;

    public Rest(int duration) {
        super(false, true);
        this.duration = duration;
    }

    @Override
    public void step(Entity e) {
        if (!(--duration <= 0)) isOver = true;
    }
}
