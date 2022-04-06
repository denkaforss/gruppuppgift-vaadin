package com.example.gruppuppgiftvaadin.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column
    @Getter
    @Setter
    @NotNull
    private String artistName;

    @Column
    @Getter
    @Setter
    private LocalDate startingYear;

    @Column
    @Getter
    @Setter
    private String homeCountry;

    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    @Getter
    @Setter
    public Set<Songs> songsSet;

    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    @Getter
    @Setter
    public Set<Album> albumSet;

    @ManyToOne
    @JoinColumn(name = "appUser_id")
    @Getter
    @Setter
    private AppUser appUser;

    public Artist(){}

    public Artist(String artistName, LocalDate startingYear, String homeCountry, AppUser appUser) {
        this.artistName = artistName;
        this.startingYear = startingYear;
        this.homeCountry = homeCountry;
        this.appUser = appUser;
    }
}
