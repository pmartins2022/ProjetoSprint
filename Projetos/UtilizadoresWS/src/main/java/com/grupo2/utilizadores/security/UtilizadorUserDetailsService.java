package com.grupo2.utilizadores.security;

import com.grupo2.utilizadores.dto.UtilizadorAuthDTO;
import com.grupo2.utilizadores.dto.mapper.UtilizadorDTOMapper;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.springframework.security.core.userdetails.User.builder;

@Component
public class UtilizadorUserDetailsService implements UserDetailsService
{
    @Autowired
    private UtilizadorRepository utilizadorRepository;
    private static InMemoryUserDetailsManager inMemoryUserDetailsManager;
    @Autowired
    private UtilizadorDTOMapper mapper;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        BCryptPasswordEncoder p = new BCryptPasswordEncoder();
        inMemoryUserDetailsManager = new InMemoryUserDetailsManager(
                builder()
                        .passwordEncoder(p::encode)
                        .username("admin")
                        .password("admin")
                        .roles("ADMIN")
                        .build()/*,
                builder()
                        .passwordEncoder(p::encode)
                        .username("docente1")
                        .password("docente1")
                        .roles("DOCENTE")
                        .build(),
                builder()
                        .passwordEncoder(p::encode)
                        .username("docente2")
                        .password("docente2")
                        .roles("DOCENTE")
                        .build(),
                builder()
                        .passwordEncoder(p::encode)
                        .username("docente3")
                        .password("docente3")
                        .roles("DOCENTE")
                        .build(),
                builder()
                        .passwordEncoder(p::encode)
                        .username("docente4")
                        .password("docente4")
                        .roles("DOCENTE")
                        .build(),
                builder()
                        .passwordEncoder(p::encode)
                        .username("aluno1")
                        .password("aluno1")
                        .roles("ALUNO")
                        .build(),
                builder()
                        .passwordEncoder(p::encode)
                        .username("aluno2")
                        .password("aluno2")
                        .roles("ALUNO")
                        .build(),
                builder()
                        .passwordEncoder(p::encode)
                        .username("aluno3")
                        .password("aluno3")
                        .roles("ALUNO")
                        .build(),
                builder()
                        .passwordEncoder(p::encode)
                        .username("aluno4")
                        .password("aluno4")
                        .roles("ALUNO")
                        .build()*/
        );

        return inMemoryUserDetailsManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        try
        {
            UserDetails x = inMemoryUserDetailsManager.loadUserByUsername(username);
            return x;
        } catch (UsernameNotFoundException ignored)
        {
        }

        Optional<Utilizador> utilizador = utilizadorRepository.findByUsername(username);
        if (utilizador.isEmpty())
        {
            throw new UsernameNotFoundException("Utilizador não encontrado: " + username);
        }

        UtilizadorAuthDTO authDTO = mapper.toAuthDTO(utilizador.get());

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(authDTO.getTipoUtilizador().toString());

        return new User(username, utilizador.get().getPassword(), authorities);
    }

    public Optional<UtilizadorAuthDTO> findInMemory(String username)
    {
        try
        {
            UserDetails user = inMemoryUserDetailsManager.loadUserByUsername(username);

            return Optional.of(mapper.toAuthDTO(user));

        } catch (UsernameNotFoundException ignored)
        {
        }
        return Optional.empty();
    }
}

