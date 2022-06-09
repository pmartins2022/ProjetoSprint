package com.grupo2.edicaouc.security;

import com.grupo2.edicaouc.dto.UtilizadorDTO;
import org.springframework.stereotype.Service;

@Service
public class LoginContext
{
    private static UtilizadorDTO current;

    public static void setCurrent(UtilizadorDTO c)
    {
        current = c;
    }

    public static UtilizadorDTO getCurrent()
    {
        return current;
    }


}