package com.example.gruppuppgiftvaadin.frontend.views;

import com.example.gruppuppgiftvaadin.backend.security.PrincipalUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

public class Header extends AppLayout {

    HorizontalLayout navbar = new HorizontalLayout();

    public Header() {
        configureNavbar();

        Tab artistView = new Tab("Artist View");

        Tabs tabs = new Tabs(artistView);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);

        addToDrawer(tabs);
        addToNavbar(navbar);
    }

    private void configureNavbar() {
        DrawerToggle toggle = new DrawerToggle();

        Image logo = new Image("/images/mythictunes.png", "MythicTunes");

        Button logoutButton = new Button("Logout", evt -> PrincipalUtil.logout());
        Button loginButton = new Button("Login", evt ->
                UI.getCurrent().navigate(LoginView.class));

        navbar.add(toggle, logo);

        if (PrincipalUtil.isLoggedIn()) {
            navbar.add(logoutButton);
        } else {
            navbar.add(loginButton);
        }

        navbar.setWidthFull();
        navbar.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        navbar.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        navbar.setPadding(true);
    }


}
