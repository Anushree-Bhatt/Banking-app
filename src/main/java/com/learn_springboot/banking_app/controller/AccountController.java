package com.learn_springboot.banking_app.controller;

import com.learn_springboot.banking_app.dto.AccountDto;
import com.learn_springboot.banking_app.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;
    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        logger.info(accountDto+"");
        AccountDto res = accountService.createAccount(accountDto);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(res.getAccountId()).toUri();
        return ResponseEntity.created(location).body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable(name = "id") Long accountId){
        AccountDto accountDto = accountService.findAccount(accountId);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }
}
