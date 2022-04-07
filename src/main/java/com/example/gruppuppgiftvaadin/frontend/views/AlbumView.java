package com.example.gruppuppgiftvaadin.frontend.views;

import com.example.gruppuppgiftvaadin.backend.services.AlbumService;
import com.example.gruppuppgiftvaadin.frontend.components.AlbumDetails;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Album View | MythicTunes")
@Route(value = "/", layout = Header.class)
@AnonymousAllowed
public class AlbumView extends FlexLayout {

    AlbumService albumService;
    private AlbumDetails albumDetails;
    private FlexLayout content;

    public AlbumView(AlbumService albumService) {
        this.albumService = albumService;
        content = renderAlbum();
        content.setWidthFull();
/*        albumDetails.setMaxWidth("30%");*/
        this.setSizeFull();

        add(content /*albumDetails*/);
    }

    private FlexLayout renderAlbum() {
        FlexLayout layout = new FlexLayout();
        albumService.findAll().forEach(album -> {
            VerticalLayout albumLayout = new VerticalLayout();

            H2 albumTitle = new H2(album.getAlbumName());
            H3 artistName = new H3(album.getArtist().getArtistName());

            Image albumImage = new Image(album.getImagePath(), "Album Image");
            albumImage.setHeight("250px");
            albumImage.setWidth("250px");

            albumDetails = new AlbumDetails(album, this);
            albumLayout.setSizeUndefined();
            albumLayout.setAlignItems(Alignment.CENTER);
            albumLayout.add(albumTitle, albumImage, artistName);
            albumLayout.addClickListener(click -> {
                if (!albumDetails.isVisible()){
                this.add(albumDetails = new AlbumDetails(album, this));
            } else {
                    albumDetails.setVisible(false);
                }
            });

            layout.setFlexDirection(FlexDirection.ROW);
            layout.setFlexWrap(FlexWrap.WRAP);
            layout.setFlexBasis("325px", albumLayout);
            layout.setFlexGrow(1, albumLayout);

            layout.add(albumLayout);
        });
        return layout;
    }
}
