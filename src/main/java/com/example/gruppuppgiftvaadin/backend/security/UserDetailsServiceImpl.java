package com.example.gruppuppgiftvaadin.backend.security;

import com.example.gruppuppgiftvaadin.backend.entities.AppUser;
import com.example.gruppuppgiftvaadin.backend.repositories.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AppUserRepo appUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser = appUserRepo.findByUsername(username).orElseThrow();

        return new User(appUser.getUsername(), appUser.getPassword(), List.of());

    }
}