package com.grupo2.projeto.jdbcService;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.sql.SQLException;

public class JDBC
{
  DBConnectionHandler dbConnHandler = null;

  public JDBC()
  {
    this.openJDBCConnection();
  }

  public void openJDBCConnection()
  {
    //Colocar dados de login no ficheiro de configuração?
    String[] jdbcDeiUrl = null;
    String username = "upskill_bd_turma2_07";
    String password = "qwerty";

    Configurations configs = new Configurations();
    try
    {
      Configuration config = configs.properties(new File("config.properties"));

      // access configuration properties
      jdbcDeiUrl = config.getStringArray("jdbcPorticUrl"); //jdbcDeiUrl
    } catch (ConfigurationException ex)
    {
      // Something went wrong
    }
    try
    {
      dbConnHandler = new DBConnectionHandler(jdbcDeiUrl[0], username, password);

      System.out.println("\nEstabelecer a ligação à BD...");
      dbConnHandler.openConnection();

      System.out.println("\t... Ligação obtida.");


    } catch (SQLException ex)
    {
      System.out.println(ex.getMessage());
    }

  }

  public DBConnectionHandler getDbConnHandler()
  {
    return dbConnHandler;
  }
}
