package com.grupo2.edicaouc.security;

import com.grupo2.edicaouc.dto.UtilizadorDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

@Service
public class LoginContext
{
    private static UtilizadorDTO currentUser;
    private static String token;

    public static void setUser(UtilizadorDTO user, String pass)
    {
        currentUser = user;
        byte[] t = (user.getUsername()+":"+pass).getBytes();
        token = Base64Utils.encodeToString(t);
    }

    public static UtilizadorDTO getCurrent()
    {
        return currentUser;
    }

    public static void setCurrentUser(UtilizadorDTO currentUser)
    {
        LoginContext.currentUser = currentUser;
    }

    public static String getToken()
    {
        return token;
    }

    public static void setToken(String token)
    {
        LoginContext.token = token;
    }
}