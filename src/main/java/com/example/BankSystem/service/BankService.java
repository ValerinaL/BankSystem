import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public BankService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(int accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(int accountId) {
        accountRepository.deleteById(accountId);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByAccountId(int accountId) {
        return transactionRepository.findByOriginatingAccountIdOrResultingAccountId(accountId, accountId);
    }

    // Add more methods as needed to interact with the database
}
package com.example.BankSystem.service;

import com.example.BankSystem.model.Account;
import com.example.BankSystem.model.Bank;
import com.example.BankSystem.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankService {
    private Map<String, Bank> banks = new HashMap<>();

    public Bank createBank(Bank bank) {
        banks.put(bank.getBankName(), bank);
        return bank;
    }

    public List<Account> getBankAccounts(String bankName) {
        Bank bank = banks.get(bankName);
        if (bank != null) {
            return bank.getAccounts();
        }
        return null;
    }

    public List<Transaction> getBankTransactions(String bankName) {
        Bank bank = banks.get(bankName);
        if (bank != null) {
            // Implement logic to fetch transactions for the bank
        }
        return null;
    }

    public double getTotalTransactionFeeAmount(String bankName) {
        Bank bank = banks.get(bankName);
        if (bank != null) {
            return bank.getTotalTransactionFeeAmount();
        }
        return 0;
    }

    public double getTotalTransferAmount(String bankName) {
        Bank bank = banks.get(bankName);
        if (bank != null) {
            return bank.getTotalTransferAmount();
        }
        return 0;
    }
}
