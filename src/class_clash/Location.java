package class_clash;

public class Location {

    // X and Y variables for a specific location
    private int x;
    private int y;

    // Constructor
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Update a location
    public void update(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** Return the X-coord of the location */
    public int getX() {
        return x;
    }

    /** Return the Y-coord of the location */
    public int getY() {
        return y;
    }
}
