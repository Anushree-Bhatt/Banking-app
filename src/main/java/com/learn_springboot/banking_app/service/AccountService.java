package com.learn_springboot.banking_app.service;

import com.learn_springboot.banking_app.dto.AccountDto;
import com.learn_springboot.banking_app.entity.Account;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
}
