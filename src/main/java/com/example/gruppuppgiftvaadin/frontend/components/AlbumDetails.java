package com.example.gruppuppgiftvaadin.frontend.components;

import com.example.gruppuppgiftvaadin.backend.entities.Album;
import com.example.gruppuppgiftvaadin.backend.entities.AppUser;
import com.example.gruppuppgiftvaadin.backend.entities.Artist;
import com.example.gruppuppgiftvaadin.backend.repositories.AlbumRepo;
import com.example.gruppuppgiftvaadin.backend.repositories.AppUserRepo;
import com.example.gruppuppgiftvaadin.backend.repositories.ArtistRepo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class AlbumDetails extends VerticalLayout {


    Button close = new Button("Close");

    Image albumImage;
    H2 albumTitle;
    Paragraph albumDescription;
    H2 artistName;
    Paragraph artistDescription;


    public AlbumDetails(Album album) {
        this.setVisible(true);

        albumImage = new Image(album.getImagePath(), "Album image");
        albumTitle = new H2(album.getAlbumName());
        albumDescription = new Paragraph(album.getDetailedInfo());
        artistName = new H2(album.getArtist().getArtistName());
        artistDescription = new Paragraph(album.getArtist().getDetailedInfo());
        albumImage.setWidth("80%");
        close.addClickListener(click -> {
            this.setVisible(false);
        });

        add(
                close,
                albumImage,
                albumTitle,
                albumDescription,
                artistName,
                artistDescription
                );
    }

}
