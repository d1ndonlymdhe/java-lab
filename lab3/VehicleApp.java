import java.util.Random;

class MRandom {
    private static Random r = new Random();

    private MRandom() {
    }

    public static int getRandom(int rangeStart, int rangeEnd) {
        return r.nextInt(rangeEnd) + rangeStart;
    }
}

class Vehicle {
    protected String make;
    protected String model;
    protected int year;

    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public void start() {
        System.out.println("The vehicle is starting");
    }

    public void stop() {
        System.out.println("Stopping vehicle");
    }

    public void display() {
        System.out.println("Make = " + make + " model = " + model + " year " + year);
    }
}

class Car extends Vehicle {
    private int numberOfDoors;

    public Car(String make, String model, int year, int numberOfDoors) {
        super(make, model, year);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public void start() {
        System.out.println("Car Vehicle:");
        super.start();
    }

    @Override
    public void stop() {
        System.out.println("Car Vehicle:");
        super.stop();
    }

    public void drift() {
        int rand = MRandom.getRandom(0, 100);
        System.out.println("Your car drifted " + rand + " meters");
    }

    @Override
    public void display() {
        super.display();
        System.out.println("doors = " + numberOfDoors);
    }
}

class Motorcycle extends Vehicle {
    private boolean hasFairing;

    public Motorcycle(String make, String model, int year, boolean hasFairing) {
        super(make, model, year);
        this.hasFairing = hasFairing;
    }

    @Override
    public void start() {
        System.out.println("Motorcycle Vehicle:");
        super.start();
    }

    @Override
    public void stop() {
        System.out.println("Motocycle Vehicle:");
        super.stop();
    }

    public void wheelie() {
        int rand = MRandom.getRandom(0, 10);
        System.out.println("Your motorcycle wheelied " + rand + " meters");
    }

    @Override
    public void display() {
        super.display();
        System.out.println("fairing = " + hasFairing);
    }

}

public class VehicleApp {
    public static void main(String[] args) {
        Vehicle generic = new Vehicle("Unknown", "Unknown", 0);
        generic.start();
        generic.display();
        generic.stop();
        Car lambo = new Car("Lamborghini", "Gallardo", 2012, 2);
        lambo.start();
        lambo.drift();
        lambo.display();
        lambo.stop();
        Motorcycle unicorn = new Motorcycle("Honda", "Unicorn", 2010, false);
        unicorn.start();
        unicorn.wheelie();
        unicorn.display();
        unicorn.stop();
    }
}