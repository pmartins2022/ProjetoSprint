package com.pp.app.security;

import com.pp.app.dto.UtilizadorDTO;
import com.pp.app.model.TipoUtilizador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.pp.app.repository.rest.UtilizadorRestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UtilizadorUserDetailsService implements UserDetailsService
{
    @Autowired
    private UtilizadorRestRepository utilizadorRepository;

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

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (utilizador.getTipoUtilizador() == TipoUtilizador.ADMINISTRADOR)
        {
            authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        }
        else if (utilizador.getTipoUtilizador() == TipoUtilizador.DOCENTE)
        {
            authorities = AuthorityUtils.createAuthorityList("ROLE_DOCENTE");
        }
        else if (utilizador.getTipoUtilizador() == TipoUtilizador.ALUNO)
        {
            authorities = AuthorityUtils.createAuthorityList("ROLE_ALUNO");
        }

        UserDetails userDetails = new User(username, utilizador.getPassword(), authorities);

        return userDetails;
    }
}