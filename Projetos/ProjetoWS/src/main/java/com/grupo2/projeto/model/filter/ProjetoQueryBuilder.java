package com.grupo2.projeto.model.filter;

import com.grupo2.projeto.dto.filter.ProjetoFilterBody;
import com.grupo2.projeto.dto.filter.ProjetoFilterElement;

import java.util.ArrayList;
import java.util.List;

public class ProjetoQueryBuilder
{
    List<Filter> filterList;

    public ProjetoQueryBuilder()
    {
        filterList = new ArrayList<>();
    }


    public void addFilter(Filter f)
    {
        filterList.add(f);
    }


    public String build()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT * FROM PROJETO");

        if (filterList.size() > 0)
        {
            stringBuilder.append(" WHERE ");
            for(Filter f : filterList)
            {
                stringBuilder.append(f.toString());
            }
        }

        return stringBuilder.toString();
    }

    public Filter readBody(ProjetoFilterBody body)
    {
        return readElement(body.getBase());
    }

    private Filter readElement(ProjetoFilterElement e)
    {
        Filter f = null;

        switch (e.getName().toUpperCase())
        {
            case "AND" -> {
                f = new AndFilter(readElement(e.getElements()[0]), readElement(e.getElements()[1]));
                return f;
            }
            case "OR" -> {
                f = new OrFilter(readElement(e.getElements()[0]), readElement(e.getElements()[1]));
                return f;
            }
            case "NOT" -> {
                f = new NotFilter(readElement(e.getElements()[0]));
                return f;
            }
        }

        QueryPresets qp = QueryPresets.valueOf(e.getName());

        f = new StringFilter(qp.getValue());

        return f;
    }
}