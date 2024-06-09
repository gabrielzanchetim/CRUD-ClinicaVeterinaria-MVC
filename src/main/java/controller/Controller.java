/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import model.Animal;
import model.AnimalDAO;
import model.Cliente;
import model.ClienteDAO;
import model.Consulta;
import model.ConsultaDAO;
import model.DAO;
import model.Especie;
import model.EspecieDAO;
import model.Exame;
import model.ExameDAO;
import model.Vet;
import model.VetDAO;
/**
 *
 * @author Gabriel
 */
public class Controller {

    public void carregaDados(DefaultTableModel model, JTable tabela, String tableName) {
        model = new DefaultTableModel();
        tabela.setModel(model);

        try {
            Connection conn = DAO.getConnection();
            PreparedStatement stmt;

            if (null == tableName) {
                stmt = conn.prepareStatement("SELECT * FROM " + tableName);
            } else switch (tableName) {
                case "animal":
                    stmt = conn.prepareStatement("SELECT animal.id, animal.nome, animal.anoNasc, animal.sexo, especie.nome, cliente.nome FROM animal INNER JOIN especie ON animal.id_especie = especie.id INNER JOIN cliente ON animal.id_cliente = cliente.id");
                    model.setColumnIdentifiers(new String[] {"ID", "Nome", "Ano Nascimento", "Sexo", "Espécie", "Cliente"});
                    break;
                case "consulta":
                    stmt = conn.prepareStatement("SELECT consulta.id, consulta.data, consulta.horario, consulta.comentario, animal.nome, vet.nome, consulta.terminado FROM consulta INNER JOIN animal ON consulta.id_animal = animal.id INNER JOIN vet ON consulta.id_vet = vet.id");
                    model.setColumnIdentifiers(new String[] {"ID", "Data", "Horário", "Comentário", "Animal", "Veterinário", "Terminado"});
                    break;
                default:
                    stmt = conn.prepareStatement("SELECT * FROM " + tableName);
                    break;
            }

            ResultSet rs = stmt.executeQuery();

            if (!"animal".equals(tableName) && !"consulta".equals(tableName)) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                String[] columnNames = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    columnNames[i - 1] = metaData.getColumnName(i);
                }
                model.setColumnIdentifiers(columnNames);
            }

