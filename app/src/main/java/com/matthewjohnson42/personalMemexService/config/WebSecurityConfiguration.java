package com.matthewjohnson42.personalMemexService.config;

import com.matthewjohnson42.personalMemexService.web.filter.auth.CustomJwtGrantedAuthoritiesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Profile("prod")
@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

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
//                .and().cors() // todo need to configure CORS
//                .and().requiresChannel().anyRequest().requiresSecure() // todo this can be done using Nginx
                .and().oauth2ResourceServer().jwt(
                    jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter)
                );
    }

}
