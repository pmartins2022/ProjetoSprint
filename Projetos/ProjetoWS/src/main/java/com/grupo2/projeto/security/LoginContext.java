package com.grupo2.projeto.security;

import com.grupo2.projeto.dto.UtilizadorAuthDTO;

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

    public static void setToken(String encoded)
    {
        token = encoded;
    }

    public static String getToken()
    {
        return token;
    }
}
