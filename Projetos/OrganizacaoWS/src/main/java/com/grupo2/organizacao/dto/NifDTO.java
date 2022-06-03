package com.grupo2.organizacao.dto;

/**
 * Classe DTO do nif
 */
public class NifDTO
{
    /**
     * numero do nif
     */
    private int nr;
    /**
     * name do portador do nif
     */
    private String name;

    /**
     * Inicializa o Nif sem parametros
     */
    public NifDTO() {
    }

    /**
     * Inicializa o nif com nr e name passando com parametros o nr o name
     * @param nr e o nr do NifDTO
     * @param name e o name do NifDTO
     */
    public NifDTO(int nr, String name) {
        this.nr = nr;
        this.name = name;
    }

    /**
     Devolve o nr do nif
     * @return o nr do nif
     */
    public int getNr() {
        return nr;
    }

    /**
     * Modifica o nr do nif
     * @param nr novo nr do nif
     */
    public void setNr(int nr) {
        this.nr = nr;
    }

    /**
     Devolve o name do nif
     * @return o name do nif
     */
    public String getName() {
        return name;
    }

    /**
     * Modifica o name do nif
     * @param name novo name do nif
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Devolve um Nif com nr e name
     * @return nr e name
     */
    @Override
    public String toString() {
        return "NIF : \n" +
                "Número: " + nr +"\n"+
                "Nome: " + name ;
    }
}

