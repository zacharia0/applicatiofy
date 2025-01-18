package com.zacharia.applicatiofybe.controller;


import com.zacharia.applicatiofybe.dto.UpdateUserRequestDTO;
import com.zacharia.applicatiofybe.dto.UpdateUserResponseDTO;
import com.zacharia.applicatiofybe.entity.Account;
import com.zacharia.applicatiofybe.service.AccountService;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/account")
@CrossOrigin
public class UserController {

    private final AccountService accountService;
    public UserController(AccountService accountService){
        this.accountService = accountService;
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateUserResponseDTO> updateAccount(@RequestBody UpdateUserRequestDTO updateUserRequestDTO, Principal principal){
        System.out.println("This is the username of the account" + principal.getName());
//        Account accountUpdate = accountService.updateUser(principal.getName(), updateUserRequestDTO);
//        accountService.updateUser(principal.getName(), updateUserRequestDTO);
        UpdateUserResponseDTO updateUserResponseDTO = accountService.updateUser(principal.getName(), updateUserRequestDTO);
        return ResponseEntity.ok(updateUserResponseDTO);
    }


}
