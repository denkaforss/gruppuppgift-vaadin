package com.example.gruppuppgiftvaadin.frontend.components;

import com.example.gruppuppgiftvaadin.backend.entities.Album;
import com.example.gruppuppgiftvaadin.frontend.views.AlbumView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AlbumDetails extends VerticalLayout {
    private AlbumView albumView;

    private Button close = new Button("Close");

    private Image albumImage;
    private H2 albumTitle;
    private Paragraph albumDescription;
    private H2 artistName;
    private Paragraph artistDescription;

    public AlbumDetails(Album album, AlbumView albumView) {
        this.setVisible(true);
        this.setWidth("30%");

        albumImage = new Image(album.getImagePath(), "Album image");
        albumTitle = new H2(album.getAlbumName());
        albumDescription = new Paragraph(album.getDetailedInfo());
        artistName = new H2(album.getArtist().getArtistName());
        artistDescription = new Paragraph(album.getArtist().getDetailedInfo());
        albumImage.setWidth("80%");
        close.addClickListener(click -> {
            albumView.remove(this);
        });

        add(close, albumImage, albumTitle, albumDescription, artistName, artistDescription);
    }

}