package com.grupo2.proposta.security;

import com.grupo2.proposta.dto.UtilizadorAuthDTO;
import com.grupo2.proposta.dto.UtilizadorDTO;
import org.springframework.stereotype.Service;

@Service
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