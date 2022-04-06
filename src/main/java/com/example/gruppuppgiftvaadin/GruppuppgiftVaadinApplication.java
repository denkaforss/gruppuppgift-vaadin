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
            Artist avantasia = new Artist("Avantasia", LocalDate.of(1999, 4, 14), "Germany");
            artistRepo.save(avantasia);

            Album album = new Album("The Wicked Symphony", LocalDate.of(2010, 4, 3), avantasia);
            album.setArtist(avantasia);
            album.setImagePath("/images/avantasia.jpg");
            albumRepo.save(album);

            Songs avantSongs1 = new Songs("The Wicked Symphony", 568, avantasia, album);
            Songs avantSongs2 = new Songs("Wastelands", 284, avantasia, album);
            Songs avantSongs3 = new Songs("Scales of Justice", 304, avantasia, album);
            Songs avantSongs4 = new Songs("Dying for an Angel", 272, avantasia, album);
            Songs avantSongs5 = new Songs("Blizzard On a Broken Mirror", 367, avantasia, album);
            Songs avantSongs6 = new Songs("Runaway Train", 522, avantasia, album);
            Songs avantSongs7 = new Songs("Crestfallen", 242, avantasia, album);
            Songs avantSongs8 = new Songs("Forever is a Long Time", 305, avantasia, album);
            Songs avantSongs9 = new Songs("Black Wings", 277, avantasia, album);
            Songs avantSongs10 = new Songs("States of Matter", 237, avantasia, album);
            Songs avantSongs11 = new Songs("The Edge", 252, avantasia, album);

            album.setArtist(avantasia);
            album.setAlbumName(album.getAlbumName());
            songsRepo.save(avantSongs1);
            songsRepo.save(avantSongs2);

            Artist artist2 = new Artist("Miss May I", LocalDate.of(2001, 1, 1), "denmark");
            artistRepo.save(artist2);

            Album album2 = new Album("Shadows Inside", LocalDate.of(2002, 2, 2), artist2);
            album2.setArtist(artist2);
            album2.setImagePath("/images/shadowsinside.jpg");
            albumRepo.save(album2);

            Songs songs2 = new Songs("Shadows Inside", 236, artist2, album2);
            album2.setArtist(artist2);
            album2.setAlbumName(album2.getAlbumName());
            songsRepo.save(songs2);


            Album album3 = new Album("Moonglow", LocalDate.of(2019, 2, 2), avantasia);
            album3.setArtist(avantasia);
            album3.setImagePath("/images/moonglow.png");
            albumRepo.save(album3);

            Songs songs3 = new Songs("Third song", 149, avantasia, album3);
            album3.setArtist(avantasia);
            album3.setAlbumName(album3.getAlbumName());
            songsRepo.save(songs3);

            Artist artist4 = new Artist("Fourth artist", LocalDate.of(2001, 1, 1), "Spain");
            artistRepo.save(artist4);

            Album album4 = new Album("Fourth album", LocalDate.of(2002, 2, 2), artist4);
            album4.setArtist(artist4);
            album4.setImagePath("/images/avantasia.jpg");
            albumRepo.save(album4);

            Songs songs4 = new Songs("Fourth song", 149, artist4, album4);
            album4.setArtist(artist4);
            album4.setAlbumName(album4.getAlbumName());
            songsRepo.save(songs4);
        };
    }
}
