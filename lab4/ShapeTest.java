import shapes.Circle;
import shapes.ResizeRectangle;

public class ShapeTest {
    public static void main(String[] args) {
        Circle circle = new Circle("Circle", "Red", 5.0f);
        ResizeRectangle rectangle = new ResizeRectangle("Rectangle", "Blue", 4.0f, 6.0f);
        // Display information
        circle.displayShapeInfo();
        System.out.println("--------------------");
        rectangle.displayShapeInfo();
        // Resize rectangle and display updated information
        rectangle.resize(1.5);
        System.out.println("--------------------");
        rectangle.displayShapeInfo();

    }
}
