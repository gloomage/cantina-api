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

        /* Falta fazer a validação por token pra liberar todos os endpoints
        * Por enquanto vai ficar essa liberação manual
        * */

        httpSec.csrf().disable()
                .authorizeHttpRequests()
                /* PRODUTO */
                .antMatchers(HttpMethod.GET,"/api/v1/publica/prod").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/publica/prod/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/publica/prod").permitAll()
                .antMatchers(HttpMethod.PUT,"/api/v1/publica/prod/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/api/v1/publica/prod/{id}").permitAll()
                /* CLIENTE */
                .antMatchers(HttpMethod.GET,"/parceiros").permitAll()
                .antMatchers(HttpMethod.GET,"/parceiros/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/parceiros").permitAll()
                .antMatchers(HttpMethod.PUT,"/parceiros/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/parceiros/{id}").permitAll()
                /* VENDAS */
                .antMatchers(HttpMethod.GET,"/vendas").permitAll()
                .antMatchers(HttpMethod.GET,"/vendas/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/vendas").permitAll()
                .antMatchers(HttpMethod.PUT,"/vendas/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/vendas/{id}").permitAll()
                /* ITENS - VENDAS */
                .antMatchers(HttpMethod.GET,"/itens-vendas").permitAll()
                .antMatchers(HttpMethod.GET,"/itens-vendas/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/itens-vendas").permitAll()
                .antMatchers(HttpMethod.PUT,"/itens-vendas/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/itens-vendas/{id}").permitAll()
                /* MEIOS */
                .antMatchers(HttpMethod.GET,"/meios").permitAll()
                .antMatchers(HttpMethod.GET,"/meios/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/meios").permitAll()
                .antMatchers(HttpMethod.PUT,"/meios/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/meios/{id}").permitAll()
                /* PAGAMENTOS */
                .antMatchers(HttpMethod.GET,"/pagamentos").permitAll()
                .antMatchers(HttpMethod.GET,"/pagamentos/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/pagamentos").permitAll()
                .antMatchers(HttpMethod.PUT,"/pagamentos/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/pagamentos/{id}").permitAll()
                /* TICKETS */
                .antMatchers(HttpMethod.GET,"/tickets").permitAll()
                .antMatchers(HttpMethod.GET,"/tickets/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/tickets").permitAll()
                .antMatchers(HttpMethod.PUT,"/tickets/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/tickets/{id}").permitAll()
                /* Teste */
                .antMatchers(HttpMethod.GET,"/pais").permitAll()
                .antMatchers(HttpMethod.POST,"/pais").permitAll()


                .anyRequest().authenticated().and().cors();

    }
}
