public class ZigZag extends State {
    public int speed;
    public int duration;
    public Vector2D direction;

    public ZigZag(int duration, int speed, Vector2D dir) {
        super(false, true);
        this.speed = speed;
        this.duration = duration;
        this.direction = dir.normalize();
    }
//slozhna(
    @Override
    public void step(Entity e) {
        e.position.change(this.direction.scale(speed));

        if (e.position.x <= 0.0 || e.position.x >= e.common.windowWidth){
            direction.x *= -1;
        }
        if (e.position.y <= 0.0 || e.position.y >= e.common.windowHeight){
            direction.y *= -1;
        }
        if (--this.duration <= 0){
            isOver = true;
        }
    }
}
