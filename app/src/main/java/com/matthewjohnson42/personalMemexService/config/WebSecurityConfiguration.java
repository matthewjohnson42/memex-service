package com.matthewjohnson42.personalMemexService.config;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.matthewjohnson42.personalMemexService.web.filter.auth.CustomJwtGrantedAuthoritiesConverter;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Profile("prod")
@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${http.oauth2.encKeySecret}")
    private String encryptionKeySecret;

    @Autowired
    CustomJwtGrantedAuthoritiesConverter customJwtGrantedAuthConverter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(customJwtGrantedAuthConverter);
        httpSecurity
                .authorizeRequests()
                .antMatchers("/api/v0/auth").permitAll()
                .antMatchers("/**").authenticated()
                .and().csrf().ignoringAntMatchers("/api/v0/auth")
                .and().cors()
//                .and().requiresChannel().anyRequest().requiresSecure() // this can be done using Nginx
                .and().oauth2ResourceServer().jwt(
                    jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter)
                );
    }

    @Bean
    public MACSigner macSigner(SecretKey secretKey) throws KeyLengthException {
        return new MACSigner(secretKey);
    }

    @Bean
    public SecretKey secretKey() throws InvalidKeySpecException, NoSuchAlgorithmException {
        // ref: org.bouncycastle.jcajce.provider.digest.SHA256.PBEWithMacKeyFactory
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEwithHmacSHA256");
        char[] secret = encryptionKeySecret.toCharArray();
        int saltByteCt = 32;
        byte[] salt = new byte[saltByteCt];
        for (int i = 0; i < saltByteCt; i++) {
            salt[i] = (byte) Math.floor(Math.random() * 8);
        }
        PBEKeySpec keySpec = new PBEKeySpec(secret, salt, 185000);
        return secretKeyFactory.generateSecret(keySpec);
    }

    // used by oauth2ResourceServer in JWT mode
    // see OAuth2ResourceServerConfigurer
    @Bean
    public JwtDecoder jwtDecoder(SecretKey secretKey) {
        return NimbusJwtDecoder.withSecretKey(
                secretKey
        ).build();
    }

//    @Bean
//    public EncryptionKey encryptionKey() throws GeneralSecurityException {
//        char[] secret = encryptionKeySecret.toCharArray();
//        int[] randomCodePoints = new int[8];
//        double maxCodePointVal = (double) Character.MAX_CODE_POINT;
//        for (int i = 0; i < 8; i++) {
//            randomCodePoints[i] = (int) Math.floor(Math.random() * maxCodePointVal);
//        }
//        String salt = new String(randomCodePoints, 0, 0);
//
//        byte[] encryptionKey = new AesSha2DkCrypto(256).stringToKey(secret, salt, null);
//        byte[] encryptionKey = new byte[32];
//        for (int i = 0; i < 32; i++) {
//            encryptionKey[i] = 0;
//        }
//        // positional argument #2 taken from IANA - Kerberos Encryption Type Numbers
//        SecretKeyFactory.getInstance();
//        return new EncryptionKey(encryptionKey, 20);
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}
