import java.io.File;
import java.io.IOException;
import java.util.*;

public class Vektor
{
    private Coordinate initialPoint;
    private Coordinate terminalPoint;

    public Vektor(Coordinate initialPoint, Coordinate terminalPoint)
    {
        this.initialPoint = initialPoint;
        this.terminalPoint = terminalPoint;
    }

    public Vektor()
    {
    }


    public static void main(String[] args)
    {
        List<Vektor> vektorList = new ArrayList<>();
        Vektor vektor = new Vektor();
        Coordinate initalCoordinate = new Coordinate();
        Coordinate terminalCoordinate = new Coordinate();

        try
        {
            Scanner scanner = new Scanner(new File("F:\\Projektek\\IB_Controll_vektorok\\src\\input.txt"));
            while (scanner.hasNext())
            {
                String[] row = scanner.nextLine().split("->");

                String[] firstCoordinateArray = row[0].trim().split(",");
                String[] secondCoordinateArray = row[1].trim().split(",");

                int firstCoordinateX = Integer.parseInt(firstCoordinateArray[0]);
                int firstCoordinateY = Integer.parseInt(firstCoordinateArray[1]);
                initalCoordinate = new Coordinate(firstCoordinateX, firstCoordinateY);

                int secondCoordinateX = Integer.parseInt(secondCoordinateArray[0]);
                int secondCoordinateY = Integer.parseInt(secondCoordinateArray[1]);
                terminalCoordinate = new Coordinate(secondCoordinateX, secondCoordinateY);

                vektorList.add(new Vektor(initalCoordinate, terminalCoordinate));
            }
        } catch (Exception e)
        {
            System.err.println("Hiba történt: " + e.getMessage());
        }





        Set<Coordinate> set = new HashSet<>();
        for (int i = 0; i < vektorList.size(); i++)
        {
            for (int j = i + 1; j < vektorList.size(); j++)
            {
                Coordinate intersect = vektor.intersect(vektorList.get(i), vektorList.get(j));
                if (intersect != null )
                {
                    set.add(intersect);
                }
            }
        }

        System.out.println(set.size());
    }
    public Coordinate intersect(Vektor vektor1, Vektor vektor2)
    {
        int x1 = vektor1.initialPoint.getX();
        int y1 = vektor1.initialPoint.getY();
        int x2 = vektor1.terminalPoint.getX();
        int y2 = vektor1.terminalPoint.getY();
        int x3 = vektor2.initialPoint.getX();
        int y3 = vektor2.initialPoint.getY();
        int x4 = vektor2.terminalPoint.getX();
        int y4 = vektor2.terminalPoint.getY();




        double denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);


        if (denom == 0) return null;

        double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denom;
        double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denom;


        if (ua >= 0 && ua <= 1 && ub >= 0 && ub <= 1)
        {
            double x = x1 + ua * (x2 - x1);
            double y = y1 + ua * (y2 - y1);
            return new Coordinate((int) x, (int) y);
        }

        return null;
    }


    @Override
    public String toString()
    {
        return "Vektor{" +
                "initialPoint=" + initialPoint +
                ", terminalPoint=" + terminalPoint +
                '}';
    }
}