            while (rs.next()) {
                Object[] rowData = new Object[model.getColumnCount()];
                for (int i = 1; i <= model.getColumnCount(); i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
            }

            if (model.getColumnCount() > 0) {
                TableColumn column = tabela.getColumnModel().getColumn(0);
                column.setPreferredWidth(25);
            }
        } catch (SQLException e) {
        }
    }

    public void preencheJBoxAnimal(JComboBox<String> campoSexoAnimalCadastro, JComboBox<String> campoEspecieAnimalCadastro, JComboBox<String> campoClienteAnimalCadastro) {
        campoSexoAnimalCadastro.removeAllItems();
        campoSexoAnimalCadastro.addItem("Macho");
        campoSexoAnimalCadastro.addItem("Fêmea");

        campoEspecieAnimalCadastro.removeAllItems();
        EspecieDAO especieDAO = EspecieDAO.getInstance();
        List<Especie> especies = especieDAO.retrieveAll();
        for (Especie especie : especies) {
            campoEspecieAnimalCadastro.addItem(especie.getNome() + ":" + especie.getId());
        }

        campoClienteAnimalCadastro.removeAllItems();
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        List<Cliente> clientes = clienteDAO.retrieveAll();
        for (Cliente cliente : clientes) {
            campoClienteAnimalCadastro.addItem(cliente.getNome() + ":" + cliente.getId());
        }
    }

    public void preencheJBoxConsulta(JComboBox<String> campoTerminadoConsultaCadastro, JComboBox<String> campoVeterinárioConsultaCadastro, JComboBox<String> campoAnimalConsultaCadastro) {
        campoTerminadoConsultaCadastro.removeAllItems();
        campoTerminadoConsultaCadastro.addItem("0");
        campoTerminadoConsultaCadastro.addItem("1");
    
        campoVeterinárioConsultaCadastro.removeAllItems();
        VetDAO vetDAO = VetDAO.getInstance();
        List<Vet> vets = vetDAO.retrieveAll();
        for (Vet vet : vets) {
            campoVeterinárioConsultaCadastro.addItem(vet.getNome() + ":" + vet.getId());
        }

        campoAnimalConsultaCadastro.removeAllItems(); 
        AnimalDAO animalDAO = AnimalDAO.getInstance();
        List<Animal> animais = animalDAO.retrieveAll();
        for (Animal animal : animais) {
            campoAnimalConsultaCadastro.addItem(animal.getNome() + ":" + animal.getId());
        }
    }

    public void preencheJBoxExame(JComboBox<String> campoIDConsultaCadastroExame) {
        campoIDConsultaCadastroExame.removeAllItems(); // Limpar itens existentes
        ConsultaDAO consultaDAO = ConsultaDAO.getInstance();
        List<Consulta> consultas = consultaDAO.retrieveAll();
        for (Consulta consulta : consultas)     {
            campoIDConsultaCadastroExame.addItem(String.valueOf(consulta.getId()));
        }
    }

    public void criaNovoVet(String[] values, DefaultTableModel model, JTable table) {
        VetDAO vetDAO = VetDAO.getInstance();
        Vet novoVeterinario = vetDAO.create(values[0], values[1], values[2]);
        carregaDados(model, table, "vet");
    }

    public void criaNovoExame(String[] values, DefaultTableModel model, JTable table) {
        ExameDAO exameDAO = ExameDAO.getInstance();
        Exame novoExame = exameDAO.create(values[0], Integer.parseInt(values[1]));
        carregaDados(model, table, "exame");
    }
    
    public void criaNovaEspecie(String[] values, DefaultTableModel model, JTable table) {
        EspecieDAO especieDAO = EspecieDAO.getInstance();
        Especie novaEspecie = especieDAO.create(values[0]);
        carregaDados(model, table, "especie");
    }
    
    public void criaNovaConsulta(String[] values, DefaultTableModel model, JTable table) {
        ConsultaDAO consultaDAO = ConsultaDAO.getInstance();

        String[] animalParts = values[3].split(":");
        int animalId = Integer.parseInt(animalParts[1]);

        String[] vetParts = values[4].split(":");
        int vetId = Integer.parseInt(vetParts[1]);

        LocalDate localDate = LocalDate.parse(values[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Date sqlDate = Date.valueOf(localDate);

        Consulta novaConsulta = consultaDAO.create(sqlDate, values[1], values[2], animalId, vetId, Integer.parseInt(values[5]));
        carregaDados(model, table, "consulta");
    }

    public void criaNovoCliente(String[] values, DefaultTableModel model, JTable table) {
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente novoCliente = clienteDAO.create(values[0], values[1], values[2], values[3], values[4]);
        carregaDados(model, table, "cliente");
    }

    public void criaNovoAnimal(String[] values, DefaultTableModel model, JTable table) {
        AnimalDAO animalDAO = AnimalDAO.getInstance();
        String[] especieParts = values[3].split(":");
        int especieId = Integer.parseInt(especieParts[1]);

        String[] clienteParts = values[4].split(":");
        int clienteId = Integer.parseInt(clienteParts[1]);

        Animal novoAnimal = animalDAO.create(values[0], Integer.parseInt(values[1]), values[2], especieId, clienteId);
        carregaDados(model, table, "animal");
    }
    
    public void deleteAnimal(int animalId, DefaultTableModel model, JTable table) {
        AnimalDAO animalDAO = new AnimalDAO();
        Animal animalToDelete = animalDAO.retrieveById(animalId);

        ConsultaDAO consultaDAO = new ConsultaDAO();
        List<Consulta> consultasAssociadas = consultaDAO.retrieveByAnimalId(animalId);

        if (consultasAssociadas.isEmpty()) {
            animalDAO.delete(animalToDelete);
            carregaDados(model, table, "animal");
        } else {
            JOptionPane.showMessageDialog(null, "A exclusão do animal não foi realizada pois ele está associado a uma consulta");
        }
    }
    
    public void deleteCliente(int clienteId, DefaultTableModel model, JTable table) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente clienteToDelete = clienteDAO.retrieveById(clienteId);

        AnimalDAO animalDAO = new AnimalDAO();
        List<Animal> animaisAssociados = animalDAO.retrieveByClienteId(clienteId);

        if (animaisAssociados.isEmpty()) {
            clienteDAO.delete(clienteToDelete);
            carregaDados(model, table, "cliente");
        } else {
            JOptionPane.showMessageDialog(null, "A exclusão do cliente não foi realizada pois ele está associado a um animal");
        }
    }
    
    public void deleteConsulta(int consultaId, DefaultTableModel model, JTable table) {
        ConsultaDAO consultaDAO = new ConsultaDAO();
        Consulta consultaToDelete = consultaDAO.retrieveById(consultaId);

        ExameDAO exameDAO = new ExameDAO();
        List<Exame> examesAssociados = exameDAO.retrieveByConsultaId(consultaId);

        if (examesAssociados.isEmpty()) {
            consultaDAO.delete(consultaToDelete);
            carregaDados(model, table, "consulta");
        } else {
            JOptionPane.showMessageDialog(null, "A exclusão da consulta não foi realizada pois ela está associada a um exame");
        }
    }

    public void deleteEspecie(int especieId, DefaultTableModel model, JTable table) {
        EspecieDAO especieDAO = new EspecieDAO();
        Especie especieToDelete = especieDAO.retrieveById(especieId);

        AnimalDAO animalDAO = new AnimalDAO();
        List<Animal> animaisAssociados = animalDAO.retrieveByEspecieId(especieId);

        if (animaisAssociados.isEmpty()) {
            especieDAO.delete(especieToDelete);
            carregaDados(model, table, "especie");
        } else {
            JOptionPane.showMessageDialog(null, "A exclusão da espécie não foi realizada pois ela está associada a um ou mais animais");
        }
    }

    public void deleteExame(int exameId, DefaultTableModel model, JTable table) {
        ExameDAO exameDAO = new ExameDAO();
        Exame exameToDelete = exameDAO.retrieveById(exameId);
        exameDAO.delete(exameToDelete);
        carregaDados(model, table, "exame");
    }
    
    public void deleteVeterinario(int vetId, DefaultTableModel model, JTable table) {
        VetDAO vetDAO = new VetDAO();
        Vet vetToDelete = vetDAO.retrieveById(vetId);

        ConsultaDAO consultaDAO = new ConsultaDAO();
        List<Consulta> consultasAssociadas = consultaDAO.retrieveByVetId(vetId);

        if (consultasAssociadas.isEmpty()) {
            vetDAO.delete(vetToDelete);
            carregaDados(model, table, "vet");
        } else {
            JOptionPane.showMessageDialog(null, "A exclusão do veterinário não foi realizada pois ele está associado a uma ou mais consultas");
        }
    }
    
    public void filtrarAnimais(DefaultTableModel model, String nomeAnimal, String nomeCliente) {
        model.setRowCount(0); 
        try {
            Connection conn = DAO.getConnection();
            String sql = "SELECT animal.id, animal.nome AS nome_animal, animal.anoNasc, animal.sexo, especie.nome AS nome_especie, cliente.nome AS nome_cliente FROM animal INNER JOIN especie ON animal.id_especie = especie.id INNER JOIN cliente ON animal.id_cliente = cliente.id WHERE animal.nome LIKE ? AND cliente.nome LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeAnimal + "%");
            stmt.setString(2, "%" + nomeCliente + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("nome_animal"), rs.getString("anoNasc"), rs.getString("sexo"), rs.getString("nome_especie"), rs.getString("nome_cliente")});
            }
        } catch (SQLException e) {
        }
    }

    public void filtrarClientes(DefaultTableModel model, String nomeCliente, String telefoneCliente) {
        model.setRowCount(0); 
        try {
            Connection conn = DAO.getConnection();
            String sql = "SELECT * FROM cliente WHERE nome LIKE ? AND telefone LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeCliente + "%");
            stmt.setString(2, "%" + telefoneCliente + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("nome"), rs.getString("endereco"), rs.getString("cep"), rs.getString("email"), rs.getString("telefone")});
            }
        } catch (SQLException e) {
        }
    }
    
    public void filtrarConsultas(DefaultTableModel model, String nomeAnimal, String nomeVeterinario) {
        model.setRowCount(0);

        try {
            Connection conn = DAO.getConnection();
            String sql = "SELECT consulta.id, consulta.data, consulta.horario, consulta.comentario, animal.nome AS nomeAnimal, vet.nome AS nomeVet, consulta.terminado " +
                         "FROM consulta " +
                         "INNER JOIN animal ON consulta.id_animal = animal.id " +
                         "INNER JOIN vet ON consulta.id_vet = vet.id " +
                         "WHERE animal.nome LIKE ? AND vet.nome LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeAnimal + "%");
            stmt.setString(2, "%" + nomeVeterinario + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String terminadoTexto = rs.getBoolean("terminado") ? "Sim" : "Não";
                model.addRow(new Object[]{
                    rs.getInt("id"), 
                    rs.getDate("data"), 
                    rs.getTime("horario"), 
                    rs.getString("comentario"), 
                    rs.getString("nomeAnimal"), 
                    rs.getString("nomeVet"), 
                    terminadoTexto
                });
            }
        } catch (SQLException e) {
        }
    }

    public void filtrarEspecies(DefaultTableModel model, String nomeEspecie) {
        model.setRowCount(0); // Limpa a tabela atual

        try {
            Connection conn = DAO.getConnection();
            String sql = "SELECT * FROM especie WHERE nome LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeEspecie + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("nome")});
            }
        } catch (SQLException e) {
        }
    }
    
    public void filtrarExames(DefaultTableModel model, String nomeExame) {
        model.setRowCount(0); 

        try {
            Connection conn = DAO.getConnection();
            String sql = "SELECT * FROM exame WHERE nome LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeExame + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("nome"), rs.getString("id_consulta")});
            }
        } catch (SQLException e) {
        }
    }
    
    public void filtrarVeterinarios(DefaultTableModel model, String nomeVet, String telefoneVet) {
        model.setRowCount(0); // Limpa a tabela atual

        try {
            Connection conn = DAO.getConnection();
            String sql = "SELECT * FROM vet " +
                         "WHERE nome LIKE ? AND telefone LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeVet + "%");
            stmt.setString(2, "%" + telefoneVet + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"), 
                    rs.getString("nome"), 
                    rs.getString("email"), 
                    rs.getString("telefone")
                });
            }
        } catch (SQLException e) {
        }
    }
    
    public void updateCliente(int id, String[] values, DefaultTableModel model, JTable table) {
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente clienteToUpdate = clienteDAO.retrieveById(id);

        clienteToUpdate.setNome(values[0]);
        clienteToUpdate.setEndereco(values[1]);
        clienteToUpdate.setCep(values[2]);
        clienteToUpdate.setEmail(values[3]);
        clienteToUpdate.setTelefone(values[4]);

        clienteDAO.update(clienteToUpdate);

        carregaDados(model, table, "cliente");
    }

    public void updateAnimal(int id, String[] values, DefaultTableModel model, JTable table) {
        AnimalDAO animalDAO = AnimalDAO.getInstance();
        Animal animalToUpdate = animalDAO.retrieveById(id);

        animalToUpdate.setNome(values[0]);
        animalToUpdate.setAnoNasc(Integer.parseInt(values[1]));
        animalToUpdate.setSexo(values[2]);

        String[] especieParts = values[3].split(":");
        int especieId = Integer.parseInt(especieParts[1]);
        animalToUpdate.setIdEspecie(especieId);

        String[] clienteParts = values[4].split(":");
        int clienteId = Integer.parseInt(clienteParts[1]);
        animalToUpdate.setIdCliente(clienteId);

        animalDAO.update(animalToUpdate);

        carregaDados(model, table, "animal");
    }

    public void updateVeterinario(int vetId, String[] values, DefaultTableModel model, JTable table) {
        VetDAO vetDAO = VetDAO.getInstance();
        Vet vetToUpdate = vetDAO.retrieveById(vetId);

        vetToUpdate.setNome(values[0]);
        vetToUpdate.setEmail(values[1]);
        vetToUpdate.setTelefone(values[2]);

        vetDAO.update(vetToUpdate);

        carregaDados(model, table, "vet");
    }
    
    public void updateConsulta(int consultaId, String[] values, DefaultTableModel model, JTable table) {
        ConsultaDAO consultaDAO = ConsultaDAO.getInstance();
        Consulta consultaToUpdate = consultaDAO.retrieveById(consultaId);
        LocalDate localDate = LocalDate.parse(values[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Date sqlDate = Date.valueOf(localDate);

        consultaToUpdate.setData(sqlDate);
        consultaToUpdate.setHorario(values[1]);
        consultaToUpdate.setComentario(values[2]);
        consultaToUpdate.setIdAnimal(Integer.parseInt(values[3].split(":")[1]));
        consultaToUpdate.setIdVet(Integer.parseInt(values[4].split(":")[1]));
        consultaToUpdate.setTerminado(Integer.parseInt(values[5]));
        
        consultaDAO.update(consultaToUpdate);

        carregaDados(model, table, "consulta");
    }

    public void updateEspecie(int id, String[] values, DefaultTableModel model, JTable table) {
        EspecieDAO especieDAO = EspecieDAO.getInstance();
        Especie especieToUpdate = especieDAO.retrieveById(id);

        especieToUpdate.setNome(values[0]);

        especieDAO.update(especieToUpdate);

        carregaDados(model, table, "especie");
    }

    public void updateExame(int id, String[] values, DefaultTableModel model, JTable table) {
        ExameDAO exameDAO = ExameDAO.getInstance();
        Exame exameToUpdate = exameDAO.retrieveById(id);

        exameToUpdate.setNome(values[0]);
        exameToUpdate.setId_consulta(Integer.parseInt(values[1]));

        exameDAO.update(exameToUpdate);

        carregaDados(model, table, "exame");
    }
    
    public void deletar(JTable table, String entityType) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int entityId = Integer.parseInt(((DefaultTableModel) table.getModel()).getValueAt(selectedRow, 0).toString());
            switch (entityType) {
                case "animal" -> deleteAnimal(entityId, (DefaultTableModel) table.getModel(), table);
                case "cliente" -> deleteCliente(entityId, (DefaultTableModel) table.getModel(), table);
                case "consulta" -> deleteConsulta(entityId, (DefaultTableModel) table.getModel(), table);
                case "especie" -> deleteEspecie(entityId, (DefaultTableModel) table.getModel(), table);
                case "exame" -> deleteExame(entityId, (DefaultTableModel) table.getModel(), table);
                case "vet" -> deleteVeterinario(entityId, (DefaultTableModel) table.getModel(), table);
            }
        }
    }
    
    public void filterRecords(DefaultTableModel model, JTable table, String filterType, String... filterParams) {
        switch (filterType) {
            case "animal" -> filtrarAnimais(model, filterParams[0], filterParams[1]);
            case "cliente" -> filtrarClientes(model, filterParams[0], filterParams[1]);
            case "consulta" -> filtrarConsultas(model, filterParams[0], filterParams[1]);
            case "especie" -> filtrarEspecies(model, filterParams[0]);
            case "exame"-> filtrarExames(model, filterParams[0]);
            case "veterinario" -> filtrarVeterinarios(model, filterParams[0], filterParams[1]);
        }
    }
    
    public int[] idJComboBoxConsulta (int consultaId) {
        ConsultaDAO consultaDAO = ConsultaDAO.getInstance();
        Consulta consultaDesejada = consultaDAO.retrieveById(consultaId);
        int ids[] = {consultaDesejada.getIdAnimal(), consultaDesejada.getIdVet(), consultaDesejada.getTerminado()};
        return ids;
    }
    
    public int[] idJComboBoxAnimal (int animalId) {
        AnimalDAO animalDAO = AnimalDAO.getInstance();
        Animal animalDesejado = animalDAO.retrieveById(animalId);
        int ids[] = {animalDesejado.getIdEspecie(), animalDesejado.getIdCliente()};
        return ids;
    }
}