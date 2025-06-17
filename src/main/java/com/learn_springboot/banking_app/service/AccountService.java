package com.learn_springboot.banking_app.service;

import com.learn_springboot.banking_app.dto.AccountDto;
import com.learn_springboot.banking_app.entity.Account;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto findAccount(Long accountId);
    AccountDto depositAmount(Long accountId, Double amount);
    AccountDto withdrawAmount(Long accountId, Double amount);
    List<AccountDto> findAllAccounts();
    void deleteAccount(Long accountId);
}
