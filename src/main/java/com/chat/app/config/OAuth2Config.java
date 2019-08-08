package com.chat.app.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableOAuth2Sso
public class OAuth2Config extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
    .and().authorizeRequests()
      .anyRequest()
      .permitAll()
    .and().logout()
      .logoutSuccessUrl("/")
      .permitAll();
  }
}
