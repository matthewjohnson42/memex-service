package com.matthewjohnson42.personalMemexService.web.filter.auth;

import com.matthewjohnson42.personalMemexService.logic.service.AuthService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private AuthService authService;

    public CustomJwtGrantedAuthoritiesConverter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        return authService.getAuthorities(jwt);
    }

}
