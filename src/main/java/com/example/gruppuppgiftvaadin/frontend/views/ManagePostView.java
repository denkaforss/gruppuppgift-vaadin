package com.example.gruppuppgiftvaadin.frontend.views;

import com.example.gruppuppgiftvaadin.backend.entities.Album;
import com.example.gruppuppgiftvaadin.backend.services.AlbumService;
import com.example.gruppuppgiftvaadin.components.BlogForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "/manageposts",layout = Header.class)

public class ManagePostView extends VerticalLayout {

    Grid<Album> grid = new Grid<>(Album.class,false);
    AlbumService albumService;
    BlogForm blogForm;



    public ManagePostView() {
        this.albumService = albumService;
        this.blogForm = new BlogForm(albumService,this );
        setAlignItems(Alignment.CENTER);

        grid.addComponentColumn(album -> {
            Button button = new Button(new Icon(VaadinIcon.CLOSE), evt -> {
                Notification.show(album.getAlbumName());
                albumService.deleteById(album.getId());
                updateItems();

            });

            button.addThemeVariants(
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_PRIMARY,
                    ButtonVariant.LUMO_SMALL
            );

            return button;
        });





        grid.addColumn(Album::getId).setHeader("id").setSortable(true).setResizable(true);
        grid.addColumn(Album::getAlbumName).setHeader("Title");
        grid.addColumn(Album::getArtist).setHeader("Name");
        grid.addColumn(Album::getReleaseYear).setHeader("Date");

        grid.asSingleSelect().addValueChangeListener(evt -> {
         blogForm.setAlbum(evt.getValue());
        });


        HorizontalLayout mainContent = new HorizontalLayout(grid,blogForm);
        mainContent.setSizeFull();
        add(mainContent);
    }
    public void updateItems(){
        grid.setItems(albumService.findAll());
    }
}
