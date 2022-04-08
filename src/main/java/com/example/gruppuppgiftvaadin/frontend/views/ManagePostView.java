package com.example.gruppuppgiftvaadin.frontend.views;

import com.example.gruppuppgiftvaadin.backend.entities.Album;
import com.example.gruppuppgiftvaadin.backend.entities.AppUser;
import com.example.gruppuppgiftvaadin.backend.repositories.AppUserRepo;
import com.example.gruppuppgiftvaadin.backend.security.PrincipalUtil;
import com.example.gruppuppgiftvaadin.backend.services.AlbumService;
import com.example.gruppuppgiftvaadin.components.BlogForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import javax.annotation.security.PermitAll;

@Route(value = "/manageposts",layout = Header.class)
@PermitAll
/*@AnonymousAllowed*/
public class ManagePostView extends VerticalLayout {

    Grid<Album> grid = new Grid<>(Album.class,false);
    AlbumService albumService;
    BlogForm blogForm;
    AppUserRepo appUserRepo;

    public ManagePostView(AlbumService albumService, AppUserRepo appUserRepo) {
        this.albumService = albumService;
        this.appUserRepo = appUserRepo;
        this.blogForm = new BlogForm(albumService,this );
        setAlignItems(Alignment.CENTER);

        grid.setItems(albumService.findPostByAuthorUsername(PrincipalUtil.getPrincipalName()));
        grid.setWidthFull();

        grid.addComponentColumn(album -> {
            Button button = new Button(new Icon(VaadinIcon.CLOSE), evt -> {
                Notification.show(album.getAlbumName());
                albumService.deleteAlbum(album);
                updateItems();

            });

            button.addThemeVariants(
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_PRIMARY,
                    ButtonVariant.LUMO_SMALL
            );

            return button;
        });


/*        grid.addColumn(Album::getId).setHeader("id").setSortable(true).setResizable(true);
        grid.addColumn(Album::getAlbumName).setHeader("Title");
        grid.addColumn(Album::getArtist).setHeader("Name");
        grid.addColumn(Album::getReleaseYear).setHeader("Date");*/

        grid.asSingleSelect().addValueChangeListener(evt -> {
         blogForm.setAlbum(evt.getValue());
        });


        HorizontalLayout mainContent = new HorizontalLayout(grid,blogForm);
        mainContent.setSizeFull();

        Button button = new Button("Add new album", evt -> {
            Dialog dialog = new Dialog();
            BlogForm dialogForm = new BlogForm(albumService, this);

            Album album = new Album();
            AppUser currentUser = appUserRepo.findByUsername(PrincipalUtil.getPrincipalName()).orElseThrow();
            album.setAppUser(currentUser);

            dialogForm.setAlbum(album);

            dialog.add(dialogForm);
            dialog.open();

        });

        add(mainContent, button);

    }
    public void updateItems(){
        grid.setItems(albumService.findPostByAuthorUsername(PrincipalUtil.getPrincipalName()));
    }
}