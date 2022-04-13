package com.example.gruppuppgiftvaadin.backend.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class UserPrivilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String privilegeName;

    public UserPrivilege(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public UserPrivilege() {

    }
}
