package shapes;

interface resizeable {
    void resize(double factor);
}
public class ResizeRectangle extends Rectangle implements resizeable {
    int x = 0;
    public ResizeRectangle(String name, String color, float length, float width) {
        super(name, color,length,width);
        this.length = length;
        this.width = width;
    }

    @Override
    public void resize(double factor) {
        this.length *= factor;
        this.width *= factor;
    }

}