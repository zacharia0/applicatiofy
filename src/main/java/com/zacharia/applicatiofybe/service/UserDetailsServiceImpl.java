package com.zacharia.applicatiofybe.service;

import com.zacharia.applicatiofybe.entity.AccountEntity;
import com.zacharia.applicatiofybe.repository.AccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    public UserDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity accountEntity = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        /*
            The User class converts Account to UserDetails (Account returns Optional)
            User Class: This is a built-in Spring Security implementation of UserDetails.
             It stores user details like:
                    Username (account.getUsername())
                    Password (account.getPassword())
                    Authorities (roles/permissions): In this case, it's an empty set (new HashSet<>())
                                                     because no roles or permissions are being set here.
        */
        return new User(accountEntity.getUsername(), accountEntity.getPassword(), new HashSet<>());
    }

}
