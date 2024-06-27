import java.util.Objects;

/**
 * Represents a 2D coordinate with x and y values.
 */
public class Coordinate
{
    private int x;
    private int y;

    /**
     * Constructs a new Coordinate object with specified x and y values.
     *
     * @param x The x-coordinate value.
     * @param y The y-coordinate value.
     */
    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Default constructor for Coordinate.
     */
    public Coordinate()
    {
    }

    /**
     * Returns the x-coordinate value.
     *
     * @return The x-coordinate.
     */
    public int getX()
    {
        return x;
    }

    /**
     * Sets the x-coordinate value.
     *
     * @param x The new x-coordinate value.
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Returns the y-coordinate value.
     *
     * @return The y-coordinate.
     */
    public int getY()
    {
        return y;
    }

    /**
     * Sets the y-coordinate value.
     *
     * @param y The new y-coordinate value.
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * Returns a string representation of the coordinate in the format "x,y".
     *
     * @return A string representation of the coordinate.
     */
    @Override
    public String toString()
    {
        return x + "," + y;
    }

    /**
     * Checks if this Coordinate is equal to another object.
     *
     * @param compared The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object compared)
    {
        if (this == compared) return true;
        if (compared == null || getClass() != compared.getClass()) return false;
        Coordinate coordinate = (Coordinate) compared;
        return this.x == coordinate.x &&
                this.y == coordinate.y;
    }

    /**
     * Generates a hash code for the Coordinate object.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
}
