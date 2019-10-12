public class GotoXY extends State {
    private int some_int;
    private Vector2D direction;

    public GotoXY(Vector2D v2d, int integer) {
        super(false, true);
        this.direction = v2d;
        this.some_int = integer;
    }

    @Override
    public void step(Entity e) {
        if (e.position.distanceTo(direction) < (double)some_int){
            e.position.set(direction);
        } else {
            e.position.change(direction.minus(e.position).normalize().scale(some_int));
        }
        if (e.position.distanceTo(direction) < 1.0){
            isOver = true;
        }
    }
}
