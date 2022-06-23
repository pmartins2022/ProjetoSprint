package com.grupo2.projeto.jdbcService;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;


public class DBConnectionHandler {

    private String jdbcUrl;
    private String username;
    private String password;

    private Connection connection;
    private PreparedStatement prepStmt;
    private Statement stmt;
    private CallableStatement callStmt;
    private ResultSet rSet;

    public DBConnectionHandler(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;

        connection = null;
        prepStmt = null;
        rSet = null;
        stmt = null;
        callStmt=null;
    }

    public void openConnection() throws SQLException {
        OracleDataSource ds = new OracleDataSource();
        ds.setURL(jdbcUrl);
        connection = ds.getConnection(username, password);
    }

    public void createStatement() throws SQLException {
        stmt = connection.createStatement();
        }

    public void createPrepareStatement(String sqlStm) throws SQLException {
        prepStmt = connection.prepareStatement(sqlStm);
    }

    public void createCallableStatement(String sqlStm) throws SQLException {
        callStmt = connection.prepareCall(sqlStm);
    }

    public String closeAll() {

        StringBuilder message = new StringBuilder("");

        if (rSet != null) {
            try {
                rSet.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            rSet = null;
        }

        if (prepStmt != null) {
            try {
                prepStmt.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            prepStmt = null;
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            stmt = null;
        }

        if (callStmt != null) {
            try {
                callStmt.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            callStmt  = null;
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            connection = null;
        }
        return message.toString();
    }


    public boolean create(String sqlCreate) throws SQLException{
        boolean bool = stmt.execute(sqlCreate);

        return bool;
    }

    public boolean createView(String sqlCreateView) throws SQLException{
        return(prepStmt.execute(sqlCreateView));
    }


    public int createProcedure(String sqlCreateProcedure) throws SQLException{
        return (stmt.executeUpdate(sqlCreateProcedure));
    }

    public ResultSet executeProcedure(String sqlCreateProcedure) throws SQLException{
        callStmt.registerOutParameter (1, OracleTypes.CURSOR);
        callStmt.execute();
        ResultSet rs=(ResultSet) callStmt.getObject(1);

        return rs;
    }


    public int insert(String sqlInsert) throws SQLException {
        int numRecords = stmt.executeUpdate(sqlInsert);

        return numRecords;
    }

    public int delete(String sqlDelete) throws SQLException {
        int numRecords = stmt.executeUpdate(sqlDelete);

        return numRecords;
    }

    public ResultSet select(String sqlSelect) throws SQLException {
        return( stmt.executeQuery(sqlSelect));
    }

    public void drop(String sqlDrop) throws SQLException{
           stmt.executeUpdate(sqlDrop);
    }
}
