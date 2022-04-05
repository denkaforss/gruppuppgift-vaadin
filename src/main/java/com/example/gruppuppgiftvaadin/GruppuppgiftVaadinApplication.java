package com.example.gruppuppgiftvaadin;

import com.example.gruppuppgiftvaadin.backend.entities.Album;
import com.example.gruppuppgiftvaadin.backend.entities.Artist;
import com.example.gruppuppgiftvaadin.backend.entities.Songs;
import com.example.gruppuppgiftvaadin.backend.repositories.AlbumRepo;
import com.example.gruppuppgiftvaadin.backend.repositories.ArtistRepo;
import com.example.gruppuppgiftvaadin.backend.repositories.SongsRepo;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
@Theme(themeClass = Lumo.class, variant = Lumo.DARK)
public class GruppuppgiftVaadinApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(GruppuppgiftVaadinApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ArtistRepo artistRepo, AlbumRepo albumRepo, SongsRepo songsRepo) {
        return args -> {
            Artist artist = new Artist("first artist", LocalDate.of(2001, 1, 1), "sweden");
            artistRepo.save(artist);

            Album album = new Album("first album", LocalDate.of(2002, 2, 2), artist);
            album.setArtist(artist);
            albumRepo.save(album);

            Songs songs = new Songs("first song", 149, artist, album);
            album.setArtist(artist);
            album.setAlbumName(album.getAlbumName());
            songsRepo.save(songs);

            Artist artist2 = new Artist("second artist", LocalDate.of(2001, 1, 1), "denmark");
            artistRepo.save(artist2);

            Album album2 = new Album("second album", LocalDate.of(2002, 2, 2), artist2);
            album.setArtist(artist2);
            albumRepo.save(album2);

            Songs songs2 = new Songs("second song", 149, artist2, album2);
            album.setArtist(artist2);
            album.setAlbumName(album2.getAlbumName());
            songsRepo.save(songs2);
        };
    }
}
