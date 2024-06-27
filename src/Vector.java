import java.io.*;
import java.util.*;

/**
 * Represents a vector defined by its initial and terminal coordinates.
 */
public class Vector
{
    private Coordinate initialPoint;
    private Coordinate terminalPoint;

    /**
     * Constructs a new Vektor object with specified initial and terminal points.
     *
     * @param initialPoint  The initial point of the vector.
     * @param terminalPoint The terminal point of the vector.
     */
    public Vector(Coordinate initialPoint, Coordinate terminalPoint)
    {
        this.initialPoint = initialPoint;
        this.terminalPoint = terminalPoint;
    }

    /**
     * Default constructor for Vektor.
     */
    public Vector()
    {
    }

    /**
     * Entry point of the program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args)
    {
        List<Vector> vectorList = readVectorsFromFile("input.txt");

        Map<String, Set <Vector>> map = findIntersections(vectorList);

        writeToFile(constructOutput(map), "output.txt");
    }
    /**
     * Constructs the output string containing the number of intersection points and details of intersecting vectors.
     *
     * @param map The map containing intersection points and sets of intersecting vectors.
     * @return The constructed output string.
     */
    public static String constructOutput(Map<String, Set<Vector>> map)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(map.size());
        stringBuilder.append("\n\n");

        // Output the number of intersection points and details
        for (String key : map.keySet()) {
            stringBuilder.append(key);
            stringBuilder.append("\n");

            for (Vector item : map.get(key)) {
                stringBuilder.append(item.initialPoint).append(" -> ").append(item.terminalPoint).append("\n");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Reads vectors from a file if the file format is the following: x,y -> a,b
     *
     * @param fileName The name of the file to read from.
     * @return A list of vectors read from the file.
     */
    public static List<Vector> readVectorsFromFile(String fileName)
    {
        List<Vector> vectorList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName)))
        {
            while (scanner.hasNext())
            {
                String[] row = scanner.nextLine().split("->");

                String[] firstCoordinateArray = row[0].trim().split(",");
                String[] secondCoordinateArray = row[1].trim().split(",");

                int firstCoordinateX = Integer.parseInt(firstCoordinateArray[0]);
                int firstCoordinateY = Integer.parseInt(firstCoordinateArray[1]);
                Coordinate initialCoordinate = new Coordinate(firstCoordinateX, firstCoordinateY);

                int secondCoordinateX = Integer.parseInt(secondCoordinateArray[0]);
                int secondCoordinateY = Integer.parseInt(secondCoordinateArray[1]);
                Coordinate terminalCoordinate = new Coordinate(secondCoordinateX, secondCoordinateY);

                vectorList.add(new Vector(initialCoordinate, terminalCoordinate));
            }
        } catch (Exception e)
        {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return vectorList;
    }

    /**
     * Finds intersection points and stores intersecting vectors in a map.
     *
     * @param vectorList The list of vectors to process.
     * @return A map where the keys are intersection points and the values are sets of vectors that intersect at those points.
     */
    public static Map<String, Set<Vector>> findIntersections(List<Vector> vectorList)
    {
        Map<String, Set<Vector>> map = new HashMap<>();
        Vector vector = new Vector();

        for (int i = 0; i < vectorList.size(); i++)
        {
            for (int j = i + 1; j < vectorList.size(); j++)
            {
                Coordinate intersect = vector.GetIntersectionPoint(vectorList.get(i), vectorList.get(j));
                if (intersect != null)
                {
                    String key = intersect.toString();
                    map.computeIfAbsent(key, k -> new HashSet<>()).add(vectorList.get(i));
                    map.get(key).add(vectorList.get(j));
                }
            }
        }

        return map;
    }

    /**
     * Write the given content to a file.
     *
     * @param content The content to write to the file.
     * @param fileName The name of the file to write to.
     */
    public static void writeToFile(String content, String fileName)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
        {
            writer.write(content);
        } catch (IOException e)
        {
            System.err.println("Error writing to file " + fileName + ": " + e.getMessage());
        }
    }

    /**
     * Returns the initial point of the vector.
     *
     * @return The initial point.
     */
    public Coordinate getInitialPoint()
    {
        return initialPoint;
    }

    /**
     * Sets the initial point of the vector.
     *
     * @param initialPoint The new initial point.
     */
    public void setInitialPoint(Coordinate initialPoint)
    {
        this.initialPoint = initialPoint;
    }

    /**
     * Returns the terminal point of the vector.
     *
     * @return The terminal point.
     */
    public Coordinate getTerminalPoint()
    {
        return terminalPoint;
    }

    /**
     * Sets the terminal point of the vector.
     *
     * @param terminalPoint The new terminal point.
     */
    public void setTerminalPoint(Coordinate terminalPoint)
    {
        this.terminalPoint = terminalPoint;
    }

    /**
     * Returns a string representation of the vector in the format "initialPoint -> terminalPoint".
     *
     * @return A string representation of the vector.
     */
    @Override
    public String toString()
    {
        return initialPoint + " -> " + terminalPoint;
    }

    /**
     * Checks if this Vektor is equal to another object.
     *
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Objects.equals(initialPoint, vector.initialPoint) &&
                Objects.equals(terminalPoint, vector.terminalPoint);
    }

    /**
     * Generates a hash code for the Vektor object.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(initialPoint, terminalPoint);
    }

    /**
     * Finds the intersection point of two vectors, if it exists.
     * The formulas used were from: https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection
     *
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The intersection coordinate if vectors intersect, null otherwise.
     */
    public Coordinate GetIntersectionPoint(Vector vector1, Vector vector2)
    {
        int x1 = vector1.initialPoint.getX();
        int y1 = vector1.initialPoint.getY();
        int x2 = vector1.terminalPoint.getX();
        int y2 = vector1.terminalPoint.getY();
        int x3 = vector2.initialPoint.getX();
        int y3 = vector2.initialPoint.getY();
        int x4 = vector2.terminalPoint.getX();
        int y4 = vector2.terminalPoint.getY();

        // Calculate the intersection using vector mathematics
        double numerator = (x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4);
        double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (denominator == 0) return null;

        double t = numerator / denominator;

        numerator = (x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3);
        denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (denominator == 0) return null;

        double u = -numerator / denominator;

        // Check if the intersection point lies on both vectors
        if (t >= 0 && t <= 1 && u >= 0 && u <= 1)
        {
            int x = (int) Math.round(x1 + t * (x2 - x1));
            int y = (int) Math.round(y1 + t * (y2 - y1));
            return new Coordinate(x, y);
        } else {
            return null;
        }
    }
}
