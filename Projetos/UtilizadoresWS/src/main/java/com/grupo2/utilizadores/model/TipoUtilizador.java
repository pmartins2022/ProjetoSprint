package com.grupo2.utilizadores.model;

/**
 * Classe Enum para o TipoUtilizador
 */
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
            },
    ORIENTADOR
            {
                @Override
                public String toString()
                {
                    return "ROLE_ORIENTADOR";
                }
            }

}
