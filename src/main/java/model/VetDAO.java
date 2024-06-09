package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VetDAO extends DAO {
    private static VetDAO instance;

    public VetDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static VetDAO getInstance() {
        return (instance == null ? (instance = new VetDAO()) : instance);
    }

    // CRUD
    public Vet create(String nome, String email, String telefone) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO vet (nome, email, telefone) VALUES (?,?,?)");
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, telefone);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(VetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("vet", "id"));
    }

    private Vet buildObject(ResultSet rs) {
        Vet vet = null;
        try {
            vet = new Vet(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return vet;
    }

    // Generic Retriever
    public List<Vet> retrieve(String query) {
        List<Vet> vets = new ArrayList<>();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                vets.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return vets;
    }

    // RetrieveAll
    public List<Vet> retrieveAll() {
        return this.retrieve("SELECT * FROM vet");
    }

    // RetrieveById
    public Vet retrieveById(int id) {
        List<Vet> vets = this.retrieve("SELECT * FROM vet WHERE id = " + id);
        return (vets.isEmpty() ? null : vets.get(0));
    }

    // Update
    public void update(Vet vet) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE vet SET nome=?, email=?, telefone=? WHERE id=?");
            stmt.setString(1, vet.getNome());
            stmt.setString(2, vet.getEmail());
            stmt.setString(3, vet.getTelefone());
            stmt.setInt(4, vet.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Delete
    public void delete(Vet vet) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM vet WHERE id = ?");
            stmt.setInt(1, vet.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
