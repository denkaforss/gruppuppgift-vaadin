package com.example.gruppuppgiftvaadin.backend.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserToRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private AppUser user;

    @ManyToOne
    private UserRole role;

    public UserToRole(AppUser user, UserRole role) {
        this.user = user;
        this.role = role;
    }
}
