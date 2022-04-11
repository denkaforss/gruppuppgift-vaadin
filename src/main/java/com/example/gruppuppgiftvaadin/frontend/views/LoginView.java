package com.example.gruppuppgiftvaadin.frontend.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route("/login")
public class LoginView extends Div implements BeforeEnterObserver {

    LoginOverlay loginOverlay = new LoginOverlay();

    public LoginView(){
        addClassName("login");
        loginOverlay.setTitle("MythicTunes");
        loginOverlay.setDescription("Här finns bara schyssta plattor");
        loginOverlay.setOpened(true);
        loginOverlay.setAction("login");

        add(loginOverlay);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (event
                .getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")){
            loginOverlay.setError(true);
        }
    }
}
