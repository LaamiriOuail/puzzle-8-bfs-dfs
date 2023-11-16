package puzzle8;

/**
 * The Position class represents a 2D position with x and y coordinates.
 */
public class Position {
    private int x;
    private int y;

    /**
     * Constructs a new Position object with the specified x and y coordinates.
     *
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the x and y coordinates of the position.
     *
     * @param x The new x-coordinate.
     * @param y The new y-coordinate.
     */
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate of the position.
     *
     * @return The x-coordinate of the position.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the position.
     *
     * @param x The new x-coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the position.
     *
     * @return The y-coordinate of the position.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the position.
     *
     * @param y The new y-coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Checks if the position is within the valid range (0 to 2 for both x and y).
     *
     * @return True if the position is valid, false otherwise.
     */
    public boolean isValid() {
        return this.x >= 0 && this.y >= 0 && this.x <= 2 && this.y <= 2;
    }

    /**
     * Returns a string representation of the Position object.
     *
     * @return A string representation of the Position object.
     */
    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Checks if this Position is equal to another object.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }
}
