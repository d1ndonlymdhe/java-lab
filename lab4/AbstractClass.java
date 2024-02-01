import shapes.Circle;
import shapes.Rectangle;

public class AbstractClass {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle("RedRect", "Red", 100.23f, 23.45f);
        Circle circle = new Circle("BlueCir", "Blue", 12.4f);
        System.out.println("Area of " + rectangle.getName() + " with color " + rectangle.getColor() + ": " + rectangle.area());
        System.out.println("Area of " + circle.getName() + " with color " + rectangle.getColor() + ": " + circle.area());

    }
}