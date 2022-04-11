package com.example.gruppuppgiftvaadin.frontend.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;


@Route("register")
class RegisterView extends Div implements BeforeEnterObserver{

    LoginOverlay registerOverlay = new LoginOverlay();

    public RegisterView() {
        registerOverlay.setTitle("Register  or else!!");
        registerOverlay.setOpened(true);
        registerOverlay.setAction("registration");

    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error")){
            registerOverlay.setError(true);
        }
    }
}
