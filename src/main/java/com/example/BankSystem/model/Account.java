import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account extends BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Account(String accountId, String userName, double balance) {
        super(accountId, userName, balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction(amount, accountId, accountId, "Deposit"));
    }

    @Override
    public void withdraw(double amount) {
        if (!hasSufficientBalance(amount)) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        balance -= amount;
        transactions.add(new Transaction(amount, accountId, accountId, "Withdrawal"));
    }
}
