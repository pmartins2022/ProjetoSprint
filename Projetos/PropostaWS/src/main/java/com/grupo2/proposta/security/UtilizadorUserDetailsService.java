package com.grupo2.proposta.security;

import com.grupo2.proposta.dto.UtilizadorAuthDTO;
import com.grupo2.proposta.repository.rest.UtilizadorRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que comunica com UtilizadorRestRepository e permite a autenticação do Utilizador aquando feito login
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
     * {{@code @Inherit}}
     * @param username username do Utilizador
     * @return UserDetails
     * @throws UsernameNotFoundException  {{@code @Inherit}}
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UtilizadorAuthDTO utilizador = null;

        try
        {
            utilizador = utilizadorRepository.findByUsername(username);
        }
        catch (Exception e)
        {
            throw new UsernameNotFoundException("Utilizador não encontrado: "+username);
        }

        List<GrantedAuthority> authorities =
                AuthorityUtils.createAuthorityList(utilizador.getTipoUtilizador());

        UserDetails userDetails = new User(username, utilizador.getPassword(), authorities);

        LoginContext.setCurrent(utilizador);

        return userDetails;
    }
}