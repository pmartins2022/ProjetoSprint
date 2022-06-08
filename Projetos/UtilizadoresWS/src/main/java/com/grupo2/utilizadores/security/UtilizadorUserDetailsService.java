package com.grupo2.utilizadores.security;

import com.grupo2.utilizadores.dto.UtilizadorAuthDTO;
import com.grupo2.utilizadores.dto.mapper.UtilizadorDTOMapper;
import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.repository.UtilizadorRepository;
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

        return new User(username, utilizador.get().getPassword() , authorities);
    }
}
