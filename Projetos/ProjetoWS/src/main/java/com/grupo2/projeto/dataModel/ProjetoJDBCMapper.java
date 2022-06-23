/*
package com.grupo2.projeto.dataModel;

import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.model.Projeto;
import domain.CD;
import dto.CDDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjetoJDBCMapper
{

    public Projeto toDTO(ProjetoDTO oProjetoDTO) {

        return new Projeto(oProjetoDTO.getCodigo(), oProjetoDTO.getTitulo(), oProjetoDTO.getM_dataCompra(), oProjetoDTO.getM_valorPago(), oProjetoDTO.getM_localCompra());
    }

    public ProjetoDTO toDTO(Projeto oProjeto) {

        return (new ProjetoDTO(oProjeto.getCodigo(), oProjeto.getTitulo(), oProjeto.getM_dataCompra(), oProjeto.getM_valorPago(), oProjeto.getM_localCompra()));
    }

    public Projeto recordToObject(Integer i_codigo, String s_titulo, Date o_dataCompra, Double d_valorPago, String s_localCompra) {
        return (new Projeto(i_codigo, s_titulo, o_dataCompra, d_valorPago, s_localCompra));
    }

    public ProjetoDTO recordToDTO(Integer i_codigo, String s_titulo, Date o_dataCompra, Double d_valorPago, String s_localCompra) {
        return (new ProjetoDTO(i_codigo, s_titulo, o_dataCompra, d_valorPago, s_localCompra));
    }
    public List<ProjetoDTO>  listObject2listDTOs(List<Projeto> olstProjeto) throws SQLException {

        List<ProjetoDTO> lstProjetos= new ArrayList<ProjetoDTO>();

        for(Projeto oProjeto: olstProjeto)
            lstProjetos.add(new ProjetoDTO(oProjeto.getCodigo(), oProjeto.getTitulo(), oProjeto.getM_dataCompra(), oProjeto.getM_valorPago(), oProjeto.getM_localCompra()));

        return lstProjetos;
    }

      public List<Projeto> resultSetToListObjects(ResultSet rs) throws SQLException {

        List<Projeto> lstProjetos= new ArrayList<Projeto>();

        while(rs.next())
            lstProjetos.add(this.recordToObject(rs.getInt("codProjeto"), rs.getString("titulo"), rs.getDate("dataCompra"), rs.getDouble("valorPago"), rs.getString("localCompra")));

        return lstProjetos;
    }

    public List<ProjetoDTO> resultSetToListDTOs(ResultSet rs) throws SQLException {

        List<ProjetoDTO> lstProjetos= new ArrayList<ProjetoDTO>();

        while(rs.next())
            lstProjetos.add(this.recordToDTO(rs.getInt("codProjeto"), rs.getString("titulo"), rs.getDate("dataCompra"), rs.getDouble("valorPago"), rs.getString("localCompra")));

        return lstProjetos;
    }

}
*/
