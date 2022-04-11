package com.example.gruppuppgiftvaadin.frontend.views;

import com.example.gruppuppgiftvaadin.backend.entities.Album;
import com.example.gruppuppgiftvaadin.backend.entities.AppUser;
import com.example.gruppuppgiftvaadin.backend.repositories.AppUserRepo;
import com.example.gruppuppgiftvaadin.backend.security.PrincipalUtil;
import com.example.gruppuppgiftvaadin.backend.services.AlbumService;
import com.example.gruppuppgiftvaadin.backend.services.ArtistService;
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
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Route(value = "/manageposts",layout = Header.class)
@PermitAll
/*@AnonymousAllowed*/
public class ManagePostView extends VerticalLayout {

    Grid<Album> grid = new Grid<>(Album.class,false);
    AlbumService albumService;
    ArtistService artistService;
    BlogForm blogForm;
    AppUserRepo appUserRepo;


    public ManagePostView(AlbumService albumService, AppUserRepo appUserRepo, ArtistService artistService) {
        this.albumService = albumService;
        this.appUserRepo = appUserRepo;
        this.blogForm = new BlogForm(albumService,artistService,this);
        setAlignItems(Alignment.CENTER);

        grid.setItems(albumService.findPostByAuthorUsername(PrincipalUtil.getPrincipalName()));
        grid.setWidthFull();

        grid.addComponentColumn(album -> {
            Button button = new Button(new Icon(VaadinIcon.TRASH), evt -> {
                Notification.show(album.getAlbumName() + " deleted");
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
        grid.addColumn(Album::getAlbumName).setHeader("Album name:");
        grid.addColumn(Album::getArtistName).setHeader("Artist name:");
        grid.addColumn(Album::getReleaseYear).setHeader("Date of release:");

        grid.asSingleSelect().addValueChangeListener(evt -> {
         blogForm.setAlbum(evt.getValue());
        });


        HorizontalLayout mainContent = new HorizontalLayout(grid,blogForm);
        mainContent.setSizeFull();

        Button button = new Button("Add new album", evt -> {
            Dialog dialog = new Dialog();
            BlogForm dialogForm = new BlogForm(albumService, artistService, this);

            Album album = new Album();
            AppUser currentUser = appUserRepo.findByUsername(PrincipalUtil.getPrincipalName()).orElseThrow();
            album.setAppUser(currentUser);

            FileBuffer fileBuffer = new FileBuffer();
            Upload upload = new Upload(fileBuffer);

            upload.addSucceededListener(succeededEvent -> {
                InputStream fileData = fileBuffer.getInputStream();
                String fileName = succeededEvent.getFileName();
                album.setImagePath("/images/" + fileName);
                BufferedImage bufferedImage;
                try {
                    bufferedImage = ImageIO.read(fileData);
                    ImageIO.write(bufferedImage, "jpg", new File("src/main/resources/META-INF/resources/images/" + fileName));
                    fileData.close();
                } catch (IOException e) {
                    System.out.println("An error occurred");
                    e.printStackTrace();
                }

            });

            dialogForm.setAlbum(album);
            dialog.add(dialogForm, upload);
            dialog.open();
        });

        add(mainContent, button);

    }
    public void updateItems(){
        grid.setItems(albumService.findPostByAuthorUsername(PrincipalUtil.getPrincipalName()));
    }
}
