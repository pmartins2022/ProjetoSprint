package com.example.javafx.model;
import io.netty.handler.codec.base64.Base64;
import io.netty.handler.codec.base64.Base64Encoder;
import org.springframework.util.Base64Utils;

import com.example.javafx.dto.UtilizadorAuthDTO;

public class LoginContext
{
    private static UtilizadorAuthDTO currentUser;
    private static String token;

    public static void setUser(UtilizadorAuthDTO user)
    {
        currentUser = user;
        token = Base64Utils.encodeToString(user.getUsername().getBytes())+":"+Base64Utils.encodeToString(user.getPassword().getBytes());
    }

    public static UtilizadorAuthDTO getCurrentUser()
    {
        return currentUser;
    }

    public static String getToken()
    {
        return token;
    }
}
