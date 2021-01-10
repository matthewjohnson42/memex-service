package com.matthewjohnson42.personalMemexService.web.controller;

import com.matthewjohnson42.personalMemexService.data.dto.AuthRequestDto;
import com.matthewjohnson42.personalMemexService.data.dto.AuthResponseDto;
import com.matthewjohnson42.personalMemexService.logic.service.AuthService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Profile("prod")
@RestController
@RequestMapping(path="/api/v0/auth")
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(method=RequestMethod.POST)
    public AuthResponseDto authenticate(AuthRequestDto authRequestDto) throws JOSEException {
        SignedJWT signedJWT = authService.getSignedJwt();
        return new AuthResponseDto().setToken(signedJWT.serialize());
    }

}
