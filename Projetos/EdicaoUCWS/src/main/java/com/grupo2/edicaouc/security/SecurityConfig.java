package com.grupo2.edicaouc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Classe que extends WebSecurityConfigurerAdapter e configura as permissões dos endpoints.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    /**
     * Objeto do tipo UtilizadorUserDetailsService a ser utilizador pelo SecurityConfig
     */
    @Autowired
    private UtilizadorUserDetailsService userDetailsService;

    /**
     * Chamado automaticamente pelo Spring, configurar o password encoded a usar pelo servico de autenticacao
     * @param auth informacao do gestor de autenticacoes
     * @throws Exception um erro
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * Configurar a seguranca e filtragens dos nossos endpoints do nosso servico
     * @param http informacao dos requests
     * @throws Exception um erro
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/edicaoUC/**", "/anoLetivo/**", "/momento/**", "/uc/**").permitAll()
                .antMatchers("/edicaoUC/criar", "edicaoUC/inscrever/{edicaoUCID}," +
                        "/anoLetivo/criar", "momento/criar", "/uc/criar").authenticated()
                .and()
                .httpBasic()
                .realmName("EdicaoUCWS")
                .and()
                .csrf()
                .disable();
    }
}