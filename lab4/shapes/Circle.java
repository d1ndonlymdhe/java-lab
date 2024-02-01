package shapes;

public class Circle extends Shape {
    private float radius;

    public Circle(String name, String color, float radius) {
        super(name, color);
        this.radius = radius;
    }

    @Override
    public float area() {
        return (float) Math.PI * radius * radius;
    }
    
}
