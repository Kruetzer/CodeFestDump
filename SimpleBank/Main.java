import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

abstract class Transaction 
{
    static double balance = 0;

    public void deposit() 
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter Deposit Amount: ");
        double depositAmount = scan.nextDouble();

        balance += depositAmount;
        System.out.println("Deposit Successful" + balance);
    }

    public void checkBalance() 
    {
        System.out.println("Your Balance is: " + balance);
    }

    public void withdraw() 
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter Amount to Withdraw: ");
        double withdrawAmount = scan.nextDouble();

        if (withdrawAmount > balance) 
        {
            System.out.println("Insufficient Balance.");
        } 
        else 
        {
            balance -= withdrawAmount;
            System.out.println("P" + withdrawAmount + "Withdrawal Successful");
        }
        scan.close();
    }
}

class AccountManager extends Transaction
{

    static class Account 
    {
        private int password;
        private double balance;

        public Account(int password, double balance) 
        {
            this.password = password;
            this.balance = balance;
        }

        public int getPassword() 
        {
            return password;
        }

        public double getBalance() 
        {
            return balance;
        }

        public String toString() 
        {
            return "Password: " + password + ", Balance: " + balance;
        }
    }

    public void CreateAccount() 
    {
        Map<String, Account> userAccount = new HashMap<>();
        Scanner input = new Scanner(System.in);

        int randomNumber = (int) (Math.random() * (99999 - 10000 + 1)) + 10000;
        String userID = String.valueOf(randomNumber);

        System.out.println("1. Log in");
        System.out.println("2. Sign up");
        System.out.println("3. Cancel");
        int choice = input.nextInt();

        input.nextLine();
        switch (choice) 
        {
            case 1:
                System.out.println("You are Logging in!");
                System.out.println("Enter your username:");
                String userNameID = input.nextLine();

                if (!userAccount.containsKey(userNameID)) 
                {
                    System.out.println("User ID not found!");
                    return;
                }
                
                Account accountToLogin =userAccount.get(userNameID);

                System.out.println("Enter your password: ");
                int loginPass = input.nextInt();

                if (accountToLogin.getPassword() == loginPass) 
                {
                    System.out.println("Login successful!");
                    System.out.println("Your current balance is: " + accountToLogin.getBalance());
                } 
                else 
                {
                    System.out.println("Incorrect password.");
                }
                break;
            case 2:
                System.out.println("You are signing up!");
                System.out.println("Enter your name to generate ID number: ");
                String userName = input.nextLine();

                System.out.println("Greetings! " + userName + ", your account number is: " + userID);

                System.out.println("Enter your password (Numeric): ");
                int passWord = input.nextInt();

                System.out.println("Enter your initial balance: ");
                double initialBalance = input.nextDouble();

                Account account = new Account(passWord, initialBalance);
                userAccount.put(userName, account);

                System.out.println("Account successfully created!");
                break;
            case 3:
                System.out.println("You chose to cancel.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
        input.close();
    }
}

public class Main 
{
    public static void main(String[] args) 
    {
        while (true) 
        {
            Scanner input = new Scanner(System.in);
            AccountManager user = new AccountManager();

            System.out.printf("%nWELCOME TO RUBEN BANK%n"
                    + "1. Account%n"
                    + "2. Withdraw%n"
                    + "3. Deposit%n"
                    + "4. Check Balance%n"
                    + "5. Exit%n"
                    + "Enter: ");
            int selection = input.nextInt();

            switch (selection) 
            {
                case 1:
                    user.CreateAccount();
                    break;
                case 2:
                    user.withdraw();
                    break;
                case 3:
                    user.deposit();
                    break;
                case 4:
                    user.checkBalance();
                    break;
            }
            input.close();
        }
    }
}