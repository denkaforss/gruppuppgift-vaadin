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

    @OneToMany(mappedBy = "album")
    @JsonIgnore
    @Getter
    @Setter
    private Set<Songs>songsSet;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    @Getter
    @Setter
    private Artist artist;

    @Column
    @Getter
    @Setter
    private String imagePath;

    public Album(String albumName, LocalDate releaseYear/*, Set<Songs> songsSet*/, Artist artist) {
        this.albumName = albumName;
        this.releaseYear = releaseYear;
        /*this.songsSet = songsSet;*/
        this.artist = artist;
    }

    public Album() {
    }
}
