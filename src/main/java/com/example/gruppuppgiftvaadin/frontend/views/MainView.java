package com.example.gruppuppgiftvaadin.frontend.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends AppLayout {

    public MainView() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("MythicTunes");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-1")
                .set("margin", "0");

        Tab landingPage = new Tab("Landing Page");
        Tab gridView = new Tab("Grid view");

        Tabs tabs = new Tabs();
        tabs.add(landingPage, gridView);

        addToDrawer(tabs);
        addToNavbar(toggle, title);
    }


}
