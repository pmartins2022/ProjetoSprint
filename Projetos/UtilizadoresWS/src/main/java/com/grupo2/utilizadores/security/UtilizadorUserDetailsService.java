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
    private UtilizadorRepository utilizadorRepository;
    /**
     * Objeto do tipo InMemoryUserDetailsManager a ser utilizador por UtilizadorUserDetailsService
     */
    private static InMemoryUserDetailsManager inMemoryUserDetailsManager;
    /**
     * Objeto do tipo UtilizadorDTOMapper a ser utilizador por UtilizadorUserDetailsService
     */
    @Autowired
    private UtilizadorDTOMapper mapper;


    /**
     * Criar uma password encoder para o Spring Security
     * @return a password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * Método que permite criar Admin
     * @return informacao do servico de utilizadores
     */
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

    /**
     * Chamado automaticamente pelo Spring. Iniciar a autenticacao ao nosso servico, fornecendo o username.
     * @param username o username a autenticar
     * @return informacao do utilizador
     * @throws UsernameNotFoundException se nao existe esse utilizador
     */
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

        LoginContext.setCurrentUser(authDTO);

        return new User(username, utilizador.get().getPassword(), authorities);
    }

    /**
     * Devolve UtilizadorAuthDTO caso inMemoryUserDetailsManager o encontre ou Optional.empty()
     * @param username username do UtilizadorAuthDTO
     * @return UtilizadorAuthDTO ou Optional.empty()
     */
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

