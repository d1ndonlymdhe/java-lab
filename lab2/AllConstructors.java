class Student {
    int roll;
    String name;

    // parameterized constructor
    Student(int roll, String name) {
        this.roll = roll;
        this.name = name;
    }
    // default constructor
    Student() {
        roll = 0;
        name = "";
    }
    // Copy constructor
    Student(Student S) {
        roll = S.roll;
        name = S.name;
    }
    public String toString() {
        return "Roll = " + this.roll + " Name = " + this.name;
    }
}

public class AllConstructors {
    public static void main(String[] args) {
        Student S1 = new Student();
        Student S2 = new Student(4,"hari");
        Student S3 = new Student(S2);
        System.out.println(S1);
        System.out.println(S2);
        System.out.println(S3);
    }
}
