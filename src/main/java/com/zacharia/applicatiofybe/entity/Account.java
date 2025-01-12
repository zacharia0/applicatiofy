package com.zacharia.applicatiofybe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Data
@Entity
@Table(name ="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "account_roles",joinColumns = @JoinColumn(name ="account_id"))
//    private Set<String> role;

    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String lastName;


}
