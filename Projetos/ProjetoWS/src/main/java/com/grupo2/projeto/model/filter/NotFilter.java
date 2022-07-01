package com.grupo2.projeto.model.filter;

public class NotFilter extends Filter
{
    private final Filter columnFilter;

    public NotFilter(Filter columnFilter)
    {
        this.columnFilter = columnFilter;
    }

    @Override
    public String toString()
    {
        return "NOT ("+columnFilter.toString()+")";
    }
}
