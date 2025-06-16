package com.learn_springboot.banking_app.service;

import com.learn_springboot.banking_app.dto.AccountDto;
import com.learn_springboot.banking_app.entity.Account;
import com.learn_springboot.banking_app.mapper.AccountMapper;
import com.learn_springboot.banking_app.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account acc = accountRepository.save(AccountMapper.mapToAccount(accountDto));
        return AccountMapper.mapToAccountDto(acc);
    }

    @Override
    public AccountDto findAccount(Long accountId) {
        Account account = accountRepository
                        .findById(accountId)
                        .orElseThrow(() -> new RuntimeException("Account not Found!"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto depositAmount(Long accountId, Double amount) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not Found!"));

        Double new_balance = account.getBalance()+amount;
        account.setBalance(new_balance);
        Account res = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(res);
    }
}
