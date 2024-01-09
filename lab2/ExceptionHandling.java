import java.util.Scanner;

public class ExceptionHandling {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = 0;
        int b = 0;
        String line = "";
        System.out.println("Enter value for a");
        line = in.next();
        try {
            a = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("Unhandled input " + e);
        }
        System.out.println("Enter value for b");
        line = in.next();
        try {
            b = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("Unhandled input " + e);
        }
        float c = 0;
        try {
            c = a / b;
        } catch (ArithmeticException e) {
            System.out.println("Division error " + e);
        } finally {
            System.out.println("c = " + c);
        }

        in.close();
    }
}
