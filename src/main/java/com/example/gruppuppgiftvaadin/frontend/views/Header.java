package com.example.gruppuppgiftvaadin.frontend.views;

import com.example.gruppuppgiftvaadin.backend.security.PrincipalUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

import java.util.Locale;

public class Header extends AppLayout {

    public Header() {
        DrawerToggle toggle = new DrawerToggle();
        Image logo = new Image("/images/mythictunes.png", "MythicTunes");
        logo.setHeight("40px");

        addToNavbar(toggle, logo);

        Button logoutButton = new Button("Logout", evt -> PrincipalUtil.logout());
        Button loginButton = new Button("Login", evt ->
                UI.getCurrent().navigate(LoginView.class));
        Button registerButton = new Button("Register", evt -> UI.getCurrent().navigate(RegisterView.class));

        RouterLink albumViewLink = new RouterLink("Album View",AlbumView.class );
        RouterLink artistViewLink = new RouterLink("Artist View",ArtistView.class );
        RouterLink managePostLink = new RouterLink("Manage Albums", ManageAlbum.class );
        RouterLink manageArtists = new RouterLink("Manage Artists", ManageArtist.class);

        Avatar loggedInUser = new Avatar(PrincipalUtil.getPrincipalName().toUpperCase(Locale.ROOT));

        Tabs tabs = new Tabs(new Tab(albumViewLink), new Tab(artistViewLink));

        HorizontalLayout userLayout = new HorizontalLayout(loggedInUser, logoutButton);
        userLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        if (PrincipalUtil.isLoggedIn()) {
            addToNavbar(new HorizontalLayout(userLayout));
            remove(registerButton);
            tabs.add(new Tab(managePostLink), new Tab(manageArtists));
        } else {
            addToNavbar(new HorizontalLayout(loginButton, registerButton));
        }



        tabs.setOrientation(Tabs.Orientation.VERTICAL);

        addToDrawer(tabs);

        setDrawerOpened(false);
        setPrimarySection(Section.DRAWER);
    }
}
