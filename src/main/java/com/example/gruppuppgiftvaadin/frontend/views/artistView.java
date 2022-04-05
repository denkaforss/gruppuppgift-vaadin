package com.example.gruppuppgiftvaadin.frontend.views;

import com.example.gruppuppgiftvaadin.backend.entities.Artist;
import com.example.gruppuppgiftvaadin.backend.services.ArtistService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "/", layout = Header.class)
public class artistView extends VerticalLayout {

    ArtistService artistService;

    public artistView(ArtistService artistService) {
        this.artistService = artistService;

        Grid<Artist> artistGrid = new Grid<>(Artist.class, false);

        artistGrid.setItems(artistService.findAll());
        artistGrid.setWidthFull();

        artistGrid.addColumn(Artist::getArtistName).setHeader("Artist Name");
        artistGrid.addColumn(Artist::getHomeCountry).setHeader("Home Country");
        artistGrid.addColumn(Artist::getStartingYear).setHeader("Starting Year");

        add(artistGrid);
    }


}
