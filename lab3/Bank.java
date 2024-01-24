
class BankAccount{
    protected static int  accountCount = 0;
    protected int accountNumber;
    private String cusName;
    protected float balance;
    private String cusAddr;
    public BankAccount(String cusName, float balance, String cusAddr) {
        accountCount++;
        this.accountNumber = accountCount;
        this.cusName = cusName;
        this.balance = balance;
        this.cusAddr = cusAddr;
    }
    void deposit(float amt){
        if(amt<0){
            balance += amt;
            System.out.println(amt +" deposited, New Balance = "+balance);
        }else{
            System.out.println("Deposit amount cannot be negative");
        }
    }
    void withdraw(float amt){
        if(balance-amt > 0){
            balance = balance-amt;
            System.out.println(amt +" withdrawn, New Balance = "+balance);
        }
    }
    void displayBalance(){
        System.out.println("AC num = "+accountNumber);
        System.out.println("Customer name = "+cusName);
        System.out.println("Customer address = "+cusAddr);
        System.out.println("Balance = "+balance);
    }
    
}

class SavingAccount extends BankAccount{
    private float interestRate;
    public SavingAccount(String cusName, float balance, String cusAddr, float interestRate) {
        super(cusName, balance, cusAddr);
        this.interestRate = interestRate;
    }
    void addInterest(){
        float interest = balance * interestRate/100;
        balance += interest;
        System.out.println("Interest = "+interest+" New Balance = "+balance);
    }
    @Override
    void displayBalance(){
        super.displayBalance();
        System.out.println("Interest Rate = "+interestRate);
    }
}

class CheckingAccount extends BankAccount{
    private float overdraftlimit;
    public CheckingAccount(String cusName, float balance, String cusAddr, float overdraftlimit) {
        super(cusName, balance, cusAddr);
        this.overdraftlimit = overdraftlimit;
    }
    @Override
    void withdraw(float amt){
        if((balance-amt)>(0-overdraftlimit)){
            balance-=amt;
            System.out.println(amt +" withdrawn, New Balance = "+balance);
        }else{
            System.out.println("Overdraft limit exceeded");
        }
    }
    @Override
    void displayBalance(){
        super.displayBalance();
        System.out.println("Overdraft limt = "+overdraftlimit);
    }
}

public class Bank {
    private static void seperator(){
        System.out.println("-----------------");
    }
    public static void main(String[] args) {
        // Creating a Savings Account
        SavingAccount savingsAccount = new SavingAccount("Ayush Shrestha", 1000, "Mahendrapool", 2.5f);
        savingsAccount.displayBalance();
        seperator();
        savingsAccount.addInterest();
        seperator();
        savingsAccount.displayBalance();

        seperator();
        // Creating a Checking Account
        CheckingAccount checkingAccount = new CheckingAccount("Aayush Mulmi", 500, "Prithivi Chowk", 100);
        checkingAccount.displayBalance();
        seperator();

        checkingAccount.withdraw(600); // Attempting to withdraw more than the balance and overdraft limit
        seperator();
        checkingAccount.withdraw(50);  // Withdrawing within the overdraft limit
        seperator();
        checkingAccount.displayBalance();

        seperator();
        // Creating a regular Bank Account
        BankAccount bankAccount = new BankAccount("Ayush Pun", 2000, "Lamachaur");
        bankAccount.displayBalance();
        seperator();
        bankAccount.deposit(500);
        seperator();
        bankAccount.withdraw(300);
        seperator();
        bankAccount.displayBalance();
        seperator();
    }
    
}
