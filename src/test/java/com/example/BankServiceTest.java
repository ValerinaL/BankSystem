package com.example.BankSystem.service;

import com.example.BankSystem.model.Account;
import com.example.BankSystem.model.Bank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BankServiceIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BankService bankService;

    @Test
    void testCreateBank() {
        // Given
        Bank bank = new Bank("Test Bank", 10.0, 0.05);

        // When
        Bank createdBank = bankService.createBank(bank);

        // Then
        assertNotNull(createdBank);
        assertEquals("Test Bank", createdBank.getBankName());
    }

    @Test
    void testCreateAccount() {
        Bank bank = new Bank("Test Bank", 10.0, 0.05);
        entityManager.persistAndFlush(bank);
        Account account = new Account("Suzana Su", 1000.0);

        Account savedAccount = bankService.createAccount(account);

        assertNotNull(savedAccount.getAccountId());
        assertEquals(account.getUserName(), savedAccount.getUserName());
        assertEquals(account.getBalance(), savedAccount.getBalance());
    }

    @Test
    void testGetAllAccounts() {
        Bank bank = new Bank("Test Bank", 10.0, 0.05);
        entityManager.persistAndFlush(bank);

        Account account1 = new Account("Suzana Su", 1000.0);
        Account account2 = new Account("", 2000.0);

        bankService.createAccount(account1);
        bankService.createAccount(account2);

        int numberOfAccounts = bankService.getAllAccounts().size();

        assertEquals(2, numberOfAccounts);
    }

    @Test
    void testGetAccountById() {
        Bank bank = new Bank("Test Bank", 10.0, 0.05);
        entityManager.persistAndFlush(bank);
        Account account = new Account("Suzana Su", 1000.0);
        Account savedAccount = bankService.createAccount(account);

        Account retrievedAccount = bankService.getAccountById(savedAccount.getAccountId());

        assertNotNull(retrievedAccount);
        assertEquals(savedAccount.getAccountId(), retrievedAccount.getAccountId());
    }

}
