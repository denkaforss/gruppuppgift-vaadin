package com.example.gruppuppgiftvaadin.frontend.views;

import com.example.gruppuppgiftvaadin.backend.entities.Album;
import com.example.gruppuppgiftvaadin.backend.services.AlbumService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Album View | MythicTunes")
@Route(value = "/", layout = Header.class)
@AnonymousAllowed
public class AlbumView extends VerticalLayout {

    AlbumService albumService;
    Component flexLayout;

    public AlbumView(AlbumService albumService) {
        this.albumService = albumService;
        this.setSizeFull();
        this.flexLayout = createAlbumView();

        add(flexLayout);
    }

    private Component createAlbumView() {
        FlexLayout contentLayout = new FlexLayout();

        albumService.findAll().forEach(album -> {
            VerticalLayout albumLayout = new VerticalLayout();
            H2 albumTitle = new H2(album.getAlbumName());
            H3 artistName = new H3(album.getArtistName());

            Image albumImage = new Image(album.getImagePath(), "Album Image");
            albumImage.setHeight("250px");
            albumImage.setWidth("250px");

            albumLayout.setSizeUndefined();
            albumLayout.setAlignItems(Alignment.CENTER);
            albumLayout.add(albumTitle, albumImage, artistName);

            albumLayout.addClickListener(click -> {
                Dialog dialog = new Dialog();
                VerticalLayout vl = createDialog(album, dialog);
                dialog.add(vl);
                dialog.setModal(true);
                dialog.setWidth("50%");
                dialog.open();
            });

            contentLayout.setFlexBasis("325px", albumLayout);
            contentLayout.setFlexGrow(1, albumLayout);
            contentLayout.add(albumLayout);
        });


        contentLayout.setFlexDirection(FlexLayout.FlexDirection.ROW);
        contentLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        contentLayout.setWidthFull();

        return contentLayout;
    }

    private static VerticalLayout createDialog(Album album, Dialog dialog) {
        Button close = new Button("Close");
        close.addClickListener(click -> dialog.close());
        close.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Image albumImage = new Image(album.getImagePath(), "Album image");

        H2 albumTitle = new H2(album.getAlbumName());
        Paragraph albumDescription = new Paragraph(album.getDetailedInfo());
        H2 artistName = new H2(album.getArtist().getArtistName());
        Paragraph artistDescription = new Paragraph(album.getArtist().getDetailedInfo());

        VerticalLayout vl = new VerticalLayout();
        HorizontalLayout hl = new HorizontalLayout();
        vl.add(albumTitle, albumDescription, artistName, artistDescription);
        hl.add(albumImage, vl);

        return new VerticalLayout(hl, close);
    }
}
