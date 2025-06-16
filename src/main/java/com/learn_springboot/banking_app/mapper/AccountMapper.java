package com.learn_springboot.banking_app.mapper;

import com.learn_springboot.banking_app.dto.AccountDto;
import com.learn_springboot.banking_app.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        return new Account(accountDto.getAccountId(), accountDto.getAccountHolderName(), accountDto.getBalance());
    }

    public static AccountDto mapToAccountDto(Account account){
        return new AccountDto(account.getAccountId(), account.getAccountHolderName(), account.getBalance());
    }
}
