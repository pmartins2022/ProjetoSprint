/*
package com.grupo2.projeto.repository.jdbc;

import com.grupo2.projeto.dataModel.ProjetoJDBCMapper;
import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.jdbcService.JDBCProjeto;
import com.grupo2.projeto.model.Projeto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

// Sempre que listo carrego tudo novamente
public class RepositoryProjeto
{
   private List<Projeto>  m_lstProjetos;
   private ProjetoJDBCMapper map;

    public RepositoryProjeto(){
        this.m_lstProjetos=new ArrayList<Projeto>();
    }

    public boolean adicionaProjeto(Projeto oProjeto)
    {
        if(validaProjeto(oProjeto)) {
            this.m_lstProjetos.add(oProjeto);
            return true;
        }

        return false;
    }

    public List<Projeto> getRegisto() {
        return this.m_lstProjetos;
    }

    public List<Projeto> getListProjetos(JDBCProjeto jdbcService) throws SQLException {
        String queryProjeto= "Select * From Projeto";
        //Get all Projetos from Database
        ResultSet rs = jdbcService.getListResultSetProjeto(queryProjeto);

        ProjetoJDBCMapper map= new ProjetoJDBCMapper();
        List<Projeto> lstProjetosDatabase=map.resultSetToListObjects(rs);

        //mergeProjetosWithDatabase(lstProjetosDatabase);

        return lstProjetosDatabase;

    }

    public List<Projeto> getListProjetosWithProcedure(JDBCProjeto jdbcService) throws SQLException {
        String query= "{ ? = call fncgetProjeto() }";
        ResultSet rs=jdbcService.getListResultSetProjetoWithProcedure(query);

        List<Projeto> lstProjetosDatabase=map.resultSetToListObjects(rs);
        //mergeProjetosWithDatabase(lstProjetosDatabase);

        return lstProjetosDatabase;

    }

    public void insertProjeto(JDBCProjeto jdbcService, ProjetoDTO oProjetoDTO) throws SQLException {
        Projeto Projeto= map.toDTO(oProjetoDTO);

        if(this.adicionaProjeto(Projeto)) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate= formatter.format(oProjetoDTO.getM_dataCompra());

            String insertData = "INSERT INTO Projeto " + "VALUES ('" + oProjetoDTO.getCodigo() + "','" + oProjetoDTO.getTitulo() + "','" + "TO_DATE('" + strDate + "','DD/MM/YY'),"
                    + oProjetoDTO.getM_valorPago() + "','" + oProjetoDTO.getM_localCompra()+ "')";

            int k = jdbcService.insertProjeto(insertData);
            System.out.println("Rows inserted: " + k);
        }


    }

    public void deleteProjeto(JDBCProjeto jdbcService, int codProjeto) throws SQLException {
        //Eliminar do repositorio
        for (Projeto oRegProjeto : this.m_lstProjetos){
                if (oRegProjeto.getCodigo()==codProjeto) {
                    this.m_lstProjetos.remove(oRegProjeto);
                    break;
                }
         }

        //Elimina objeto da Base de Dados
        String deleteData= "DELETE  FROM Projeto WHERE codProjeto = " +  codProjeto;
        jdbcService.deleteProjeto(deleteData);

    }
    private boolean validaProjeto(Projeto oProjeto)
     {
         return true;
     }

    */
/*
    private void mergeProjetosWithDatabase(List<Projeto> lstProjetosDatabase){
        int flag=0;
        for(Projeto oProjeto: lstProjetosDatabase) {
            for (Projeto oRegProjeto : this.m_lstProjetos)
                if (oProjeto.equals(oRegProjeto)) {
                    flag = 1;
                }
            if(flag==0) {this.m_lstProjetos.add(oProjeto);}
            flag=0;
        }

    }*//*



}

*/
