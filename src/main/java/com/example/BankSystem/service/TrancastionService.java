import com.example.BankSystem.model.Account;
import com.example.BankSystem.model.Bank;
import com.example.BankSystem.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private BankService bankService;

    @Autowired
    private AccountService accountService;

    public void performTransaction(Transaction transaction) {
        double amount = transaction.getAmount();
        String originAccountId = transaction.getOriginatingAccountId();
        String resultAccountId = transaction.getResultingAccountId();
        String reason = transaction.getTransactionReason();

        Account originAccount = accountService.getAccount(originAccountId);
        Account resultAccount = accountService.getAccount(resultAccountId);

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

        Bank bank = bankService.getBankByAccountId(originAccountId);
        bank.addToTotalTransactionFeeAmount(transactionFee);
        bank.addToTotalTransferAmount(amount);
    }

    private double calculateTransactionFee(double amount) {
        double flatFee = bank.getFlatTransactionFeeAmount();
        double percentFee = bank.getTransactionPercentFeeValue() / 100.0;
    
        double flatFeeAmount = flatFee;
        double percentFeeAmount = percentFee * amount;
    
        double transactionFee = Math.max(flatFeeAmount, percentFeeAmount);
    
        return transactionFee;
    }
    
}
