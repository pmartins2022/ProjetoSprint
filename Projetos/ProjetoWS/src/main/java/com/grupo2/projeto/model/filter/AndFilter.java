package com.grupo2.projeto.model.filter;

public class AndFilter extends Filter
{
    private final Filter columnA;
    private final Filter columnB;

    public AndFilter(Filter columnA, Filter columnB)
    {
        this.columnA = columnA;
        this.columnB = columnB;
    }

    @Override
    public String toString()
    {
        return "("+columnA.toString()+" AND "+columnB.toString()+")";
    }
}
