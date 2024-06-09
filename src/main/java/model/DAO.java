package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO {
    public static final String DBURL = "jdbc:h2:C:/Users/Gabriel/Desktop/ClinicaVeterinaria/data/vet2023-Entrega";
    private static Connection con;
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Connect to SQLite
    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(DBURL);
                if (con != null) {
                    DatabaseMetaData meta = con.getMetaData();
                }
            } catch (SQLException e) {
                System.err.println("Exception: " + e.getMessage());
            }
        }
        return con;
    }

    protected ResultSet getResultSet(String query) {
        Statement s;
        ResultSet rs = null;
        try {
            s = (Statement) con.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return rs;
    }

    protected int executeUpdate(PreparedStatement queryStatement) throws SQLException {
        int update;
        update = queryStatement.executeUpdate();
        return update;
    }

    protected int lastId(String tableName, String primaryKey) {
        Statement s;
        int lastId = -1;
        try {
            s = (Statement) con.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(" + primaryKey + ") AS id FROM " + tableName);
            if (rs.next()) {
                lastId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return lastId;
    }

    public static void terminar() {
        try {
            (DAO.getConnection()).close();
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Create table SQLite
    protected final boolean createTable() {
        try {
            PreparedStatement stmt;
            // Table client:
            stmt = DAO.getConnection().prepareStatement("""
                                                        CREATE TABLE IF NOT EXISTS cliente( 
                                                        id INTEGER PRIMARY KEY AUTO_INCREMENT, 
                                                        nome VARCHAR, 
                                                        endereco VARCHAR, 
                                                        cep VARCHAR, 
                                                        email VARCHAR, 
                                                        telefone VARCHAR); 
                                                        """);
            executeUpdate(stmt);
            // Table animal:
            stmt = DAO.getConnection().prepareStatement("""
                                                        CREATE TABLE IF NOT EXISTS animal( 
                                                        id INTEGER PRIMARY KEY AUTO_INCREMENT, 
                                                        nome VARCHAR, 
                                                        anoNasc INTEGER, 
                                                        sexo VARCHAR, 
                                                        id_especie INTEGER, 
                                                        id_cliente INTEGER); 
                                                        """);
            executeUpdate(stmt);
            // Table species:
            stmt = DAO.getConnection().prepareStatement("""
                                                        CREATE TABLE IF NOT EXISTS especie( 
                                                        id INTEGER PRIMARY KEY AUTO_INCREMENT, 
                                                        nome VARCHAR); 
                                                        """);
            executeUpdate(stmt);
            // Table vet:
            stmt = DAO.getConnection().prepareStatement("""
                                                        CREATE TABLE IF NOT EXISTS vet( 
                                                        id INTEGER PRIMARY KEY AUTO_INCREMENT, 
                                                        nome VARCHAR, 
                                                        email VARCHAR, 
                                                        telefone VARCHAR); 
                                                        """);
            executeUpdate(stmt);        
            // Table appointment:
            stmt = DAO.getConnection().prepareStatement("""
                                                        CREATE TABLE IF NOT EXISTS consulta( 
                                                        id INTEGER PRIMARY KEY AUTO_INCREMENT, 
                                                        data DATE, 
                                                        horario VARCHAR, 
                                                        comentario VARCHAR, 
                                                        id_animal INTEGER, 
                                                        id_vet INTEGER, 
                                                        terminado INTEGER); 
                                                        """);
            executeUpdate(stmt);            
             // Table exam:
            stmt = DAO.getConnection().prepareStatement("""
                                                        CREATE TABLE IF NOT EXISTS exame( 
                                                        id INTEGER PRIMARY KEY AUTO_INCREMENT, 
                                                        nome VARCHAR, 
                                                        id_consulta INTEGER); 
                                                        """);
            executeUpdate(stmt);      
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
