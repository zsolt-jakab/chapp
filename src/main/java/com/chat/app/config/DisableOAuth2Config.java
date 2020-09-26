package com.chat.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Profile({"dev"})
public class DisableOAuth2Config {

    @Bean
    public ResourceServerConfigurer resourceServerConfigurer() {
        return new ResourceServerConfigurerAdapter() {
            @Override
            public void configure(HttpSecurity http) throws Exception {
                http
                        .authorizeRequests()
                        .antMatchers("/**").permitAll();
            }
        };
    }
}