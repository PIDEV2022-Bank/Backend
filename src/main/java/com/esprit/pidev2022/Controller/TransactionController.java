package com.esprit.pidev2022.Controller;

import com.esprit.pidev2022.Dto.TransferDto;
import com.esprit.pidev2022.Exception.AccountNotFoundException;
import com.esprit.pidev2022.Exception.BalanceNotEnoughException;
import com.esprit.pidev2022.entities.*;
import com.esprit.pidev2022.services.AccountService;
import com.esprit.pidev2022.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/transactions")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;




    @PostMapping("/accounts/retrait")
    public ResponseEntity<WithdrawalTransaction> withdrawal(@RequestBody WithdrawalTransaction withdrawalTransaction) throws AccountNotFoundException, BalanceNotEnoughException {
        this.transactionService.withdrawal(withdrawalTransaction.getAccount().getAccountNumber(), withdrawalTransaction.getAmount(), withdrawalTransaction.getDescription());
        return new ResponseEntity<>(withdrawalTransaction, HttpStatus.OK);

    }



    @PostMapping("/accounts/depot")
    public ResponseEntity<DepositTransaction> deposit (@RequestBody DepositTransaction depositTransaction) throws AccountNotFoundException {
        this.transactionService.deposit(depositTransaction.getAccount().getAccountNumber(), depositTransaction.getAmount(), depositTransaction.getDescription());
        return new ResponseEntity<>(depositTransaction, HttpStatus.OK);

    }




    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferDto transfer) throws AccountNotFoundException, BalanceNotEnoughException {
        this.transactionService.transfer(
                transfer.getAccountSource(),
                transfer.getAccountDestination(),
                transfer.getAmount());

    }



    @GetMapping("/accounts/{id}/operations")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable("id") Long accountId){
        List<Transaction> transactions =transactionService.findAccountTransaction(accountId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }



}


