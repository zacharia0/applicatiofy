package com.zacharia.applicatiofybe.service;

import com.zacharia.applicatiofybe.dto.UpdateUserRequestDTO;
import com.zacharia.applicatiofybe.dto.UpdateUserResponseDTO;
import com.zacharia.applicatiofybe.entity.Account;
import com.zacharia.applicatiofybe.repository.AccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AccountService{

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UpdateUserResponseDTO updateUser(String username, UpdateUserRequestDTO updateUserRequestDTO){
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() ->  new RuntimeException("User not found."));

        if(updateUserRequestDTO.getFirstName() != null && !updateUserRequestDTO.getFirstName().isEmpty()){
            account.setFirstName(updateUserRequestDTO.getFirstName());
        }
        if(updateUserRequestDTO.getLastName() != null && !updateUserRequestDTO.getLastName().isEmpty()){
            account.setLastName(updateUserRequestDTO.getLastName());
        }

        if(updateUserRequestDTO.getPassword() != null && !updateUserRequestDTO.getPassword().isEmpty()){
            account.setPassword(passwordEncoder.encode(updateUserRequestDTO.getPassword()));
        }
        accountRepository.save(account);
        return new UpdateUserResponseDTO(account.getId(),account.getFirstName(),account.getLastName(),account.getUsername());
    }

    public void deleteAccount(String username){
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        accountRepository.delete(account);
    }


}
