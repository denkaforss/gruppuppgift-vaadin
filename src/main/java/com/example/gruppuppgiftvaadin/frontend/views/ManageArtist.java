package com.example.gruppuppgiftvaadin.frontend.views;

import com.example.gruppuppgiftvaadin.backend.entities.AppUser;
import com.example.gruppuppgiftvaadin.backend.entities.Artist;
import com.example.gruppuppgiftvaadin.backend.repositories.AppUserRepo;
import com.example.gruppuppgiftvaadin.backend.security.PrincipalUtil;
import com.example.gruppuppgiftvaadin.backend.services.AlbumService;
import com.example.gruppuppgiftvaadin.backend.services.ArtistService;
import com.example.gruppuppgiftvaadin.frontend.views.components.ArtistForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
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

@Route(value = "/manageartists",layout = Header.class)
@PermitAll
/*@AnonymousAllowed*/
public class ManageArtist extends VerticalLayout {

    Grid<Artist> grid = new Grid<>(Artist.class,false);
    AlbumService albumService;
    ArtistService artistService;
    ArtistForm artistForm;
    AppUserRepo appUserRepo;


    public ManageArtist(AlbumService albumService, AppUserRepo appUserRepo, ArtistService artistService) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.appUserRepo = appUserRepo;
        this.artistForm = new ArtistForm(albumService,artistService,this);
        setAlignItems(Alignment.CENTER);

        grid.setItems(artistService.findAll());
        grid.setWidthFull();

        grid.addComponentColumn(artist -> {
            Button button = new Button(new Icon(VaadinIcon.TRASH), evt -> {
                Dialog warning = new Dialog();
                warning.add(
                        new H1("Warning"),
                        new Paragraph("If you remove an Artist all albums related to this artis will also be removed"),
                        new HorizontalLayout(
                                new Button("Confirm", confirmed -> {
                                    artistService.deleteById(artist.getId());
                                    Notification.show(artist.getArtistName() + " deleted");
                                    updateItems();
                                    warning.close();
                                }),
                                new Button("Close", closed -> warning.close())
                        )
                );
                warning.open();
            });

            button.addThemeVariants(
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_PRIMARY,
                    ButtonVariant.LUMO_SMALL
            );

            return button;
        });


        grid.addColumn(Artist::getId).setHeader("id").setSortable(true).setResizable(true);
        grid.addColumn(Artist::getArtistName).setHeader("Artist name:");
        grid.addColumn(Artist::getHomeCountry).setHeader("Home Country:");
        grid.addColumn(Artist::getStartingYear).setHeader("Date of release:");
        grid.addColumn(Artist::getDetailedInfo).setHeader("Detailed Info");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(evt -> {
         artistForm.setArtist(evt.getValue());
        });


        HorizontalLayout mainContent = new HorizontalLayout(grid, artistForm);
        mainContent.setSizeFull();

        Button button = new Button("Add New Artist", evt -> {
            Dialog dialog = new Dialog();
            ArtistForm dialogForm = new ArtistForm(albumService, artistService, this);

            Artist artist = new Artist();
            AppUser currentUser = appUserRepo.findByUsername(PrincipalUtil.getPrincipalName()).orElseThrow();

            artist.setAppUser(currentUser);

            FileBuffer fileBuffer = new FileBuffer();
            Upload upload = new Upload(fileBuffer);

            upload.addSucceededListener(succeededEvent -> {
                InputStream fileData = fileBuffer.getInputStream();
                String fileName = succeededEvent.getFileName();
                /*album.setImagePath("./frontend/resources/images/" + fileName);*/
                artist.setImagePath("/images/" + fileName);
                BufferedImage bufferedImage;
                try {
                    bufferedImage = ImageIO.read(fileData);
                    /*ImageIO.write(bufferedImage, "jpg", new File("frontend/resources/images/" + fileName));*/
                    ImageIO.write(bufferedImage, "jpg", new File("target/classes/META-INF/resources/images/" + fileName));
                    fileData.close();
                } catch (IOException e) {
                    System.out.println("An error occurred");
                    e.printStackTrace();
                }

            });

            dialogForm.setArtist(artist);
            dialog.add(dialogForm, upload);
            dialog.open();
        });

        add(mainContent, button);

    }
    public void updateItems(){
        grid.setItems(artistService.findAll());
    }
}
