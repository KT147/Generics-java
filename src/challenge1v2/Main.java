package challenge1v2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        var nationalUSParks = new Park[]{
                new Park("Yellowstone", "44.55, 110.00"),
                new Park("Grand Canyon", "44.55, 110.00"),
                new Park("Yellowstone", "44.55, 110.00")
        };
        
        Layer<Park> parkLayer =new Layer<>(nationalUSParks);
        parkLayer.renderLayer();

        var majorUSRivers = new River[] {
                new River("Mississippi", "110, 222"),
                new River("bblabla", "110, 222"),
        };
        Layer<River> riverLayer =new Layer<>(majorUSRivers);
        riverLayer.addElements(new River("Colorado" , "222,1131"));
        riverLayer.renderLayer();

    }
}

interface Mappable {
    void render();

    static double[] stringToLatLon(String location) {
        var splits = location.split(",");
        double lat = Double.valueOf(splits[0]);
        double lng = Double.valueOf(splits[1]);
        return new double[]{lat, lng};
    }
}

abstract class Point implements Mappable {

    private double[] location = new double[2];

    public Point(String location) {
        this.location = Mappable.stringToLatLon(location);
    }

    @Override
    public void render() {
        System.out.println("Render " + this + " as POINT (" + location() + ")");
    }

    private String location() {
        return Arrays.toString(location);
    }
}

abstract class Line implements Mappable {

    private double[][] locations;

    public Line(String... locations) {
        this.locations = new double[locations.length][];
        int index = 0;
        for (var l : locations) {
            this.locations[index++] = Mappable.stringToLatLon(l);
        }
    }

    @Override
    public void render() {
        System.out.println("Render " + this + " as LINE (" + location() + ")");
    }

    private String location() {
        return Arrays.deepToString(locations);
    }
}

class Park extends Point {
    private String name;

    public Park(String name, String location) {
        super(location);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " National Park";
    }
}

class River extends Line {
    private String name;

    public River(String name, String... locations) {
        super(locations);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " River";
    }
}

class Layer<T extends Mappable> {
    private List<T> layerEelements;

    public Layer(T[] elements) {
        this.layerEelements = new ArrayList<>(List.of(elements));
    }

    public void addElements(T... elements) {
        layerEelements.addAll(List.of(elements));
    }

    public void renderLayer() {
        for (T element : layerEelements) {
            element.render();
        }
    }
}
