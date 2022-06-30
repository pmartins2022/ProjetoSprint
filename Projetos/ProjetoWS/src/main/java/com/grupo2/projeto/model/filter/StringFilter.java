package com.grupo2.projeto.model.filter;

public class StringFilter extends Filter
{
    private final String value;

    public StringFilter(String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "("+value+")";
    }
}
