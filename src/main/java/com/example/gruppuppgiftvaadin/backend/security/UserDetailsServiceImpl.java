package com.example.gruppuppgiftvaadin.backend.security;

import com.example.gruppuppgiftvaadin.backend.entities.AppUser;
import com.example.gruppuppgiftvaadin.backend.entities.UserRoleToPrivilege;
import com.example.gruppuppgiftvaadin.backend.entities.UserToRole;
import com.example.gruppuppgiftvaadin.backend.repositories.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AppUserRepo appUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser = appUserRepo.findByUsername(username).orElseThrow();
        Set<GrantedAuthority> authorities = new HashSet<>();

        for (UserToRole userToRole : appUser.getUserToRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userToRole.getRole().getRoleName()));
            for (UserRoleToPrivilege userRoleToPrivilege : userToRole.getRole().getUserRoleToPrivileges()) {
                authorities.add(new SimpleGrantedAuthority(userRoleToPrivilege.getPrivilege().getPrivilegeName()));
            }
        }

        return new User(appUser.getUsername(), appUser.getPassword(), authorities);

    }
}
