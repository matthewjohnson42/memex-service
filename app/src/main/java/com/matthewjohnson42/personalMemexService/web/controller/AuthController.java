package com.matthewjohnson42.personalMemexService.web.controller;

import com.matthewjohnson42.personalMemexService.data.dto.AuthRequestDto;
import com.matthewjohnson42.personalMemexService.data.dto.AuthResponseDto;
import com.nimbusds.jose.crypto.MACSigner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Profile("prod")
@Controller
@RequestMapping(path="/api/v0/auth")
public class AuthController {

//    AuthenticationManager authenticationManager;

//    MACSigner macSigner;

//    public AuthController(
//            AuthenticationManager authenticationManager
//            MACSigner macSigner
//    ) {
//        this.authenticationManager = authenticationManager;
//        this.macSigner = macSigner;
//    }

    @RequestMapping(method=RequestMethod.POST)
    public AuthResponseDto authenticate(AuthRequestDto authRequestDto) {
        return new AuthResponseDto();
    }

}
