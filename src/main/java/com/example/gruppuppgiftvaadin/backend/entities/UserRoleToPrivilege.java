package com.example.gruppuppgiftvaadin.backend.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserRoleToPrivilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private UserRole role;

    @ManyToOne
    private UserPrivilege privilege;

    public UserRoleToPrivilege(UserRole role, UserPrivilege privilege) {
        this.role = role;
        this.privilege = privilege;
    }

    public UserRoleToPrivilege() {
    }
}
