package com.example.gruppuppgiftvaadin.frontend.views;

import com.example.gruppuppgiftvaadin.backend.entities.Artist;
import com.example.gruppuppgiftvaadin.backend.services.AlbumService;
import com.example.gruppuppgiftvaadin.backend.services.ArtistService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Artist View | MythicTunes")
@Route(value = "/artist", layout = Header.class)
@AnonymousAllowed
public class ArtistView extends VerticalLayout {

    ArtistService artistService;
    Component flexLayout;

    public ArtistView(ArtistService artistService) {
        this.artistService = artistService;
        this.setSizeFull();
        this.flexLayout = createArtistView();

        add(flexLayout);
    }

    private Component createArtistView() {
        FlexLayout contentLayout = new FlexLayout();

        artistService.findAll().forEach(artist -> {
            VerticalLayout artistLayout = new VerticalLayout();
            H2 artistName = new H2(artist.getArtistName());

            Image artistImage = new Image(artist.getImagePath(), "Artist Image");
            artistImage.setHeight("250px");
            artistImage.setWidth("250px");
            artistImage.getStyle().set("border-radius", "5px");

            artistLayout.setSizeUndefined();
            artistLayout.setAlignItems(Alignment.CENTER);
            artistLayout.add(artistName, artistImage);

            artistLayout.addClickListener(click -> {
                Dialog dialog = new Dialog();
                VerticalLayout vl = createDialog(artist, dialog);
                dialog.add(vl);
                dialog.setModal(true);
                dialog.setMinWidth("900px");
                dialog.setWidth("60vw");
                dialog.open();
            });

            contentLayout.setFlexBasis("325px", artistLayout);
            contentLayout.setFlexGrow(1, artistLayout);
            contentLayout.add(artistLayout);
        });


        contentLayout.setFlexDirection(FlexLayout.FlexDirection.ROW);
        contentLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        contentLayout.setWidthFull();

        return contentLayout;
    }

    private static VerticalLayout createDialog(Artist artist, Dialog dialog) {
        Button close = new Button("Close");
        close.addClickListener(click -> dialog.close());
        close.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Image artistImage = new Image(artist.getImagePath(), "Artist image");
        artistImage.setWidth("100%");
        artistImage.getStyle().set("border-radius", "5px");

        H2 artistName = new H2(artist.getArtistName());
        Paragraph artistDescription = new Paragraph(artist.getDetailedInfo());

        VerticalLayout vl1 = new VerticalLayout(artistName, artistDescription);
        VerticalLayout vl2 = new VerticalLayout(artistImage);
        HorizontalLayout hl = new HorizontalLayout();

        hl.add(vl2, vl1);
        hl.setDefaultVerticalComponentAlignment(Alignment.END);

        VerticalLayout layout = new VerticalLayout(close, hl);
        layout.setDefaultHorizontalComponentAlignment(Alignment.END);

        return layout;
    }
}
