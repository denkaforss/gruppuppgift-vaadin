package com.example.gruppuppgiftvaadin.frontend.views;

import com.example.gruppuppgiftvaadin.backend.security.PrincipalUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class Header extends AppLayout {

    public Header() {
        DrawerToggle toggle = new DrawerToggle();
        Image logo = new Image("/images/mythictunes.png", "MythicTunes");
        logo.setHeight("40px");

        addToNavbar(toggle, logo);

        Button logoutButton = new Button("Logout", evt -> PrincipalUtil.logout());
        Button loginButton = new Button("Login", evt ->
                UI.getCurrent().navigate(LoginView.class));

        if (PrincipalUtil.isLoggedIn()) {
            addToNavbar(logoutButton);
        } else {
            addToNavbar(loginButton);
        }

        RouterLink albumView = new RouterLink("Album View",AlbumView.class );
        RouterLink managePostLink = new RouterLink("Manage posts",ManagePostView.class );

        Tabs tabs = new Tabs(new Tab(albumView), new Tab(managePostLink));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);

        addToDrawer(tabs);

        setDrawerOpened(false);
        setPrimarySection(Section.DRAWER);
    }
}
