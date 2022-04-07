package com.example.gruppuppgiftvaadin;

import com.example.gruppuppgiftvaadin.backend.entities.Album;
import com.example.gruppuppgiftvaadin.backend.entities.AppUser;
import com.example.gruppuppgiftvaadin.backend.entities.Artist;
import com.example.gruppuppgiftvaadin.backend.entities.Songs;
import com.example.gruppuppgiftvaadin.backend.repositories.AlbumRepo;
import com.example.gruppuppgiftvaadin.backend.repositories.AppUserRepo;
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
    CommandLineRunner init(ArtistRepo artistRepo, AlbumRepo albumRepo, SongsRepo songsRepo, AppUserRepo appUserRepo) {
        return args -> {
            AppUser appUser = new AppUser("admin", "password");
            appUserRepo.save(appUser);

            Artist avantasia = new Artist("Avantasia", LocalDate.of(1999, 4, 14), "Germany", appUser, "Massa info om artisten...");
            artistRepo.save(avantasia);

            Album album = new Album("The Wicked Symphony", LocalDate.of(2010, 4, 3), avantasia, appUser, "massa infor om albumet...");
            album.setArtist(avantasia);
            album.setImagePath("/images/avantasia.jpg");
            albumRepo.save(album);

            Songs avantSongs1 = new Songs("The Wicked Symphony", 568, avantasia, album, appUser);
            Songs avantSongs2 = new Songs("Wastelands", 284, avantasia, album, appUser);
            Songs avantSongs3 = new Songs("Scales of Justice", 304, avantasia, album, appUser);
            Songs avantSongs4 = new Songs("Dying for an Angel", 272, avantasia, album, appUser);
            Songs avantSongs5 = new Songs("Blizzard On a Broken Mirror", 367, avantasia, album, appUser);
            Songs avantSongs6 = new Songs("Runaway Train", 522, avantasia, album, appUser);
            Songs avantSongs7 = new Songs("Crestfallen", 242, avantasia, album, appUser);
            Songs avantSongs8 = new Songs("Forever is a Long Time", 305, avantasia, album, appUser);
            Songs avantSongs9 = new Songs("Black Wings", 277, avantasia, album, appUser);
            Songs avantSongs10 = new Songs("States of Matter", 237, avantasia, album, appUser);
            Songs avantSongs11 = new Songs("The Edge", 252, avantasia, album, appUser);

            album.setArtist(avantasia);
            album.setAlbumName(album.getAlbumName());
            songsRepo.save(avantSongs1);
            songsRepo.save(avantSongs2);

            Artist artist2 = new Artist("Miss May I", LocalDate.of(2001, 1, 1), "denmark", appUser, "Massa info om artisten...");
            artistRepo.save(artist2);

            Album album2 = new Album("Shadows Inside", LocalDate.of(2002, 2, 2), artist2, appUser, "Massa info om albumet...");
            album2.setArtist(artist2);
            album2.setImagePath("/images/shadowsinside.jpg");
            albumRepo.save(album2);

            Songs songs2 = new Songs("Shadows Inside", 236, artist2, album2, appUser);
            album2.setArtist(artist2);
            album2.setAlbumName(album2.getAlbumName());
            songsRepo.save(songs2);


            Album album3 = new Album("Moonglow", LocalDate.of(2019, 2, 2), avantasia, appUser, "Massa info om albumet...");
            album3.setArtist(avantasia);
            album3.setImagePath("/images/moonglow.png");
            albumRepo.save(album3);

            Songs songs3 = new Songs("Third song", 149, avantasia, album3, appUser);
            album3.setArtist(avantasia);
            album3.setAlbumName(album3.getAlbumName());
            songsRepo.save(songs3);

            Artist artist4 = new Artist("Bullet For My Valentine", LocalDate.of(2001, 1, 1), "Spain", appUser, "Massa info om artisten...");
            artistRepo.save(artist4);

            Album album4 = new Album("Fever", LocalDate.of(2010, 2, 2), artist4, appUser, "Massa info om albumet...");
            album4.setArtist(artist4);
            album4.setImagePath("/images/fever.jpg");
            albumRepo.save(album4);

            Songs songs4 = new Songs("Fourth song", 149, artist4, album4, appUser);
            album4.setArtist(artist4);
            album4.setAlbumName(album4.getAlbumName());
            songsRepo.save(songs4);
        };
    }
}
