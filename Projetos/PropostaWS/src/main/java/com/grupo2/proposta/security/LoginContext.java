package com.grupo2.proposta.security;

import com.grupo2.proposta.dto.UtilizadorAuthDTO;
import com.grupo2.proposta.dto.UtilizadorDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

@Service
public class LoginContext
{
    private static UtilizadorAuthDTO current;
    private static String token;

    public static void setCurrent(UtilizadorAuthDTO c)
    {
        current = c;
    }

    public static UtilizadorAuthDTO getCurrent()
    {
        return current;
    }

    public static void setToken(String token)
    {
        LoginContext.token = token;
    }

    public static String getToken()
    {
        return token;
    }
}