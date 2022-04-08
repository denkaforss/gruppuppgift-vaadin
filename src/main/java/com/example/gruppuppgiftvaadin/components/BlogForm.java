package com.example.gruppuppgiftvaadin.components;

import com.example.gruppuppgiftvaadin.backend.entities.Album;
import com.example.gruppuppgiftvaadin.backend.services.AlbumService;
import com.example.gruppuppgiftvaadin.frontend.views.ManagePostView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;


public class BlogForm extends FormLayout {

/*    TextField title = new TextField("Title");*/
    TextField albumName = new TextField("Album Name");
    TextField artistName = new TextField("Artist Name");
    DatePicker releaseYear = new DatePicker("Release year");
    Button saveButton = new Button("Save");


    private void handleSave() {
        Album album = binder.validate().getBinder().getBean();
        if (album.getId() == 0) ;
        albumService.saveAlbum(album);

        setAlbum(null);
        managePostView.updateItems();

        this.getParent().ifPresent(component -> {
            if(component instanceof Dialog){
                ((Dialog) component).close();
            }
        });
    }



    Binder<Album> binder = new BeanValidationBinder<>(Album.class);
    AlbumService albumService;
    ManagePostView managePostView;

    public BlogForm(AlbumService albumService, ManagePostView managePostView){
        this.managePostView = managePostView;
        this.albumService = albumService;
        binder.bindInstanceFields(this);
        setVisible(false);
        saveButton.addClickListener(evt -> handleSave());

        add(/*title,*/albumName,artistName,releaseYear,saveButton);
    }
    public void setAlbum(Album album){
        if (album != null) {
            binder.setBean(album);
            setVisible(true);
        }else{
            setVisible(false);
        }

    }


}
