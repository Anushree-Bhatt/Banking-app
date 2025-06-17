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
import java.util.List;
import java.util.Map;

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
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/accounts/{id}").buildAndExpand(res.accountId()).toUri();
        return ResponseEntity.created(location).body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable(name = "id") Long accountId){
        AccountDto accountDto = accountService.findAccount(accountId);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<Void> updateBalance_deposit(@PathVariable(name = "id") Long accountId, @RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto res = accountService.depositAmount(accountId, amount);
        logger.info(res+"");

        return ResponseEntity.status(204).headers(httpHeaders -> {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/accounts/{id}").buildAndExpand(accountId).toUri();
            httpHeaders.setLocation(location);
        }).build();
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<Void> updateBalance_withdraw(@PathVariable(name = "id") Long accountId, @RequestBody Map<String, Double> request){
        AccountDto accountDto = accountService.withdrawAmount(accountId, request.get("amount"));
        logger.info(accountDto+"");

        return ResponseEntity.status(204).headers(httpHeaders -> {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/accounts/{id}").buildAndExpand(accountId).toUri();
            httpHeaders.setLocation(location);
        }).build();
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accountDtos = accountService.findAllAccounts();
        return ResponseEntity.ok(accountDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable(name = "id") Long accountId){
        accountService.deleteAccount(accountId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
