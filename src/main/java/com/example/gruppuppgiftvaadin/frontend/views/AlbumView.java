package com.example.gruppuppgiftvaadin.frontend.views;

import com.example.gruppuppgiftvaadin.backend.services.AlbumService;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Album View | MythicTunes")
@Route(value = "/", layout = Header.class)
public class AlbumView extends FlexLayout {

    AlbumService albumService;

    public AlbumView(AlbumService albumService) {
        this.albumService = albumService;

        albumService.findAll().forEach(album -> {
            VerticalLayout layout = new VerticalLayout();
            H2 albumTitle = new H2(album.getAlbumName());
            Image albumImage = new Image(album.getImagePath(), "Album Image");
            albumImage.setHeight("250px");
            albumImage.setWidth("250px");
            H4 artistName = new H4(album.getArtist().getArtistName());

            layout.setSizeUndefined();
            layout.setAlignItems(Alignment.CENTER);
            layout.add(albumTitle, albumImage, artistName);

            this.setFlexDirection(FlexDirection.ROW);
            this.setFlexWrap(FlexWrap.WRAP);
            this.setFlexGrow(1, layout);
            add(layout);
        });
    }
}
