package com.example.gruppuppgiftvaadin.backend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Songs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column
    @NotBlank
    @Getter
    @Setter
    private String songName;

    @Column
    @Getter
    @Setter
    private int songLength;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    @Getter
    @Setter
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "album_id")
    @Getter
    @Setter
    private Album album;

    @ManyToOne
    @JoinColumn(name = "appUser_id")
    @Getter
    @Setter
    private AppUser appUser;

    public Songs(){}

    public Songs(String songName, int songLength, Artist artist, Album album, AppUser appUser) {
        this.songName = songName;
        this.songLength = songLength;
        this.artist = artist;
        this.album = album;
        this.appUser = appUser;
    }
}
