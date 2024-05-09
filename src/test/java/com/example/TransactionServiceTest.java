package com.example.BankSystem.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TransactionIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testSaveTransaction() {
        Transaction transaction = new Transaction(100.0, "123456", "789012", "Test Transaction");

        Transaction savedTransaction = entityManager.persistAndFlush(transaction);

        assertNotNull(savedTransaction.getAmount());
        assertNotNull(savedTransaction.getOriginatingAccountId());
        assertNotNull(savedTransaction.getResultingAccountId());
        assertNotNull(savedTransaction.getTransactionReason());

        Transaction retrievedTransaction = entityManager.find(Transaction.class, savedTransaction.getAmount());
        assertEquals(transaction.getAmount(), retrievedTransaction.getAmount());
        assertEquals(transaction.getOriginatingAccountId(), retrievedTransaction.getOriginatingAccountId());
        assertEquals(transaction.getResultingAccountId(), retrievedTransaction.getResultingAccountId());
        assertEquals(transaction.getTransactionReason(), retrievedTransaction.getTransactionReason());
    }

}
