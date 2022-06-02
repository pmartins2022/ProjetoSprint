package com.grupo2.organizacao.dto;

public class NifDTO {
    private int nr;
    private String name;

    public NifDTO() {
    }

    public NifDTO(int nr, String name) {
        this.nr = nr;
        this.name = name;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NIF : \n" +
                "Número: " + nr +"\n"+
                "Nome: " + name ;
    }
}

