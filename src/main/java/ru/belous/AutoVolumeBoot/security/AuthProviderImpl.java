package ru.belous.AutoVolumeBoot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.belous.AutoVolumeBoot.services.PeopleService;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {
    private final PeopleService peopleService;

    @Autowired
    public AuthProviderImpl(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails userDetails = peopleService.loadUserByUsername(username);
        String pass = authentication.getCredentials().toString();
        if(!pass.equals(userDetails.getPassword())) throw new BadCredentialsException("not right pass");

       return new UsernamePasswordAuthenticationToken(userDetails,pass, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
