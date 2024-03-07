package com.sparta.back_office.security;

import com.sparta.back_office.model.entity.Manager;
import com.sparta.back_office.model.enums.Auth;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final Manager manager;

    public UserDetailsImpl(Manager manager) {
        this.manager = manager;
    }

    public Manager getUser() {
        return manager;
    }

    @Override
    public String getPassword() {
        return manager.getPw();
    }

    @Override
    public String getUsername() {
        return manager.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Auth role = manager.getAuth();
        String authority = role.getAuthority();

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}