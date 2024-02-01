abstract class BankAccount {
    protected String accountNumber;
    protected double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }

    abstract void performAccountMaintenance();
}

interface Transaction {
    double deposit(double amount);

    double withdraw(double amount);
}

class SavingsAccount extends BankAccount implements Transaction {
    double interestRate;

    public SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    @Override
    public double deposit(double amount) {
        balance += amount;
        return balance;
    }

    @Override
    public double withdraw(double amount) {
        if(balance >= amount) {
            balance -= amount;
        }
        return balance;
    }

    @Override
    void performAccountMaintenance() {
        System.out.println("Performing Savings Account Maintenance");
    }

    void applyInterest() {
        balance += balance * interestRate;
    }

}




public class BankingSystem {
    public static void main(String[] args) {
        // Create a SavingsAccount object
        SavingsAccount savingsAccount = new SavingsAccount("123456",1000.0,0.05);

        // Display initial account information
        System.out.println("Initial Account Information:");
        savingsAccount.displayAccountInfo();
        printSeparator();

        // Deposit some amount
        double depositedAmount = 500.0;
        System.out.println("Depositing $" + depositedAmount);
        savingsAccount.deposit(depositedAmount);
        savingsAccount.displayAccountInfo();
        printSeparator();

        // Withdraw some amount
        double withdrawnAmount = 200.0;
        System.out.println("Withdrawing $" + withdrawnAmount);
        savingsAccount.withdraw(withdrawnAmount);
        savingsAccount.displayAccountInfo();
        printSeparator();

        // Perform account maintenance
        System.out.println("Performing Account Maintenance:");
        savingsAccount.performAccountMaintenance();
        printSeparator();

        // Apply interest
        System.out.println("Applying Interest:");
        savingsAccount.applyInterest();
        savingsAccount.displayAccountInfo();
    }

    private static void printSeparator() {
        System.out.println("-------------------------------");
    }
}