package com.grupo2.projeto.dto.filter;

import java.util.Arrays;

public class ProjetoFilterBody
{
    private final ProjetoFilterElement baseElement;
    private final Object[] params;

    public ProjetoFilterBody(ProjetoFilterElement baseElement, Object[] params)
    {
        this.baseElement = baseElement;
        this.params = params;
    }

    public String toString()
    {
        if (baseElement == null)
        {
            return "Base: NULL"+"\nParams:"+Arrays.toString(params);
        }
        return "Base: "+baseElement.toString()+"\nParams:"+Arrays.toString(params);
    }

    public ProjetoFilterElement getBase()
    {
        return baseElement;
    }

    public Object[] getParams()
    {
        return params;
    }
}
