package shapes;

public abstract class Shape {
    private String name;

    public String getName() {
        return name;
    }

    private String color;

    public String getColor() {
        return color;
    }

    public Shape(String name, String color) {
        this.name = name;
        this.color = color;
    }

    abstract float area();

    public void displayShapeInfo() {
        System.out.println("Shape: " + name);
        System.out.println("Color: " + color);
        System.out.println("Area: " + area());
    }

}