package com.pp.utilizadorWS.security;

import com.pp.utilizadorWS.dto.UtilizadorAuthDTO;
import com.pp.utilizadorWS.dto.mapper.UtilizadorDTOMapper;
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
    @Autowired
    private UtilizadorDTOMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<Utilizador> utilizador = utilizadorRepository.findByUsername(username);
        if (utilizador.isEmpty())
        {
            throw new UsernameNotFoundException("Utilizador não encontrado: "+username);
        }

        UtilizadorAuthDTO authDTO = mapper.toAuthDTO(utilizador.get());

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(authDTO.getTipoUtilizador().toString());

        return new User(username, utilizador.get().getPassword(), authorities);
    }
}
