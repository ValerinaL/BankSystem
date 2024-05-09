package com.example.BankSystem.controller;

import com.example.BankSystem.model.Transaction;
import com.example.BankSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/perform")
    public ResponseEntity<?> performTransaction(@RequestBody Transaction transaction) {
        transactionService.performTransaction(transaction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
