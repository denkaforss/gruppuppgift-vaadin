package com.example.gruppuppgiftvaadin.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roleName;
    @OneToMany(mappedBy = "role")
    private List<UserRoleToPrivilege> userRoleToPrivileges;

    public UserRole(String roleName) {
        this.roleName = roleName;
    }
}
