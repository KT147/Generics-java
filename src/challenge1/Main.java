package challenge1;


import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Layer layer = new Layer(new ArrayList());
        Park park = new Park(40.102_1, -75.4231, "Grand Canyon");
        River river = new River(40.1021, -75.4231, "Missisippi River");
        layer.addElements(park);
        layer.addElements(river);
        layer.renderLayer();

    }
}

interface Mappable {
    void render();
    double getX();
    double getY();
    String getName();
}

class Point implements Mappable {

    @Override
    public void render() {

    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public String getName() {
        return "";
    }
}

class Line implements Mappable {

    @Override
    public void render() {

    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public String getName() {
        return "";
    }
}

class Park extends Point {
    private double x;
    private double y;
    private String name;

    public Park(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public String getName() {
        return name;
    }

}

class River extends Line {
    private double x;
    private double y;
    private String name;

    public River(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public String getName() {
        return name;
    }


}

class Layer<T extends Mappable> {
    private List<T> elements;

    public Layer(List<T> elements) {
        this.elements = elements;
    }

    public void addElements(T element) {
        elements.add(element);
    }

    public void renderLayer() {
        for (var e : elements) {
            e.render();
            System.out.println("Render " + e.getName() + " as "
                    + (e.getClass().getSimpleName().equals("Park") ? "POINT" : "LINE")
                    + " (" + e.getX() + ", " + e.getY() + ")");
        }
    }
}
