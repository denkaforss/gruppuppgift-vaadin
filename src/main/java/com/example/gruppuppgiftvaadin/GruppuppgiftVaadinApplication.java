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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
/*@Theme(themeClass = Lumo.class, variant = Lumo.DARK)*/
@Theme(value = "custom-theme", variant = "dark")
public class GruppuppgiftVaadinApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(GruppuppgiftVaadinApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ArtistRepo artistRepo, AlbumRepo albumRepo, SongsRepo songsRepo, AppUserRepo appUserRepo) {
        return args -> {
            AppUser appUser = new AppUser("admin", "password");
            appUserRepo.save(appUser);

            Artist avantasia = new Artist("Avantasia", LocalDate.of(1999, 4, 14), "Germany", appUser, "Avantasia är ett power metalprojekt skapat av Edguys sångare Tobias Sammet. Hittills har sju skivor givits ut varav fem är album The Metal Opera Part I, The Metal Opera Part II, Lost In Space part I EP, Lost In Space Part II EP");
            artistRepo.save(avantasia);

            Album album = new Album("The Wicked Symphony", LocalDate.of(2010, 4, 3), avantasia, appUser, "The Wicked Symphony är det fjärde power metalalbumet skapat av Avantasia, ett av Tobias Sammets projekt. På skivan medverkar flertalet musiker från diverse olika band, exempelvis Tim \"Ripper\" Owens som tidigare sjöng i Judas Priest och Iced Earth.");
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

            Artist artist2 = new Artist("Miss May I", LocalDate.of(2001, 1, 1), "denmark", appUser, "Miss May I is an American metalcore band from Troy, Ohio. Formed in 2007,[3] they signed to Rise Records in 2008 and released their debut album, Apologies Are for the Weak through the label while the members were still attending high school.");
            artistRepo.save(artist2);

            Album album2 = new Album("Shadows Inside", LocalDate.of(2002, 2, 2), artist2, appUser, "Shadows Inside is the sixth studio album from American metalcore band Miss May I. Released on 2 June 2017, this was the band's first release on SharpTone Records after leaving Rise Records.[1]");
            album2.setArtist(artist2);
            album2.setImagePath("/images/shadowsinside.jpg");
            albumRepo.save(album2);

            Songs songs2 = new Songs("Shadows Inside", 236, artist2, album2, appUser);
            album2.setArtist(artist2);
            album2.setAlbumName(album2.getAlbumName());
            songsRepo.save(songs2);


            Album album3 = new Album("Moonglow", LocalDate.of(2019, 2, 2), avantasia, appUser, "Moonglow is the eighth full-length album by Tobias Sammet's German metal opera project Avantasia. It was released on 15 February 2019 through Nuclear Blast.");
            album3.setArtist(avantasia);
            album3.setImagePath("/images/moonglow.png");
            albumRepo.save(album3);

            Songs songs3 = new Songs("Third song", 149, avantasia, album3, appUser);
            album3.setArtist(avantasia);
            album3.setAlbumName(album3.getAlbumName());
            songsRepo.save(songs3);

            Artist artist4 = new Artist("Bullet For My Valentine", LocalDate.of(2001, 1, 1), "Spain", appUser, "Bullet for My Valentine är ett walesiskt heavy metalband från Bridgend, bildade år 1998. Bandet består av Matthew Tuck (sång, kompgitarr), Michael Paget (sologitarr, bakgrundssång), Michael Thomas (trummor) och Jamie Mathias (bas).");
            artistRepo.save(artist4);

            Album album4 = new Album("Fever", LocalDate.of(2010, 2, 2), artist4, appUser, "Bandets fjärde studioalbum, Fever, utgavs den 27 april 2010 och nådde plats nummer 3 på Billboard 200, vilket gjorde det till bandets bäst placerade album på Billboards topplista; detta personbästa står sig än i dag (2015).");
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
