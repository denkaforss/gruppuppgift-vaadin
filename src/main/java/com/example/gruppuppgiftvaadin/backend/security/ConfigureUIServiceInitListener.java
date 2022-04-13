package com.example.gruppuppgiftvaadin.backend.security;

import com.example.gruppuppgiftvaadin.frontend.views.LoginView;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.NotFoundException;

public class ConfigureUIServiceInitListener {
    private void beforeEnter(BeforeEnterEvent event) {
        if(!SecurityUtils.isAccessGranted(event.getNavigationTarget()))
            if (SecurityUtils.isUserLoggedIn()) {
                event.rerouteToError(NotFoundException.class);
            } else {
                event.rerouteTo(LoginView.class);
            }
    }
}
