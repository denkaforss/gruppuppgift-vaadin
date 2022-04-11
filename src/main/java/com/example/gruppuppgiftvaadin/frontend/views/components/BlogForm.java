package com.example.gruppuppgiftvaadin.frontend.views.components;

import com.example.gruppuppgiftvaadin.backend.entities.Album;
import com.example.gruppuppgiftvaadin.backend.entities.Artist;
import com.example.gruppuppgiftvaadin.backend.services.AlbumService;
import com.example.gruppuppgiftvaadin.backend.services.ArtistService;
import com.example.gruppuppgiftvaadin.frontend.views.ManagePostView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;


public class BlogForm extends FormLayout {

    /*    TextField title = new TextField("Title");*/
    TextField albumName = new TextField("Album Name");
    TextField artistName = new TextField("Artist name");
    /*TextField homeCountry = new TextField("Country");*/
    /*DatePicker startingYear = new DatePicker("starting Year");*/
    DatePicker releaseYear = new DatePicker("Release year");
    TextArea detailedInfo = new TextArea("Detailed info");


    Button saveButton = new Button("Save");

    Binder<Album> albumBinder = new BeanValidationBinder<>(Album.class);
    Binder<Artist> artistBinder = new BeanValidationBinder<>(Artist.class);

    AlbumService albumService;
    ArtistService artistService;

    ManagePostView managePostView;

    public BlogForm(AlbumService albumService, ArtistService artistService, ManagePostView managePostView) {
        this.managePostView = managePostView;
        this.albumService = albumService;
        this.artistService = artistService;

        albumBinder.bindInstanceFields(this);
        artistBinder.bindInstanceFields(this);


        setVisible(false);
        saveButton.addClickListener(evt -> handleSave());


        add(albumName, artistName,/* homeCountry, startingYear,*/ releaseYear, detailedInfo, saveButton);
    }

    public void setAlbum(Album album) {
        if (album != null) {
            albumBinder.setBean(album);
            setVisible(true);
        } else {
            setVisible(false);
        }
    }

    private void handleSave() {
        Album album = albumBinder.validate().getBinder().getBean();

        if (album.getId() == 0)
            albumService.saveAlbum(album);
        else
            albumService.updateAlbum(album.getId(), album);

        setAlbum(null);


        managePostView.updateItems();
        this.getParent().ifPresent(component -> {
            if (component instanceof Dialog) {
                ((Dialog) component).close();
            }
        });
    }

}
