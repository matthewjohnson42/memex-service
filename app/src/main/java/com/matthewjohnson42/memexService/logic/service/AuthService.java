package com.matthewjohnson42.memexService.logic.service;

import com.matthewjohnson42.memexService.data.dto.AuthRequestDto;
import com.matthewjohnson42.memexService.data.dto.AuthResponseDto;
import com.nimbusds.jose.JOSEException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import javax.security.auth.login.CredentialException;
import java.util.Collection;

/**
 * Abstract AuthService to account for multiple run environments
 */
public abstract class AuthService {
    abstract public AuthResponseDto processAuthenticationRequest(AuthRequestDto authRequestDto) throws JOSEException, CredentialException;

    abstract public Collection<GrantedAuthority> getAuthorities(Jwt jwt);
}
