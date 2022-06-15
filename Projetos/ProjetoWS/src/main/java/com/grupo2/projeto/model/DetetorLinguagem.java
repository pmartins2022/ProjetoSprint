package com.grupo2.projeto.model;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;

public class DetetorLinguagem
{
    private static final String TOKEN = "e4bf86919702e7a7e716dda243b37bdf";

    public static String detetar(String texto) throws APIError
    {
        DetectLanguage.apiKey = TOKEN;

        var results = DetectLanguage.detect(texto);

        return results.get(0).language;
    }
}
