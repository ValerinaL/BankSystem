package com.example.BankSystem.service;

import com.example.BankSystem.model.Account;
import com.example.BankSystem.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AccountServiceIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Test
    void testCreateAccount() {
        Account account = new Account("Suzana", 1000.0);
        Account savedAccount = accountService.createAccount(account);
        assertNotNull(savedAccount.getAccountId());
        assertEquals(account.getUserName(), savedAccount.getUserName());
        assertEquals(account.getBalance(), savedAccount.getBalance());
    }

    @Test
    void testDepositMoney() {
        Account account = new Account("Suzana Su", 1000.0);
        Account savedAccount = entityManager.persistAndFlush(account);
        accountService.depositMoney(savedAccount.getAccountId(), 500.0);

        Account updatedAccount = entityManager.find(Account.class, savedAccount.getAccountId());
        assertNotNull(updatedAccount);
        assertEquals(1500.0, updatedAccount.getBalance());
    }

    @Test
    void testWithdrawMoney() {
        Account account = new Account("Suzana Su", 1000.0);
        Account savedAccount = entityManager.persistAndFlush(account);

        accountService.withdrawMoney(savedAccount.getAccountId(), 500.0);

        Account updatedAccount = entityManager.find(Account.class, savedAccount.getAccountId());
        assertNotNull(updatedAccount);
        assertEquals(500.0, updatedAccount.getBalance());
    }

    @Test
    void testGetAccountBalance() {
        Account account = new Account("Suzana Su", 1000.0);
        Account savedAccount = entityManager.persistAndFlush(account);

        double balance = accountService.getAccountBalance(savedAccount.getAccountId());

        assertEquals(1000.0, balance);
    }

    @Test
    void testGetAccountTransactions() {
        Account account = new Account("John Doe", 1000.0);
        Transaction transaction1 = new Transaction(500.0, account.getAccountId(), "123456", "Transaction 1");
        Transaction transaction2 = new Transaction(200.0, account.getAccountId(), "789012", "Transaction 2");
        account.addTransaction(transaction1);
        account.addTransaction(transaction2);
        Account savedAccount = entityManager.persistAndFlush(account);

        List<Transaction> transactions = accountService.getAccountTransactions(savedAccount.getAccountId());

        assertNotNull(transactions);
        assertEquals(2, transactions.size());
    }
}
