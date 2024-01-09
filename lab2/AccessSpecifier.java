import java.util.Scanner;

class Account {
    private String username;
    private String password;
    private static int accountCount = 0;
    public int id;
    Account(){
        id = accountCount;
        accountCount++;
    }
    public boolean setUsername(String username) {
        if (username.contains("BadWord")) {
            System.out.println("Do not include bad words");
            return false;
        }
        this.username = username;
        return true;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        if (password.length() < 8) {
            System.out.println("Minimum password length is 8");
            return false;
        }
        this.password = password;
        return true;
    }

}

public class AccessSpecifier {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Account newAccount = new Account();
        boolean success = false;
        String line = "";
        while (!success) {
            System.out.println("Enter username");
            line = in.next();
            success = newAccount.setUsername(line);
        }
        success = false;
        while (!success) {
            System.out.println("Enter password");
            line = in.next();
            success = newAccount.setPassword(line);
        }
        System.out.println("id = " + newAccount.id + " username = " + newAccount.getUsername() + " password = " + newAccount.getPassword());
        in.close();
    }
}
