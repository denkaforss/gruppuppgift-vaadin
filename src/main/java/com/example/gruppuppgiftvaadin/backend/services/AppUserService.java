package com.example.gruppuppgiftvaadin.backend.services;

import com.example.gruppuppgiftvaadin.backend.entities.AppUser;
import com.example.gruppuppgiftvaadin.backend.repositories.AppUserRepo;
import com.vaadin.flow.server.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

public class AppUserService {

    @Autowired
    AppUserRepo appUserRepo;

    public AppUser storeUser(AppUser appUser) {
        return appUserRepo.save(appUser);
    }
}
