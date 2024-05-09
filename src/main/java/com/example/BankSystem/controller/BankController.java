import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/create")
    public ResponseEntity<Bank> createBank(@RequestBody BankRequest bankRequest) {
        Bank bank = bankService.createBank(bankRequest);
        return new ResponseEntity<>(bank, HttpStatus.CREATED);
    }

    @PostMapping("/account")
    public ResponseEntity<Account> addAccount(@RequestBody AccountRequest accountRequest) {
        Account account = bankService.addAccount(accountRequest);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> performTransaction(@RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = bankService.performTransaction(transactionRequest);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping("/transactions/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable String accountId) {
        List<Transaction> transactions = bankService.getTransactions(accountId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}/balance")
    public ResponseEntity<Double> getAccountBalance(@PathVariable String accountId) {
        double balance = bankService.getAccountBalance(accountId);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }
    
    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getBankAccounts() {
        List<Account> accounts = bankService.getBankAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
    }
