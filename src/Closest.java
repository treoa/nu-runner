import java.util.Iterator;

public class Closest extends State {
    public int speed;
    public int duration;

    public Closest() {
        super(false, true);
    }

    @Override
    public void step(Entity e) {
        Assessment closest = null;
        double val = Double.MAX_VALUE;
        double temp_val;
        Iterator it = e.common.assessments.iterator();

        while (it.hasNext()){
            Assessment temp_ass = (Assessment) it.next();
            if ((temp_val = e.common.v2d.distanceTo(temp_ass.position)) < val){
                closest = temp_ass;
                val = temp_val;
            }
        }

        if (closest != null){
            if (val < (double) this.speed){
                e.common.v2d.set(closest.position);
            } else {
                e.common.v2d.change(closest.position.minus(e.position).normalize().scale(this.speed));
            }

            if (--this.duration <= 0){
                this.isOver = true;
            }
        }
    }
}
