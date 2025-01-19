package com.zacharia.applicatiofybe.controller;


import com.zacharia.applicatiofybe.dto.UpdateUserRequestDTO;
import com.zacharia.applicatiofybe.dto.UpdateUserResponseDTO;
import com.zacharia.applicatiofybe.entity.Account;
import com.zacharia.applicatiofybe.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/account")
//@CrossOrigin
public class UserController {

    private final AccountService accountService;
    public UserController(AccountService accountService){
        this.accountService = accountService;
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<UpdateUserResponseDTO> updateAccount( @PathVariable String username, @RequestBody UpdateUserRequestDTO updateUserRequestDTO ){
        UpdateUserResponseDTO updateUserResponseDTO = accountService.updateUser(username, updateUserRequestDTO);
        return ResponseEntity.ok(updateUserResponseDTO);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteAccount(@PathVariable String username){
        System.out.println("This is the username:  " + username);
        this.accountService.deleteAccount(username);
        return ResponseEntity.ok("Account deleted Successfully.");
    }


}
