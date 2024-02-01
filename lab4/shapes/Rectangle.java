package shapes;

public class Rectangle extends Shape {
    float length;
    float width;

    public Rectangle(String name, String color, float length, float width) {
        super(name, color);
        this.length = length;
        this.width = width;
    }

    @Override
    public float area() {
        return length * width;
    }


    public float getLength() {
        return length;
    }
    public float getWidth() {
        return width;
    }

}
