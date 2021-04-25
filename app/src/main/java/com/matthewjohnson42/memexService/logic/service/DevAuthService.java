package com.matthewjohnson42.memexService.logic.service;

import com.matthewjohnson42.memexService.data.dto.AuthRequestDto;
import com.matthewjohnson42.memexService.data.dto.AuthResponseDto;
import com.nimbusds.jose.PlainHeader;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.PlainJWT;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Profile("dev")
@Service
public class DevAuthService extends AuthService {
    public AuthResponseDto processAuthenticationRequest(AuthRequestDto authRequestDto) {
        PlainHeader jwtHeader = new PlainHeader();
        JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                .issuer("memex-service")
                .expirationTime(new Date(System.currentTimeMillis() + 86400000))
                .subject(authRequestDto.getUsername()).build();
        PlainJWT jwt = new PlainJWT(jwtHeader, jwtClaims);
        return new AuthResponseDto().setToken(jwt.serialize());
    }

    public Collection<GrantedAuthority> getAuthorities(Jwt jwt) {
        return new HashSet<>();
    }
}
