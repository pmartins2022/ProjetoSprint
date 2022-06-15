package com.grupo2.projeto.security;

import com.grupo2.projeto.dto.UtilizadorAuthDTO;

public class LoginContext
{
    private static UtilizadorAuthDTO current;

    public static void setCurrent(UtilizadorAuthDTO c)
    {
        current = c;
    }

    public static UtilizadorAuthDTO getCurrent()
    {
        return current;
    }
}
