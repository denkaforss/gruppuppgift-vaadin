package com.example.gruppuppgiftvaadin.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column
    @NotEmpty
    @Getter
    @Setter
    private String albumName;

    @Column
    /*@NotEmpty*/
    @Getter
    @Setter
    private LocalDate releaseYear;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Getter
    @Setter
    private Set<Songs>songsSet;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    @Getter
    @Setter
    private Artist artist;

    @Getter
    @Setter
    private String artistName;

    @ManyToOne
    @JoinColumn(name = "appUser_id")
    @Getter
    @Setter
    private AppUser appUser;

    @Column
    @Getter
    @Setter
    private String imagePath;

    @Column
    @Getter
    @Setter
    private String detailedInfo;


    public Album(String albumName, LocalDate releaseYear/*, Set<Songs> songsSet*/, Artist artist, AppUser appUser, String detailedInfo, String imagePath, String artistName) {
        this.albumName = albumName;
        this.releaseYear = releaseYear;
        this.artist = artist;
        this.appUser = appUser;
        this.detailedInfo = detailedInfo;
        this.imagePath = imagePath;
        this.artistName = artistName;
    }

    public Album() {}
}
