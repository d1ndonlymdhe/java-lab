import java.util.Random;
import java.util.Scanner;
import java.util.Date;
public class Bank {
    String id;
    float balance;
    int transactionCount;
    String name;
    public Bank(){
        Random rand = new Random();
        Date date = new Date();
        this.id = String.valueOf(rand.nextInt(999999)) + String.valueOf(date.getTime());
        this.transactionCount = 0;
    }
    public void getDetails(){
        Scanner in = new Scanner(System.in);
        String line = "";
        System.out.println("Enter Name");
        line = in.nextLine();
        name = line;
        System.out.println("Enter balance");
        line = in.nextLine();
        try{
            balance = Float.parseFloat(line);
        }catch(Exception e){
            System.out.println("Invalid balance");
            balance = 0;
        }finally{
            in.close();
        }
    }
    public void withdraw(float amt){
        if(amt < balance && amt > 0){
            balance -= amt;
            transactionCount++;
        }
    }
    public void deposit(float amt){
        if(amt >0){
            balance+=amt;
            transactionCount++;
        }
    }
    public void display(){
        System.out.println("Id = " + id);
        System.out.println("Name = " + name);
        System.out.println("Balance = " + balance);
        System.out.println("Transaction Count = " + transactionCount);
    }
    public static void main(String[] args) {
        Bank myBank = new Bank();
        myBank.getDetails();
        myBank.deposit(3000);
        myBank.withdraw(5000);
        myBank.display();
    }
}
