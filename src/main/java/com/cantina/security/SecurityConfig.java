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
                /* PRODUTO */
                .antMatchers(HttpMethod.GET,"/produtos").permitAll()
                .antMatchers(HttpMethod.GET,"/produtos/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/produtos").permitAll()
                .antMatchers(HttpMethod.PUT,"/produtos/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/produtos/{id}").permitAll()
                /* CLIENTE */
                .antMatchers(HttpMethod.GET,"/parceiros").permitAll()
                .antMatchers(HttpMethod.GET,"/parceiros/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/parceiros").permitAll()
                .antMatchers(HttpMethod.PUT,"/parceiros/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/parceiros/{id}").permitAll()
                .anyRequest().authenticated().and().cors();

    }
}
