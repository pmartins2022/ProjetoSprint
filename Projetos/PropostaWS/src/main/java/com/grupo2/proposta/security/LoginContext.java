package com.grupo2.proposta.security;

import com.grupo2.proposta.dto.UtilizadorAuthDTO;
import com.grupo2.proposta.dto.UtilizadorDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * Classe que guarda as informações do Utilizador com login efetuado
 */
@Service
public class LoginContext
{
    /**
     * UtilizadorAuthDTO com login efetuado
     */
    private static UtilizadorAuthDTO current;
    /**
     * Token do UtilizadorAuthDTO
     */
    private static String token;

    /**
     * Atualiza o atual UtilizadorAuthDTO com login efetuado
     * @param c UtilizadorAuthDTO com login efetuado
     */
    public static void setCurrent(UtilizadorAuthDTO c)
    {
        current = c;
    }

    /**
     * Devolve o atual UtilizadorAuthDTO com login efetuado
     * @return
     */
    public static UtilizadorAuthDTO getCurrent()
    {
        return current;
    }

    /**
     * Modifica o token do UtilizadorAuthDTO com login efetuado
     * @param token token do UtilizadorAuthDTO com login efetuado
     */
    public static void setToken(String token)
    {
        LoginContext.token = token;
    }

    /**
     * Devolve o token do UtilizadorAuthDTO com login efetuado
     * @return token do UtilizadorAuthDTO com login efetuado
     */
    public static String getToken()
    {
        return token;
    }

}