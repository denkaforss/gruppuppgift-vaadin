package com.example.gruppuppgiftvaadin.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Set;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

     @Column(unique = true)
     @NotEmpty
     @Getter
     @Setter
    private String username;

     @Column
     @NotEmpty
     @Getter
     @Setter
    private String password;

     @OneToMany(mappedBy = "appUser")
     @JsonIgnoreProperties("appUser")
     @Getter
     @Setter
    private Set<Album> setAlbum;

     @OneToMany(mappedBy = "appUser")
     @JsonIgnore
     @Getter
     @Setter
    private Set<Artist> setArtist;

     @OneToMany(mappedBy = "appUser")
     @JsonIgnore
     @Getter
     @Setter
    private Set<Songs> setSongs;

    public AppUser() {}

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id == appUser.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
