package com.zacharia.applicatiofybe.service;

import com.zacharia.applicatiofybe.repository.AccountRepository;
import org.springframework.stereotype.Service;


@Service
public class AccountService{

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }


}
