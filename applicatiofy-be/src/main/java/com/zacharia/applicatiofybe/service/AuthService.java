package com.zacharia.applicatiofybe.service;

import com.zacharia.applicatiofybe.dto.JwtResponseDTO;
import com.zacharia.applicatiofybe.dto.LoginRequestDTO;
import com.zacharia.applicatiofybe.dto.SignUpRequestDTO;
import com.zacharia.applicatiofybe.entity.AccountEntity;
import com.zacharia.applicatiofybe.exception.InvalidCredentialsException;
import com.zacharia.applicatiofybe.repository.AccountRepository;
import com.zacharia.applicatiofybe.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public JwtResponseDTO authenticateUser(LoginRequestDTO loginRequestDTO) {

        try{

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginRequestDTO.getUsername());

                    final String jwt = jwtUtil.generateToken(userDetails);
                    AccountEntity accountEntity = accountRepository.findByUsername(loginRequestDTO.getUsername())
                            .orElseThrow(() -> new RuntimeException("Account not found"));
    //                System.out.println("GENERATED JWT TOKEN: " + jwt);

                    return new JwtResponseDTO(jwt, accountEntity.getFirstName(), accountEntity.getLastName(), accountEntity.getUsername(), accountEntity.getId());
        }catch(Exception e){
            throw new InvalidCredentialsException("Invalid username or password");
        }
    }

    public JwtResponseDTO registerUser(SignUpRequestDTO signUpRequestDTO) {
        if(accountRepository.findByUsername(signUpRequestDTO.getUsername()).isPresent()) {
            throw new InvalidCredentialsException("Username is already taken!");
        }

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUsername(signUpRequestDTO.getUsername());
        accountEntity.setFirstName(signUpRequestDTO.getFirstName());
        accountEntity.setLastName(signUpRequestDTO.getLastName());
        accountEntity.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
        accountRepository.save(accountEntity);

        // Automatically authenticate after registration.
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(signUpRequestDTO.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // Genrate Jwt Token
        final String jwt = jwtUtil.generateToken(userDetails);

        return new JwtResponseDTO(jwt, accountEntity.getFirstName(), accountEntity.getLastName(), accountEntity.getUsername(), accountEntity.getId());
    }


}
