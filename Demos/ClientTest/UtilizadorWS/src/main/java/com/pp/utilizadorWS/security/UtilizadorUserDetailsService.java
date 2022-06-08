package com.pp.utilizadorWS.security;

import com.pp.utilizadorWS.model.TipoUtilizador;
import com.pp.utilizadorWS.model.Utilizador;
import com.pp.utilizadorWS.repository.jpa.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UtilizadorUserDetailsService implements UserDetailsService
{
    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<Utilizador> utilizador = utilizadorRepository.findByUsername(username);
        if (utilizador.isEmpty())
        {
            throw new UsernameNotFoundException("Utilizador não encontrado: "+username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (utilizador.get().getTipoUtilizador() == TipoUtilizador.ADMINISTRADOR)
        {
            authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        }
        else if (utilizador.get().getTipoUtilizador() == TipoUtilizador.DOCENTE)
        {
            authorities = AuthorityUtils.createAuthorityList("ROLE_DOCENTE");
        }
        else if (utilizador.get().getTipoUtilizador() == TipoUtilizador.ALUNO)
        {
            authorities = AuthorityUtils.createAuthorityList("ROLE_ALUNO");
        }

        UserDetails userDetails = new User(username, utilizador.get().getPassword(), authorities);

        return userDetails;
    }
}
