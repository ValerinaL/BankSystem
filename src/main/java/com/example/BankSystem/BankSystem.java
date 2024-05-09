import java.util.Scanner;

public class BankSystem {

    public static void main(String[] args) {
        
        Bank bank = createBank();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to " + bank.getBankName() + " Bank!");

        while (!exit) {
            System.out.println("1. Create Account:");
            System.out.println("2. Perform Transaction:");
            System.out.println("3. Withdraw Money:");
            System.out.println("4. Deposit Money:");
            System.out.println("5. View Transactions:");
            System.out.println("6. View Account Balance:");
            System.out.println("7. View Bank Accounts:");
            System.out.println("8. Check Bank Total Transaction Fee Amount:");
            System.out.println("9. Check Bank Total Transfer Amount:");
            System.out.println("10. Exit:");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount(bank, scanner);
                    break;
                case 2:
                    performTransaction(bank, scanner);
                    break;
                case 3:
                    withdrawMoney(bank, scanner);
                    break;
                case 4:
                    depositMoney(bank, scanner);
                    break;
                case 5:
                    viewTransactions(bank, scanner);
                    break;
                case 6:
                    viewAccountBalance(bank, scanner);
                    break;
                case 7:
                    bank.printAccounts();
                    break;
                case 8:
                    System.out.println("Bank Total Transaction Fee Amount: $" + bank.getTotalTransactionFeeAmount());
                    break;
                case 9:
                    System.out.println("Bank Total Transfer Amount: $" + bank.getTotalTransferAmount());
                    break;
                case 10:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static Bank createBank() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter bank name: ");
        String bankName = scanner.nextLine();
        System.out.print("Enter transaction flat fee amount: $");
        double transactionFlatFeeAmount = scanner.nextDouble();
        System.out.print("Enter transaction percent fee value (%): ");
        double transactionPercentFeeValue = scanner.nextDouble();
        return new Bank(bankName, transactionFlatFeeAmount, transactionPercentFeeValue);
    }

    private static void createAccount(Bank bank, Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter user name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter initial balance: $");
        double balance = scanner.nextDouble();
        bank.addAccount(new Account(accountId, userName, balance));
        System.out.println("Account created successfully.");
    }

    private static void performTransaction(Bank bank, Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter originating account ID: ");
        String originAccountId = scanner.nextLine();
        System.out.print("Enter resulting account ID: ");
        String resultAccountId = scanner.nextLine();
        System.out.print("Enter transaction amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter transaction reason: ");
        String reason = scanner.nextLine();
        bank.performTransaction(new Transaction(amount, originAccountId, resultAccountId, reason));
    }
    
    private static void withdrawMoney(Bank bank, Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter withdrawal amount: $");
        double amount = scanner.nextDouble();
        try {
            Account account = bank.getAccount(accountId);
            if (account != null) {
                account.withdraw(amount);
                System.out.println("Withdrawal successful.");
            } else {
                throw new IllegalArgumentException("Account not found.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void depositMoney(Bank bank, Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter deposit amount: $");
        double amount = scanner.nextDouble();
        try {
            Account account = bank.getAccount(accountId);
            if (account != null) {
                account.deposit(amount);
                System.out.println("Deposit successful.");
            } else {
                throw new IllegalArgumentException("Account not found.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewTransactions(Bank bank, Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();
        Account account = bank.getAccount(accountId);
        if (account != null) {
            System.out.println("Transactions for Account ID: " + accountId);
            for (Transaction transaction : account.getTransactions()) {
                System.out.println("Amount: $" + String.format("%.2f", transaction.getAmount()));
                System.out.println("Originating Account ID: " + transaction.getOriginatingAccountId());
                System.out.println("Resulting Account ID: " + transaction.getResultingAccountId());
                System.out.println("Reason: " + transaction.getTransactionReason());
                System.out.println("-----------------------------");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void viewAccountBalance(Bank bank, Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();
        Account account = bank.getAccount(accountId);
        if (account != null) {
            System.out.println("Account Balance for Account ID " + accountId + ": $" + String.format("%.2f", account.getBalance()));
        } else {
            System.out.println("Account not found.");
        }
    }
}
