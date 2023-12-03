import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount account) {
        this.userAccount = account;
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void performTransaction(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                // Withdraw
                System.out.print("Enter the amount to withdraw: $");
                double withdrawAmount = scanner.nextDouble();
                if (userAccount.withdraw(withdrawAmount)) {
                    System.out.println("Withdrawal successful. Remaining balance: $" + userAccount.getBalance());
                } else {
                    System.out.println("Withdrawal failed. Insufficient funds.");
                }
                break;
            case 2:
                // Deposit
                System.out.print("Enter the amount to deposit: $");
                double depositAmount = scanner.nextDouble();
                userAccount.deposit(depositAmount);
                System.out.println("Deposit successful. Updated balance: $" + userAccount.getBalance());
                break;
            case 3:
                // Check Balance
                System.out.println("Current balance: $" + userAccount.getBalance());
                break;
            case 4:
                // Exit
                System.out.println("Exiting. Thank you for using the ATM.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 4: Create a user's bank account
        BankAccount userAccount = new BankAccount(1000.0);

        // Step 5: Connect the ATM class with the user's bank account class
        ATM atm = new ATM(userAccount);

        while (true) {
            // Step 2: Display the ATM menu
            atm.displayMenu();

            // Step 6: Validate user input
            int choice;
            do {
                System.out.print("Enter your choice (1-4): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
                choice = scanner.nextInt();
            } while (choice < 1 || choice > 4);

            // Step 7: Perform the selected transaction
            atm.performTransaction(choice, scanner);
        }
    }
}
