package com.zacharia.applicatiofybe.controller;


import com.zacharia.applicatiofybe.dto.UpdateUserRequestDTO;
import com.zacharia.applicatiofybe.dto.UpdateUserResponseDTO;
import com.zacharia.applicatiofybe.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> deleteAccount(@PathVariable String username){
        System.out.println("This is the username:  " + username);
        this.accountService.deleteAccount(username);
//        return ResponseEntity.ok("Account deleted Successfully.");
        Map<String, String> response = new HashMap<>();
        response.put("message", "Account deleted successfully.");

        return ResponseEntity.ok(response);
    }


}
