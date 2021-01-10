package com.matthewjohnson42.personalMemexService.logic.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Date;

@Profile("prod")
@Service
public class AuthService {

    MACSigner macSigner;

    public AuthService(MACSigner macSigner) throws KeyLengthException {
        this.macSigner = macSigner;
    }

    public SignedJWT getSignedJwt() throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256); // move to common location
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .issuer("personal-memex-service")
                .expirationTime(new Date(System.currentTimeMillis() + 3600000)).build();
        SignedJWT signedJWT = new SignedJWT(jwsHeader, claims);
        signedJWT.sign(macSigner);
        return signedJWT;
    }

}
