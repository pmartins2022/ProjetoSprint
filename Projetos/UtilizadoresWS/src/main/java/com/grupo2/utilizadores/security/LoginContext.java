package com.grupo2.utilizadores.security;
import com.grupo2.utilizadores.dto.UtilizadorAuthDTO;
import com.grupo2.utilizadores.dto.UtilizadorDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

@Service
public class LoginContext
{
    private static UtilizadorAuthDTO currentUser;
    private static String token;

    public static void setUser(UtilizadorAuthDTO user, String pass)
    {
        currentUser = user;
        byte[] t = (user.getUsername()+":"+pass).getBytes();
        token = Base64Utils.encodeToString(t);
    }

    public static UtilizadorAuthDTO getCurrent()
    {
        return currentUser;
    }

    public static void setCurrentUser(UtilizadorAuthDTO currentUser)
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