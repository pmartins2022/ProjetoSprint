package com.pp.utilizadorWS.model;

public enum TipoUtilizador
{
    ADMINISTRADOR
            {
                @Override
                public String toString()
                {
                    return "ROLE_ADMIN";
                }
            },
    DOCENTE
            {
                @Override
                public String toString()
                {
                    return "ROLE_DOCENTE";
                }
            },
    ALUNO
            {
                @Override
                public String toString()
                {
                    return "ROLE_ALUNO";
                }
            }
}