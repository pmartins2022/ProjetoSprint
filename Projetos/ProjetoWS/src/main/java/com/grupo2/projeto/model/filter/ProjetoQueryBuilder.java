package com.grupo2.projeto.model.filter;

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
}