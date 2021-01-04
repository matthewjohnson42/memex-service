package com.matthewjohnson42.personalMemexService.logic.service;

import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.security.auth.kerberos.EncryptionKey;

@Profile("prod")
@Service
public class AuthService {

//    MACSigner macSigner;
//    EncryptionKey encryptionKey;

//    public AuthService(EncryptionKey encryptionKey) throws KeyLengthException {
//        this.encryptionKey = encryptionKey;
//        macSigner = new MACSigner(encryptionKey);
//    }


}
