package com.zacharia.applicatiofybe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name ="accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @JsonIgnore
    @Column(nullable = false)
    private String password;
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "account_roles",joinColumns = @JoinColumn(name ="account_id"))
//    private Set<String> role;

    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String lastName;

    @JsonIgnore // Prevent serialization of job applications when serializing the account
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List< JobApplicationEntity> jobApplication = new ArrayList<>();


}
