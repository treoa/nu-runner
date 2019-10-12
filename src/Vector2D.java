import static java.lang.Math.*;

class Vector2D {
    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D v) {
        this.y = v.y;
        this.x = v.x;
    }

    public void change (Vector2D other){
        this.x += other.x;
        this.y += other.y;
    }

    public double distanceTo(Vector2D other) {
        return sqrt(pow((this.x - other.x), 2) + pow((this.y - other.y), 2));
    }

    public Vector2D normalize() {
        int divider = (int) Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
        return new Vector2D(this.x/divider, this.y / divider);
    }

    public Vector2D plus(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D minus(Vector2D other) {
        this.x -= other.x;
        this.y -= other.y;
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D scale (int factor) {
        return new Vector2D (this.x * factor, this.y  * factor);
    }
}
