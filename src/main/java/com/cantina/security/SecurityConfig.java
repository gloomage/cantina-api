package com.cantina.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public void configure(HttpSecurity httpSec) throws Exception {

        httpSec.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET,"/produtos").permitAll()
                .anyRequest().authenticated().and().cors();

    }
}
