import java.util.ArrayList;
import java.util.List;


@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String bankName;
    private List<Account> accounts;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double transactionFlatFeeAmount;
    private double transactionPercentFeeValue;

    public Bank(String bankName, double transactionFlatFeeAmount, double transactionPercentFeeValue) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
        this.totalTransactionFeeAmount = 0;
        this.totalTransferAmount = 0;
        this.transactionFlatFeeAmount = transactionFlatFeeAmount;
        this.transactionPercentFeeValue = transactionPercentFeeValue;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void performTransaction(Transaction transaction) {
        double amount = transaction.getAmount();
        String originAccountId = transaction.getOriginatingAccountId();
        String resultAccountId = transaction.getResultingAccountId();
        String reason = transaction.getTransactionReason();

        Account originAccount = getAccount(originAccountId);
        Account resultAccount = getAccount(resultAccountId);

        if (originAccount == null || resultAccount == null) {
            throw new IllegalArgumentException("One or both accounts do not exist.");
        }

        if (!originAccount.hasSufficientBalance(amount)) {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        double transactionFee = calculateTransactionFee(amount);
        double totalAmount = amount + transactionFee;

        originAccount.withdraw(totalAmount);
        resultAccount.deposit(amount);

        totalTransactionFeeAmount += transactionFee;
        totalTransferAmount += amount;
    }


    public List<Transaction> getTransactions(String accountId) {
        Account account = getAccount(accountId);
        if (account != null) {
            return account.getTransactions();
        }
        return null;
    }

    public void printAccounts() {
        for (Account account : accounts) {
            System.out.println("Account ID: " + account.getAccountId());
            System.out.println("User Name: " + account.getUserName());
            System.out.println("Balance: $" + String.format("%.2f", account.getBalance()));
            System.out.println("-----------------------------");
        }
    }

    public double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public String getBankName() {
        return bankName;
    }

    public Account getAccount(String accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    private double calculateTransactionFee(double amount) {
        return transactionFlatFeeAmount + (transactionPercentFeeValue / 100.0) * amount;
    }
}
