package com.example.gruppuppgiftvaadin.frontend.views;

import com.example.gruppuppgiftvaadin.backend.security.PrincipalUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

public class Header extends AppLayout {

    public Header() {
        DrawerToggle toggle = new DrawerToggle();

        Image logo = new Image("/images/mythictunes.png", "MythicTunes");
        logo.setHeight("40px");

        H1 title = new H1("MythicTunes");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        Tab artistView = new Tab("Artist View");

        Tabs tabs = new Tabs(artistView);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);

        Button loginButton = new Button("Login", evt ->
                UI.getCurrent().navigate(LoginView.class));

        Button logoutButton = new Button("Logout", evt -> PrincipalUtil.logout());

        addToDrawer(tabs);
        addToNavbar(toggle, logo);

        if (PrincipalUtil.isLoggedIn()) {
            addToNavbar(logoutButton);
        } else {
            addToNavbar(loginButton);
        }
    }
}
