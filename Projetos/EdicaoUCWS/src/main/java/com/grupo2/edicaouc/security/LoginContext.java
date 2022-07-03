package com.grupo2.edicaouc.security;

import com.grupo2.edicaouc.dto.UtilizadorDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * Classe para facilitar a gestao do utilizador atual que esta autenticado no servico
 */
@Service
public class LoginContext
{
    private static UtilizadorDTO currentUser;
    private static String token;

    /**
     * Colocar um utilizador novo como o atual
     * @param user informacao do utilizador
     * @param pass a sua password
     */
    public static void setUser(UtilizadorDTO user, String pass)
    {
        currentUser = user;
        byte[] t = (user.getUsername()+":"+pass).getBytes();
        token = Base64Utils.encodeToString(t);
    }

    /**
     * Obter o utilizador atual
     * @return o utilizador
     */
    public static UtilizadorDTO getCurrent()
    {
        return currentUser;
    }

    /**
     * Colocar diretamente um utilizador como o atual
     * @param currentUser informacao do utilizador
     */
    public static void setCurrentUser(UtilizadorDTO currentUser)
    {
        LoginContext.currentUser = currentUser;
    }

    /**
     * Obter o token de sessao deste utilizador
     * @return o token
     */
    public static String getToken()
    {
        return token;
    }

    /**
     * Colocar diretamente o token de sessao para este utilizador
     * @param token o novo token
     */
    public static void setToken(String token)
    {
        LoginContext.token = token;
    }
}