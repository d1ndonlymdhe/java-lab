import shapes.ResizeRectangle;

public class InterfaceProgram {
    public static void main(String[] args) {
        ResizeRectangle rectangle = new ResizeRectangle("RedRect", "Red", 100f, 20f);
        System.out.println("Before resize :");
        System.out.println("Length = " + rectangle.getLength() + " Width " + rectangle.getWidth());
        System.out.println("After resize :");
        rectangle.resize(2);
        System.out.println("Length = " + rectangle.getLength() + " Width " + rectangle.getWidth());
    }
}