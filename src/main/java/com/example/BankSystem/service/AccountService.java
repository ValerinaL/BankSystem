import com.example.BankSystem.model.Account;
import com.example.BankSystem.model.Transaction;
import com.example.BankSystem.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountById(String accountId) {
        return accountRepository.findById(accountId)
                                 .orElseThrow(() -> new IllegalArgumentException("Account not found with ID: " + accountId));
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(String accountId) {
        accountRepository.deleteById(accountId);
    }

    public void deposit(String accountId, double amount) {
        Account account = getAccountById(accountId);
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        updateAccount(account);
    }

    public void withdraw(String accountId, double amount) {
        Account account = getAccountById(accountId);
        double newBalance = account.getBalance() - amount;
        if (newBalance < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        account.setBalance(newBalance);
        updateAccount(account);
    }

    public double getAccountBalance(String accountId) {
        Account account = getAccountById(accountId);
        return account.getBalance();
    }

    public List<Transaction> getAccountTransactions(String accountId) {
        Account account = getAccountById(accountId);
        return account.getTransactions();
    }
}
