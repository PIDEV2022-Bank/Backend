package com.esprit.pidev2022.Controller;
import com.esprit.pidev2022.entities.*;
import com.esprit.pidev2022.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    private AccountService accountService;




  /*  @RequestMapping("/all")
    public List<Account> findAccountList() {
        List<Account> appointmentList = appointmentService.findAll();

        return appointmentList;
    }

    @RequestMapping("/{id}/confirm")
    public void confirmAppointment(@PathVariable("id") Long id) {
        appointmentService.confirmAppointment(id);
    }*/



    @PostMapping("/add/savingAccount")
    @ResponseBody
    public ResponseEntity<SavingAccount> addSavingAccount (@RequestBody SavingAccount savingAccount) {
        SavingAccount saving = accountService.createSavingAccount(savingAccount);
        return new ResponseEntity<>(saving, HttpStatus.CREATED);

    }

    @PostMapping("/add/depositAccount")
    @ResponseBody
    public ResponseEntity<Account> addDepositAccount () {
        Account deposit = accountService.createDepositAccount();
        return new ResponseEntity<>(deposit, HttpStatus.CREATED);

    }



    @PutMapping("/update")
    public ResponseEntity<Account> updateForum(@RequestBody Account account) {
        Account updateAccount = accountService.updateAccount(account);
        return new ResponseEntity<>(updateAccount, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccountByID(@PathVariable("id") Long id) {

        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllForum() {

        List<Account> accounts = accountService.findAllAccount();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

}

