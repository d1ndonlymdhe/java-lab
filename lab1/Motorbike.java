public class Motorbike {
    int speed;
    String model;

    public void accelerate() {
        this.speed += 10;
    }
    public void stop(){
        this.speed = 0;
    }
    public void printSpeed(){
        System.out.println(model + " is travelling at " + speed);
    }
    public static void main(String[] args) {
        Motorbike honda = new Motorbike();
        honda.model = "Hero";
        honda.speed = 100;
        Motorbike bajaj = new Motorbike();
        bajaj.model = "pulsar";
        bajaj.speed = 140;
        honda.accelerate();
        honda.printSpeed();
        bajaj.stop();
        bajaj.printSpeed();
    }
}
