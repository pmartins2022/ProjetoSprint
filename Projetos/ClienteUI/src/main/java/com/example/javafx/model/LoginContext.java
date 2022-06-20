package com.example.javafx.model;

import com.example.javafx.dto.UtilizadorAuthDTO;
import io.netty.handler.codec.base64.Base64;
import io.netty.handler.codec.base64.Base64Encoder;
import org.springframework.util.Base64Utils;

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

    public static UtilizadorAuthDTO getCurrentUser()
    {
        return currentUser;
    }

    public static String getToken()
    {
        return "Basic "+token;
    }
}
