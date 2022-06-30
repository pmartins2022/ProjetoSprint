package com.grupo2.projeto.model.filter;

public class OrFilter extends Filter
{
    private final Filter columnA;
    private final Filter columnB;

    public OrFilter(Filter columnA, Filter columnB)
    {
        this.columnA = columnA;
        this.columnB = columnB;
    }

    @Override
    public String toString()
    {
        return "("+columnA.toString()+" OR "+columnB.toString()+")";
    }
}
