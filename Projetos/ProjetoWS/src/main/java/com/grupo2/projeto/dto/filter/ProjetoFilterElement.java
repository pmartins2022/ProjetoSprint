package com.grupo2.projeto.dto.filter;

import java.util.Arrays;

public class ProjetoFilterElement
{
    private final String name;
    private final ProjetoFilterElement[] elements;

    public ProjetoFilterElement(String name, ProjetoFilterElement[] elements)
    {
        this.name = name;
        this.elements = elements;
    }
    
    public ProjetoFilterElement[] getElements()
    {
        return elements;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name+"("+Arrays.toString(elements)+")";
    }
}
