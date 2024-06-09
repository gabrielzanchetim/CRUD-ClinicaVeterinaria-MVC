package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultaDAO extends DAO {
    private static ConsultaDAO instance;

    public ConsultaDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static ConsultaDAO getInstance() {
        return (instance == null ? (instance = new ConsultaDAO()) : instance);
    }

    // CRUD
    public Consulta create(Date data, String horario, String comentario, int id_animal, int id_vet, int terminado) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO consulta (data, horario, comentario, id_animal, id_vet, terminado) VALUES (?,?,?,?,?,?)");
            stmt.setDate(1, data);
            stmt.setString(2, horario);
            stmt.setString(3, comentario);
            stmt.setInt(4, id_animal);
            stmt.setInt(5, id_vet);
            stmt.setInt(6, terminado);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("consulta", "id"));
    }

    private Consulta buildObject(ResultSet rs) {
        Consulta consulta = null;
        try {
            consulta = new Consulta(rs.getInt("id"), rs.getDate("data"), rs.getString("horario"), rs.getString("comentario"), rs.getInt("id_animal"), rs.getInt("id_vet"), rs.getInt("terminado"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consulta;
    }

    // Generic Retriever
    public List<Consulta> retrieve(String query) {
        List<Consulta> consultas = new ArrayList<>();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                consultas.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consultas;
    }

    // RetrieveAll
    public List<Consulta> retrieveAll() {
        return this.retrieve("SELECT * FROM consulta");
    }

    // RetrieveById
    public Consulta retrieveById(int id) {
        List<Consulta> consultas = this.retrieve("SELECT * FROM consulta WHERE id = " + id);
        return (consultas.isEmpty() ? null : consultas.get(0));
    }
    
    // RetrieveByAnimalID
    public List<Consulta> retrieveByAnimalId(int animalId) {
        return this.retrieve("SELECT * FROM consulta WHERE id_animal = " + animalId);
    }
    
    // RetrieveByVetId
    public List<Consulta> retrieveByVetId(int vetId) {
        return this.retrieve("SELECT * FROM consulta WHERE id_vet = " + vetId);
    }

    // Update
    public void update(Consulta consulta) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE consulta SET data=?, horario=?, comentario=?, id_animal=?, id_vet=?, terminado=? WHERE id=?");
            stmt.setDate(1, consulta.getData());
            stmt.setString(2, consulta.getHorario());
            stmt.setString(3, consulta.getComentario());
            stmt.setInt(4, consulta.getIdAnimal());
            stmt.setInt(5, consulta.getIdVet());
            stmt.setInt(6, consulta.getTerminado());
            stmt.setInt(7, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Delete
    public void delete(Consulta consulta) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM consulta WHERE id = ?");
            stmt.setInt(1, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
