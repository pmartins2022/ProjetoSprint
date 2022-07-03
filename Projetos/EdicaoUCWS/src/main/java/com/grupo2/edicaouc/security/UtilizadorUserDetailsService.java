package com.grupo2.edicaouc.security;

import com.grupo2.edicaouc.dto.UtilizadorDTO;
import com.grupo2.edicaouc.repository.rest.UtilizadorRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Classe que comunica com UtilizadorRepository e InMemoryUserDetailsManager e permite a autenticação do Utilizador aquando feito login
 */
@Component
public class UtilizadorUserDetailsService implements UserDetailsService
{
    /**
     * Objeto do tipo UtilizadorRestRepository a ser utilizador por UtilizadorUserDetailsService
     */
    @Autowired
    private UtilizadorRestRepository utilizadorRepository;

    /**
     * Chamado automaticamente pelo Spring. Iniciar a autenticacao ao nosso servico, fornecendo o username.
     * @param username o username a autenticar
     * @return informacao do utilizador
     * @throws UsernameNotFoundException se nao existe esse utilizador
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UtilizadorDTO utilizador = null;

        try
        {
            utilizador = utilizadorRepository.findByUsername(username);
        }
        catch (Exception e)
        {
            throw new UsernameNotFoundException("Utilizador não encontrado: "+username);
        }

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(utilizador.getTipoUtilizador());

        UserDetails userDetails = new User(username, utilizador.getPassword(), authorities);

        LoginContext.setCurrentUser(utilizador);

        return userDetails;
    }


}