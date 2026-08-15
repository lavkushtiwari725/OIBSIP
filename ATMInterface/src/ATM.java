import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner sc;
    private ArrayList<Transaction> transactions;

    public ATM(Bank bank) {
        this.bank = bank;
        sc = new Scanner(System.in);
        transactions = new ArrayList<>();
    }

    // Login
    public Account login() {

        int attempts = 0;

        while (attempts < 3) {

            System.out.print("Enter User ID: ");
            String userId = sc.nextLine();

            System.out.print("Enter PIN: ");
            int pin = sc.nextInt();
            sc.nextLine();

            Account account = bank.findAccountByUserId(userId);

            if (account != null && account.getPin() == pin) {
                System.out.println("\nLogin Successful!");
                return account;
            }

            attempts++;
            System.out.println("Invalid User ID or PIN.");

            if (attempts < 3) {
                System.out.println("Attempts remaining: " + (3 - attempts));
            }
        }

        System.out.println("Too many incorrect attempts. Access Denied.");
        return null;
    }

    // Main ATM Menu
    public void start() {

        Account currentAccount = login();

        if (currentAccount == null) {
            return;
        }

        while (true) {

            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    showTransactionHistory();
                    break;

                case 2:
                    withdraw(currentAccount);
                    break;

                case 3:
                    deposit(currentAccount);
                    break;

                case 4:
                    transfer(currentAccount);
                    break;

                case 5:
                    System.out.println("Thank you for using ATM!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Withdraw
    private void withdraw(Account account) {

        System.out.print("Enter withdrawal amount: ₹");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (account.withdraw(amount)) {

            transactions.add(
                new Transaction(
                    "WITHDRAW",
                    amount,
                    "Cash withdrawn"
                )
            );

            System.out.println("Withdrawal successful.");
            System.out.println("Remaining Balance: ₹" +
                    account.getBalance());

        } else {

            System.out.println("Insufficient Funds.");
        }
    }

    // Deposit
    private void deposit(Account account) {

        System.out.print("Enter deposit amount: ₹");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        account.deposit(amount);

        transactions.add(
            new Transaction(
                "DEPOSIT",
                amount,
                "Cash deposited"
            )
        );

        System.out.println("Deposit successful.");
        System.out.println("Current Balance: ₹" +
                account.getBalance());
    }

    // Transfer
    private void transfer(Account sender) {

        sc.nextLine();

        System.out.print("Enter recipient Account ID: ");
        String recipientId = sc.nextLine();

        Account receiver =
                bank.findAccountByAccountId(recipientId);

        if (receiver == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        System.out.print("Enter transfer amount: ₹");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (sender.withdraw(amount)) {

            receiver.deposit(amount);

            transactions.add(
                new Transaction(
                    "TRANSFER",
                    amount,
                    "Transferred to Account " + recipientId
                )
            );

            System.out.println("Transfer successful.");
            System.out.println("Remaining Balance: ₹" +
                    sender.getBalance());

        } else {

            System.out.println("Insufficient Funds.");
        }
    }

    // Transaction History
    private void showTransactionHistory() {

        System.out.println("\n===== TRANSACTION HISTORY =====");

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }
    }
}