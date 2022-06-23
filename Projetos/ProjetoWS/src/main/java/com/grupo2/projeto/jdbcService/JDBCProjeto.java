package com.grupo2.projeto.jdbcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


public class JDBCProjeto
{
  @Autowired
  private JDBC jdbc;

  public JDBCProjeto()
  {
    this.jdbc = new JDBC();
  }

  public void closeJDBCConnection()
  {
    String mensagem = jdbc.getDbConnHandler().closeAll();
    if (!mensagem.isEmpty())
      System.out.println(mensagem);
    System.out.println("\nTerminada a ligação à BD.");
    jdbc.getDbConnHandler().closeAll();
  }

/*
CUIDADO! QUERYS AINDA NÃO FUNCIONAM
 */
  public ResultSet getListResultSetProjeto(String query) throws SQLException
  {
    jdbc.getDbConnHandler().createStatement();
    ResultSet rs = jdbc.getDbConnHandler().select(query);

    return rs;
  }

  public ResultSet getListResultSetProjetoWithProcedure(String query) throws SQLException
  {
    jdbc.getDbConnHandler().createCallableStatement(query);
    ResultSet rs = jdbc.getDbConnHandler().executeProcedure(query);

    return rs;
  }

  public int insertCD(String query) throws SQLException
  {
    jdbc.getDbConnHandler().createStatement();
    int i = jdbc.getDbConnHandler().insert(query);

    return i;
  }

  public int doSomething() throws SQLException
  {
    jdbc.getDbConnHandler().createStatement();
    int i = jdbc.getDbConnHandler().insert("select * from Projeto");

    return i;
  }

  public int deleteCD(String query) throws SQLException
  {
    jdbc.getDbConnHandler().createStatement();
    int i = jdbc.getDbConnHandler().delete(query);

    return i;
  }


}
