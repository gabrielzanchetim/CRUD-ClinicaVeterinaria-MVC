/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import controller.Controller;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Gabriel
 */
public class Principal extends javax.swing.JFrame {
    private final Controller controller;
    
    private void iniciaListeners() {
        
        opcoesBanco.addChangeListener((ChangeEvent e) -> {
            int selectedIndex = opcoesBanco.getSelectedIndex();
            switch (selectedIndex) {
                case 0 -> preencheDadosAnimal();
                case 1 -> preencheDadosCliente();
                case 2 -> preencheDadosConsulta();
                case 3 -> preencheDadosEspecie();
                case 4 -> preencheDadosExame();
                case 5 -> preencheDadosVeterinario();
            }
        });     
        
        botaoExcluirAnimal.addActionListener((ActionEvent e) -> {
            controller.deletar(tabelaAnimal, "animal");
        });

        botaoExcluirCliente.addActionListener((ActionEvent e) -> {
            controller.deletar(tabelaCliente, "cliente");
        });

        botaoExcluirConsulta.addActionListener((ActionEvent e) -> {
            controller.deletar(tabelaConsulta, "consulta");
        });

        botaoExcluirEspecie.addActionListener((ActionEvent e) -> {
            controller.deletar(tabelaEspecie, "especie");
        });

        botaoExcluirExame.addActionListener((ActionEvent e) -> {
            controller.deletar(tabelaExame, "exame");
        });

        botaoExcluirVet.addActionListener((ActionEvent e) -> {
            controller.deletar(tabelaVeterinario, "vet");
        });
        
        botaoPesquisarFiltroAnimal.addActionListener((ActionEvent e) -> {
            controller.filterRecords(
                    (DefaultTableModel) tabelaAnimal.getModel(),
                    tabelaAnimal,
                    "animal",
                    campoNomeAnimalConsultaAnimal.getText(),
                    campoNomeClienteConsultaAnimal.getText()
            );
        });
        
        botaoPesquisarCliente.addActionListener((ActionEvent e) -> {
            controller.filterRecords(
                    (DefaultTableModel) tabelaCliente.getModel(),
                    tabelaCliente,
                    "cliente",
                    campoNomeClienteConsultaCliente.getText(),
                    campoTelefoneConsultaCliente.getText()
            );
        });
        
        botaoPesquisarFiltroConsulta.addActionListener((ActionEvent e) -> {
            controller.filterRecords(
                    (DefaultTableModel) tabelaConsulta.getModel(),
                    tabelaConsulta,
                    "consulta",
                    campoNomeAnimalConsultaConsulta.getText(),
                    campoVeterinárioConsultaConsulta.getText()
            );
        });

        botaoPesquisarFiltroEspecie.addActionListener((ActionEvent e) -> {
            controller.filterRecords(
                    (DefaultTableModel) tabelaEspecie.getModel(),
                    tabelaEspecie,
                    "especie",
                    campoEspecieConsultaEspecie.getText()
            );
        });

        botaoPesquisarFiltroExame.addActionListener((ActionEvent e) -> {
            controller.filterRecords(
                    (DefaultTableModel) tabelaExame.getModel(),
                    tabelaExame,
                    "exame",
                    campoExameConsultaExame.getText()
            );
        });
        
        botaoPesquisarVeterinario.addActionListener((ActionEvent e) -> {
            controller.filterRecords(
                    (DefaultTableModel) tabelaVeterinario.getModel(),
                    tabelaVeterinario,
                    "veterinario",
                    campoNomeVeterinárioFiltro.getText(),
                    campoTelefoneVeterinárioFiltro.getText()
            );
        });
            
        botaoCadastrarNovoCliente.addActionListener((ActionEvent e) -> {
            String[] values = getCamposCliente();
            if (verificaPreenchimento(values[0]) && verificaPreenchimento(values[1]) && verificaCepCliente(values[2]) && verificaEmail(values[3]) && verificaTelefone(values[4])) {
                if ("Salvar alterações".equals(botaoCadastrarNovoCliente.getText())) {
                    int selectedRow = tabelaCliente.getSelectedRow();
                    if (selectedRow >= 0) {
                        int clienteId = Integer.parseInt(tabelaCliente.getModel().getValueAt(selectedRow, 0).toString());
                        controller.updateCliente(clienteId, values, (DefaultTableModel) tabelaCliente.getModel(), tabelaCliente);
                        alternarTextoBotao(botaoCadastrarNovoCliente); 
                        limpaTextoCliente();
                    }
                } else {
                    criaNovo("cliente", values);
                }
            }
        });
     
        botaoEditarCliente.addActionListener((ActionEvent e) -> {
            preencherClienteSelecionado(tabelaCliente.getSelectedRow());
        });
        
        botaoCadastrarNovoAnimal.addActionListener((ActionEvent e) -> {
            String[] values = getCamposAnimal();
            if(verificaPreenchimento(values[0]) && verificaAnoNascimentoAnimal(values[1])) {
                if ("Salvar alterações".equals(botaoCadastrarNovoAnimal.getText())) {
                    int selectedRow = tabelaAnimal.getSelectedRow();
                    if (selectedRow >= 0) {
                        int animalId = Integer.parseInt(tabelaAnimal.getModel().getValueAt(selectedRow, 0).toString());
                        controller.updateAnimal(animalId, values, (DefaultTableModel) tabelaAnimal.getModel(), tabelaAnimal);
                        alternarTextoBotao(botaoCadastrarNovoAnimal);
                        limpaTextoAnimal();
                    }
                } else {
                    criaNovo("animal", values);
                }
            }
        });
      
        botaoEditarAnimal.addActionListener((ActionEvent e) -> {
            preencherAnimalSelecionado(tabelaAnimal.getSelectedRow());
        });
        
        botaoCadastrarNovaConsulta.addActionListener((ActionEvent e) -> {
            String[] values = getCamposConsulta();
            if (verificaDataConsulta (values[0]) && verificaHorarioConsulta(values[1])) {
                
            }
            if ("Salvar alterações".equals(botaoCadastrarNovaConsulta.getText())) {
                int selectedRow = tabelaConsulta.getSelectedRow();
                if (selectedRow >= 0) {
                    int consultaId = Integer.parseInt(tabelaConsulta.getModel().getValueAt(selectedRow, 0).toString());
                    controller.updateConsulta(consultaId, values, (DefaultTableModel) tabelaConsulta.getModel(), tabelaConsulta);
                    alternarTextoBotao(botaoCadastrarNovaConsulta);
                    limpaTextoConsulta();
                }
            } else {
                criaNovo("consulta", values);
            }
        });
        
        botaoEditarConsulta.addActionListener((ActionEvent e) -> {
            preencherConsultaSelecionada(tabelaConsulta.getSelectedRow());
        });
        
        botaoCadastrarNovoVeterinario.addActionListener((ActionEvent e) -> {
            String[] values = getCamposVeterinario();
            if (verificaPreenchimento(values[0]) && verificaEmail(values[1]) && verificaTelefone(values[2])) {
                if ("Salvar alterações".equals(botaoCadastrarNovoVeterinario.getText())) {
                    int selectedRow = tabelaVeterinario.getSelectedRow();
                    if (selectedRow >= 0) {
                        int vetId = Integer.parseInt(tabelaVeterinario.getModel().getValueAt(selectedRow, 0).toString());
                        controller.updateVeterinario(vetId, values, (DefaultTableModel) tabelaVeterinario.getModel(), tabelaVeterinario);
                        alternarTextoBotao(botaoCadastrarNovoVeterinario);
                        limpaTextoVeterinario();
                    }
                } else {
                    criaNovo("vet", values);
                }
            }
        });
        
        botaoEditarVet.addActionListener((ActionEvent e) -> {
            preencherVeterinarioSelecionado(tabelaVeterinario.getSelectedRow());
        });

        botaoCadastrarNovaEspecie.addActionListener((ActionEvent e) -> {
            String[] values = getCamposEspecie();
            if (verificaPreenchimento(values[0])) {
                if ("Salvar alterações".equals(botaoCadastrarNovaEspecie.getText())) {
                    int selectedRow = tabelaEspecie.getSelectedRow();
                    if (selectedRow >= 0) {
                        int especieId = Integer.parseInt(tabelaEspecie.getModel().getValueAt(selectedRow, 0).toString());
                        controller.updateEspecie(especieId, values, (DefaultTableModel) tabelaEspecie.getModel(), tabelaEspecie);
                        alternarTextoBotao(botaoCadastrarNovaEspecie); // onde 'seuBotao' é o JButton que você deseja alterar
                        limpaTextoEspecie();
                    }
                } else {
                    criaNovo("especie", values);
                }
            }
        });
        
        botaoEditarEspecie.addActionListener((ActionEvent e) -> {
            preencherEspecieSelecionada(tabelaEspecie.getSelectedRow());
        });
        
        botaoCadastrarNovoExame.addActionListener((ActionEvent e) -> {
            String[] values = getCamposExame();
            if (verificaPreenchimento(values[0])) {
                if ("Salvar alterações".equals(botaoCadastrarNovoExame.getText())) {
                    int selectedRow = tabelaExame.getSelectedRow();
                    if (selectedRow >= 0) {
                        int exameId = Integer.parseInt(tabelaExame.getModel().getValueAt(selectedRow, 0).toString());
                        controller.updateExame(exameId, values, (DefaultTableModel) tabelaExame.getModel(), tabelaExame);
                        alternarTextoBotao(botaoCadastrarNovoExame);
                        limpaTextoExame();
                    }
                } else {
                    criaNovo("exame", values);
                }
            }

        });
        
        botaoEditarExame.addActionListener((ActionEvent e) -> {
            preencherExameSelecionado(tabelaExame.getSelectedRow());
        });
    }
    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        iniciaListeners();
        this.controller = new Controller();
        preencheDadosAnimal();     
    }     
    
    private void preencheDadosAnimal() {
        controller.carregaDados((DefaultTableModel) tabelaAnimal.getModel(), tabelaAnimal, "animal");
        controller.preencheJBoxAnimal(campoSexoAnimalCadastro, campoEspecieAnimalCadastro, campoClienteAnimalCadastro);
    }

    private void preencheDadosCliente() {
        controller.carregaDados((DefaultTableModel) tabelaCliente.getModel(), tabelaCliente, "cliente");
    }
    
    private void preencheDadosConsulta() {
        controller.carregaDados((DefaultTableModel) tabelaConsulta.getModel(), tabelaConsulta, "consulta");
        controller.preencheJBoxConsulta(campoTerminadoConsultaCadastro, campoVeterinárioConsultaCadastro, campoAnimalConsultaCadastro);
    }
    
    private void preencheDadosEspecie() {
        controller.carregaDados((DefaultTableModel) tabelaEspecie.getModel(), tabelaEspecie, "especie");
    }
    
    private void preencheDadosExame() {
        controller.carregaDados((DefaultTableModel) tabelaExame.getModel(), tabelaExame, "exame");
        controller.preencheJBoxExame(campoIDConsultaCadastroExame);
    }
    
    private void preencheDadosVeterinario() {
        controller.carregaDados((DefaultTableModel) tabelaVeterinario.getModel(), tabelaVeterinario, "vet");
    }
    
    private void limpaTextoVeterinario() {
        campoNomeVeterinarioCadastro.setText("");
        campoTelefoneVeterinarioCadastro.setText("");
        campoEmailVeterinarioCadastro.setText("");
    }
    
    private void limpaTextoExame() {
        campoNomeExameCadastro.setText("");
    }
    
    private void limpaTextoEspecie() {
        campoNomeEspecieCadastro.setText("");
    }
    
    private void limpaTextoConsulta() {
        campoDataConsultaCadastro.setText("");
        campoHorarioConsultaCadastro.setText("");
        campoComentarioConsultaCadastro.setText("");
        campoVeterinárioConsultaCadastro.setSelectedIndex(0);
        campoAnimalConsultaCadastro.setSelectedIndex(0);
        campoTerminadoConsultaCadastro.setSelectedIndex(0);
    }
    
    private void limpaTextoCliente() {
        campoNomeClienteCadastro.setText("");
        campoTelefoneClienteCadastro.setText("");
        campoEndereçoClienteCadastro.setText("");
        campoCEPClienteCadastro.setText("");
        campoEmailClienteCadastro.setText("");
    }
    
    private void limpaTextoAnimal() {
        campoNomeAnimalCadastro.setText("");
        campoDataAnimalCadastro.setText("");
    }
    
    private void criaNovo(String tableName, String[] values) {
        switch (tableName) {
            case "vet" -> {
                controller.criaNovoVet(values, (DefaultTableModel) tabelaVeterinario.getModel(), tabelaVeterinario);
                limpaTextoVeterinario();
            }
            case "exame" -> {
                controller.criaNovoExame(values, (DefaultTableModel) tabelaExame.getModel(), tabelaExame);
                limpaTextoExame();
            }
            case "especie" -> {
                controller.criaNovaEspecie(values, (DefaultTableModel) tabelaEspecie.getModel(), tabelaEspecie);
                limpaTextoEspecie();
            }
            case "consulta" -> {
                controller.criaNovaConsulta(values, (DefaultTableModel) tabelaConsulta.getModel(), tabelaConsulta);
                limpaTextoConsulta();
            }
            case "cliente" -> {
                controller.criaNovoCliente(values, (DefaultTableModel) tabelaCliente.getModel(), tabelaCliente);
                limpaTextoCliente();
            }
            case "animal" -> {
                controller.criaNovoAnimal(values, (DefaultTableModel) tabelaAnimal.getModel(), tabelaAnimal);
                limpaTextoAnimal();
            }
        }
    }
    
    private String[] getCamposCliente() {
        return new String[] {
            campoNomeClienteCadastro.getText(),
            campoEndereçoClienteCadastro.getText(),
            campoCEPClienteCadastro.getText(),
            campoEmailClienteCadastro.getText(),
            campoTelefoneClienteCadastro.getText()
        };
    }
    
    private void preencherClienteSelecionado(int selectedRow) {
        if (selectedRow >= 0) {
            String nome = tabelaCliente.getModel().getValueAt(selectedRow, 1).toString();
            String endereco = tabelaCliente.getModel().getValueAt(selectedRow, 2).toString();
            String cep = tabelaCliente.getModel().getValueAt(selectedRow, 3).toString();
            String email = tabelaCliente.getModel().getValueAt(selectedRow, 4).toString();
            String telefone = tabelaCliente.getModel().getValueAt(selectedRow, 5).toString();

            campoNomeClienteCadastro.setText(nome);
            campoEndereçoClienteCadastro.setText(endereco);
            campoCEPClienteCadastro.setText(cep);
            campoEmailClienteCadastro.setText(email);
            campoTelefoneClienteCadastro.setText(telefone);

            alternarTextoBotao(botaoCadastrarNovoCliente);
        }
    }
    
    private String[] getCamposAnimal() {
        return new String[] {
            campoNomeAnimalCadastro.getText(),
            campoDataAnimalCadastro.getText(),
            campoSexoAnimalCadastro.getSelectedItem().toString(),
            campoEspecieAnimalCadastro.getSelectedItem().toString(),
            campoClienteAnimalCadastro.getSelectedItem().toString()
        };
    }
    
    private void preencherAnimalSelecionado(int selectedRow) {
        if (selectedRow >= 0) {
                    // Obter dados da consulta selecionada
                    int animalID = Integer.parseInt(tabelaAnimal.getModel().getValueAt(selectedRow, 0).toString());
                    String nome = tabelaAnimal.getModel().getValueAt(selectedRow, 1).toString();
                    String anoNasc = tabelaAnimal.getModel().getValueAt(selectedRow, 2).toString();
                    String sexo = tabelaAnimal.getModel().getValueAt(selectedRow, 3).toString();
                    String especie = tabelaAnimal.getModel().getValueAt(selectedRow, 4).toString();
                    String cliente = tabelaAnimal.getModel().getValueAt(selectedRow, 5).toString();
                    int ids[] = controller.idJComboBoxAnimal(animalID);
                    
                    campoNomeAnimalCadastro.setText(nome);
                    campoDataAnimalCadastro.setText(anoNasc);
                    campoSexoAnimalCadastro.setSelectedItem(sexo);
                    campoEspecieAnimalCadastro.setSelectedIndex(ids[0]-1);
                    campoClienteAnimalCadastro.setSelectedIndex(ids[1]-1);

                    alternarTextoBotao(botaoCadastrarNovoAnimal);
        }
    }
    
    private String[] getCamposConsulta() {
        return new String[] {
                            campoDataConsultaCadastro.getText(), 
                            campoHorarioConsultaCadastro.getText(),
                            campoComentarioConsultaCadastro.getText(),
                            campoAnimalConsultaCadastro.getSelectedItem().toString(),
                            campoVeterinárioConsultaCadastro.getSelectedItem().toString(),
                            campoTerminadoConsultaCadastro.getSelectedItem().toString()
        };
    }
    
    private void preencherConsultaSelecionada(int selectedRow) {
        if (selectedRow >= 0) {
                    int consultaId = Integer.parseInt(tabelaConsulta.getModel().getValueAt(selectedRow, 0).toString());
                    String data = tabelaConsulta.getModel().getValueAt(selectedRow, 1).toString();
                    String horario = tabelaConsulta.getModel().getValueAt(selectedRow, 2).toString();
                    String comentario = tabelaConsulta.getModel().getValueAt(selectedRow, 3).toString();
                    int ids[] = controller.idJComboBoxConsulta(consultaId);

                    campoDataConsultaCadastro.setText(data);
                    campoHorarioConsultaCadastro.setText(horario);
                    campoComentarioConsultaCadastro.setText(comentario);
                    campoAnimalConsultaCadastro.setSelectedIndex(ids[0]-1);
                    campoVeterinárioConsultaCadastro.setSelectedIndex(ids[1]-1);
                    campoTerminadoConsultaCadastro.setSelectedIndex(ids[2]);

                    alternarTextoBotao(botaoCadastrarNovaConsulta);
        }
    }
    
    private String[] getCamposVeterinario() {
        return new String[] {
                            campoNomeVeterinarioCadastro.getText(),
                            campoEmailVeterinarioCadastro.getText(),
                            campoTelefoneVeterinarioCadastro.getText()
        };
    }
    
    private void preencherVeterinarioSelecionado(int selectedRow) {
        if (selectedRow >= 0) {
                    int vetId = Integer.parseInt(tabelaVeterinario.getModel().getValueAt(selectedRow, 0).toString());
                    String nome = tabelaVeterinario.getModel().getValueAt(selectedRow, 1).toString();
                    String email = tabelaVeterinario.getModel().getValueAt(selectedRow, 2).toString();
                    String telefone = tabelaVeterinario.getModel().getValueAt(selectedRow, 3).toString();

                    campoNomeVeterinarioCadastro.setText(nome);
                    campoEmailVeterinarioCadastro.setText(email);
                    campoTelefoneVeterinarioCadastro.setText(telefone);

                    alternarTextoBotao(botaoCadastrarNovoVeterinario);
        }
    }
    
    private String[] getCamposEspecie() {
        return new String[] {
            campoNomeEspecieCadastro.getText()
        };
    }
    
    private void preencherEspecieSelecionada(int selectedRow) {
        if (selectedRow >= 0) {
                    String nome = tabelaEspecie.getModel().getValueAt(selectedRow, 1).toString();
                    campoNomeEspecieCadastro.setText(nome);
                    alternarTextoBotao(botaoCadastrarNovaEspecie);
        }
    }
    
    private String[] getCamposExame() {
        return new String[] {
                            campoNomeExameCadastro.getText(),
                            campoIDConsultaCadastroExame.getSelectedItem().toString()
        };
    }
    
    private void preencherExameSelecionado(int selectedRow) {
        if (selectedRow >= 0) {
                    String nome = tabelaExame.getModel().getValueAt(selectedRow, 1).toString();
                    String id_consulta = tabelaExame.getModel().getValueAt(selectedRow, 2).toString();

                    campoNomeExameCadastro.setText(nome);
                    campoIDConsultaCadastroExame.setSelectedItem(id_consulta);

                    alternarTextoBotao(botaoCadastrarNovoExame);
        }
    }
    
    private boolean verificaAnoNascimentoAnimal(String anoNasc) {
        if (anoNasc.length() != 4 || !anoNasc.matches("\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Ano de nascimento fora do padrão AAAA", "Erro de formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private boolean verificaTelefone(String telefone) {
        if (!telefone.matches("\\(\\d{2}\\) \\d{5}-\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Telefone fora do formato (NN) NNNNN-NNNN", "Erro de formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean verificaCepCliente(String cep) {
        if (!cep.matches("\\d{5}-\\d{3}")) {
            JOptionPane.showMessageDialog(this, "CEP fora do formato NNNNN-NNN", "Erro de formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean verificaEmail(String email) {
        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(this, "É necessário haver um @ no email", "Erro de formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean verificaDataConsulta(String data) {
        if (!data.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Data fora do formato AAAA-MM-DD", "Erro de formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean verificaHorarioConsulta(String horario) {
        if (!horario.matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Horário fora do formato HH:MM", "Erro de formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private boolean verificaPreenchimento (String textoDoCampo) {
        if (textoDoCampo.matches("")) {
            JOptionPane.showMessageDialog(this, "Um ou mais campos de cadastro não foram preenchidos", "Erro de preenchimento", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void alternarTextoBotao(JButton botao) {
        String textoAtual = botao.getText();
        if ("Cadastrar novo".equals(textoAtual)) {
            botao.setText("Salvar alterações");
        } else if ("Salvar alterações".equals(textoAtual)) {
            botao.setText("Cadastrar novo");
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        opcoesBanco = new javax.swing.JTabbedPane();
        painelAnimal = new javax.swing.JPanel();
        textoNomeAnimalCadastro = new javax.swing.JLabel();
        campoNomeAnimalCadastro = new javax.swing.JTextField();
        textoDataAnimalCadastro = new javax.swing.JLabel();
        campoDataAnimalCadastro = new javax.swing.JTextField();
        textoSexoAnimalCadastro = new javax.swing.JLabel();
        campoSexoAnimalCadastro = new javax.swing.JComboBox<>();
        textoEspecieAnimalCadastro = new javax.swing.JLabel();
        textoClienteAnimalCadastro = new javax.swing.JLabel();
        campoEspecieAnimalCadastro = new javax.swing.JComboBox<>();
        campoClienteAnimalCadastro = new javax.swing.JComboBox<>();
        botaoCadastrarNovoAnimal = new javax.swing.JButton();
        scrollTabelaAnimal = new javax.swing.JScrollPane();
        tabelaAnimal = new javax.swing.JTable();
        TextoCadastrar = new javax.swing.JLabel();
        TextoConsultar = new javax.swing.JLabel();
        campoNomeAnimalConsultaAnimal = new javax.swing.JTextField();
        textoNomeAnimalFiltro = new javax.swing.JLabel();
        campoNomeClienteConsultaAnimal = new javax.swing.JTextField();
        textoNomeClienteFiltro = new javax.swing.JLabel();
        botaoPesquisarFiltroAnimal = new javax.swing.JButton();
        botaoExcluirAnimal = new javax.swing.JButton();
        botaoEditarAnimal = new javax.swing.JButton();
        painelCliente = new javax.swing.JPanel();
        textoNomeClienteCadastro = new javax.swing.JLabel();
        campoNomeClienteCadastro = new javax.swing.JTextField();
        textoTelefoneClienteCadastro = new javax.swing.JLabel();
        campoTelefoneClienteCadastro = new javax.swing.JTextField();
        textoEndereçoClienteCadastro = new javax.swing.JLabel();
        textoCEPClienteCadastro = new javax.swing.JLabel();
        textoEmailClienteCadastro = new javax.swing.JLabel();
        botaoCadastrarNovoCliente = new javax.swing.JButton();
        scrollTabelaCliente = new javax.swing.JScrollPane();
        tabelaCliente = new javax.swing.JTable();
        TextoCadastrar1 = new javax.swing.JLabel();
        TextoConsultar1 = new javax.swing.JLabel();
        campoNomeClienteConsultaCliente = new javax.swing.JTextField();
        textoNomeClienteFiltro1 = new javax.swing.JLabel();
        campoTelefoneConsultaCliente = new javax.swing.JTextField();
        textoTelefoneClienteFiltro = new javax.swing.JLabel();
        botaoPesquisarCliente = new javax.swing.JButton();
        campoEmailClienteCadastro = new javax.swing.JTextField();
        campoCEPClienteCadastro = new javax.swing.JTextField();
        campoEndereçoClienteCadastro = new javax.swing.JTextField();
        botaoExcluirCliente = new javax.swing.JButton();
        botaoEditarCliente = new javax.swing.JButton();
        painelConsulta = new javax.swing.JPanel();
        textoDataConsultaCadastro = new javax.swing.JLabel();
        campoDataConsultaCadastro = new javax.swing.JTextField();
        textoHorarioConsultaCadastro = new javax.swing.JLabel();
        campoHorarioConsultaCadastro = new javax.swing.JTextField();
        textoAnimalConsultaCadastro = new javax.swing.JLabel();
        campoAnimalConsultaCadastro = new javax.swing.JComboBox<>();
        textoTerminadoConsultaCadastro = new javax.swing.JLabel();
        textoVeterinárioConsultaCadastro = new javax.swing.JLabel();
        campoTerminadoConsultaCadastro = new javax.swing.JComboBox<>();
        campoVeterinárioConsultaCadastro = new javax.swing.JComboBox<>();
        botaoCadastrarNovaConsulta = new javax.swing.JButton();
        scrollTabelaConsulta = new javax.swing.JScrollPane();
        tabelaConsulta = new javax.swing.JTable();
        TextoCadastrar2 = new javax.swing.JLabel();
        TextoConsultar2 = new javax.swing.JLabel();
        campoNomeAnimalConsultaConsulta = new javax.swing.JTextField();
        textoNomeAnimalFiltro1 = new javax.swing.JLabel();
        campoVeterinárioConsultaConsulta = new javax.swing.JTextField();
        textoNomeClienteFiltro2 = new javax.swing.JLabel();
        botaoPesquisarFiltroConsulta = new javax.swing.JButton();
        textoComentarioConsultaCadastro = new javax.swing.JLabel();
        campoComentarioConsultaCadastro = new javax.swing.JTextField();
        botaoExcluirConsulta = new javax.swing.JButton();
        botaoEditarConsulta = new javax.swing.JButton();
        painelEspecie = new javax.swing.JPanel();
        textoNomeEspecieCadastro = new javax.swing.JLabel();
        campoNomeEspecieCadastro = new javax.swing.JTextField();
        botaoCadastrarNovaEspecie = new javax.swing.JButton();
        scrollTabelaEspecie = new javax.swing.JScrollPane();
        tabelaEspecie = new javax.swing.JTable();
        TextoCadastrar3 = new javax.swing.JLabel();
        TextoConsultar3 = new javax.swing.JLabel();
        campoEspecieConsultaEspecie = new javax.swing.JTextField();
        textoNomeEspecieFiltro = new javax.swing.JLabel();
        botaoPesquisarFiltroEspecie = new javax.swing.JButton();
        botaoExcluirEspecie = new javax.swing.JButton();
        botaoEditarEspecie = new javax.swing.JButton();
        painelExame = new javax.swing.JPanel();
        scrollTabelaExame = new javax.swing.JScrollPane();
        tabelaExame = new javax.swing.JTable();
        botaoPesquisarFiltroExame = new javax.swing.JButton();
        campoExameConsultaExame = new javax.swing.JTextField();
        TextoConsultar4 = new javax.swing.JLabel();
        textoNomeExameFiltro = new javax.swing.JLabel();
        botaoCadastrarNovoExame = new javax.swing.JButton();
        campoNomeExameCadastro = new javax.swing.JTextField();
        TextoCadastrar4 = new javax.swing.JLabel();
        textoNomeExameCadastro = new javax.swing.JLabel();
        textoIDConsultaCadastroExame = new javax.swing.JLabel();
        campoIDConsultaCadastroExame = new javax.swing.JComboBox<>();
        botaoExcluirExame = new javax.swing.JButton();
        botaoEditarExame = new javax.swing.JButton();
        painelVeterinario = new javax.swing.JPanel();
        textoNomeVeterinarioCadastro = new javax.swing.JLabel();
        campoNomeVeterinarioCadastro = new javax.swing.JTextField();
        textoTelefoneVeterinarioCadastro = new javax.swing.JLabel();
        campoTelefoneVeterinarioCadastro = new javax.swing.JTextField();
        textoEmailVeterinarioCadastro = new javax.swing.JLabel();
        botaoCadastrarNovoVeterinario = new javax.swing.JButton();
        scrollTabelaVeterinario = new javax.swing.JScrollPane();
        tabelaVeterinario = new javax.swing.JTable();
        TextoCadastrar5 = new javax.swing.JLabel();
        TextoConsultar5 = new javax.swing.JLabel();
        campoNomeVeterinárioFiltro = new javax.swing.JTextField();
        textoNomeVeterinárioFiltro = new javax.swing.JLabel();
        campoTelefoneVeterinárioFiltro = new javax.swing.JTextField();
        textoTelefoneVeterinárioFiltro = new javax.swing.JLabel();
        botaoPesquisarVeterinario = new javax.swing.JButton();
        campoEmailVeterinarioCadastro = new javax.swing.JTextField();
        botaoExcluirVet = new javax.swing.JButton();
        botaoEditarVet = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 924));

        painelAnimal.setPreferredSize(new java.awt.Dimension(800, 300));

        textoNomeAnimalCadastro.setText("Nome do animal:");

        campoNomeAnimalCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeAnimalCadastroActionPerformed(evt);
            }
        });

        textoDataAnimalCadastro.setText("Ano de nascimento:");

        campoDataAnimalCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDataAnimalCadastroActionPerformed(evt);
            }
        });

        textoSexoAnimalCadastro.setText("Sexo:");

        campoSexoAnimalCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        campoSexoAnimalCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoSexoAnimalCadastroActionPerformed(evt);
            }
        });

        textoEspecieAnimalCadastro.setText("Espécie:");

        textoClienteAnimalCadastro.setText("Cliente:");

        campoEspecieAnimalCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        campoClienteAnimalCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        botaoCadastrarNovoAnimal.setText("Cadastrar novo");

        tabelaAnimal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollTabelaAnimal.setViewportView(tabelaAnimal);

        TextoCadastrar.setText("Cadastrar:");

        TextoConsultar.setText("Consultar:");

        textoNomeAnimalFiltro.setText("Nome do animal:");

        textoNomeClienteFiltro.setText("Nome do cliente:");

        botaoPesquisarFiltroAnimal.setText("🔎");

        botaoExcluirAnimal.setText("Excluir");

        botaoEditarAnimal.setText("Editar");

        javax.swing.GroupLayout painelAnimalLayout = new javax.swing.GroupLayout(painelAnimal);
        painelAnimal.setLayout(painelAnimalLayout);
        painelAnimalLayout.setHorizontalGroup(
            painelAnimalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAnimalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelAnimalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelAnimalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(painelAnimalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelAnimalLayout.createSequentialGroup()
                                .addComponent(botaoCadastrarNovoAnimal)
                                .addGap(336, 336, 336))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelAnimalLayout.createSequentialGroup()
                                .addComponent(TextoCadastrar)
                                .addGap(363, 363, 363))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelAnimalLayout.createSequentialGroup()
                                .addComponent(TextoConsultar)
                                .addGap(364, 364, 364))))
                    .addGroup(painelAnimalLayout.createSequentialGroup()
                        .addGroup(painelAnimalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelAnimalLayout.createSequentialGroup()
                                .addComponent(textoNomeAnimalCadastro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(campoNomeAnimalCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textoDataAnimalCadastro)
                                .addGap(18, 18, 18)
                                .addComponent(campoDataAnimalCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelAnimalLayout.createSequentialGroup()
                                .addComponent(textoSexoAnimalCadastro)
                                .addGap(18, 18, 18)
                                .addComponent(campoSexoAnimalCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(116, 116, 116)
                                .addComponent(textoEspecieAnimalCadastro)
                                .addGap(18, 18, 18)
                                .addComponent(campoEspecieAnimalCadastro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(painelAnimalLayout.createSequentialGroup()
                                .addComponent(textoClienteAnimalCadastro)
                                .addGap(18, 18, 18)
                                .addComponent(campoClienteAnimalCadastro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(painelAnimalLayout.createSequentialGroup()
                                .addComponent(textoNomeAnimalFiltro)
                                .addGap(18, 18, 18)
                                .addComponent(campoNomeAnimalConsultaAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textoNomeClienteFiltro)
                                .addGap(18, 18, 18)
                                .addComponent(campoNomeClienteConsultaAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botaoPesquisarFiltroAnimal, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addComponent(scrollTabelaAnimal)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelAnimalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoEditarAnimal)
                .addGap(18, 18, 18)
                .addComponent(botaoExcluirAnimal)
                .addContainerGap())
        );
        painelAnimalLayout.setVerticalGroup(
            painelAnimalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAnimalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TextoCadastrar)
                .addGap(18, 18, 18)
                .addGroup(painelAnimalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNomeAnimalCadastro)
                    .addComponent(campoNomeAnimalCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoDataAnimalCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoDataAnimalCadastro))
                .addGap(18, 18, 18)
                .addGroup(painelAnimalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoSexoAnimalCadastro)
                    .addComponent(campoSexoAnimalCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoEspecieAnimalCadastro)
                    .addComponent(campoEspecieAnimalCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(painelAnimalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoClienteAnimalCadastro)
                    .addComponent(campoClienteAnimalCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botaoCadastrarNovoAnimal)
                .addGap(18, 18, 18)
                .addComponent(TextoConsultar)
                .addGap(18, 18, 18)
                .addGroup(painelAnimalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNomeAnimalConsultaAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNomeAnimalFiltro)
                    .addComponent(textoNomeClienteFiltro)
                    .addComponent(botaoPesquisarFiltroAnimal)
                    .addComponent(campoNomeClienteConsultaAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollTabelaAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(painelAnimalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoExcluirAnimal)
                    .addComponent(botaoEditarAnimal))
                .addGap(0, 0, 0))
        );

        opcoesBanco.addTab("Animal", painelAnimal);

        textoNomeClienteCadastro.setText("Nome do cliente:");

        campoNomeClienteCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeClienteCadastroActionPerformed(evt);
            }
        });

        textoTelefoneClienteCadastro.setText("Telefone:");

        campoTelefoneClienteCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTelefoneClienteCadastroActionPerformed(evt);
            }
        });

        textoEndereçoClienteCadastro.setText("Endereço:");

        textoCEPClienteCadastro.setText("CEP:");

        textoEmailClienteCadastro.setText("Email:");

        botaoCadastrarNovoCliente.setText("Cadastrar novo");

        tabelaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollTabelaCliente.setViewportView(tabelaCliente);

        TextoCadastrar1.setText("Cadastrar:");

        TextoConsultar1.setText("Consultar:");

        textoNomeClienteFiltro1.setText("Nome do cliente:");

        textoTelefoneClienteFiltro.setText("Telefone:");

        botaoPesquisarCliente.setText("🔎");

        campoEmailClienteCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEmailClienteCadastroActionPerformed(evt);
            }
        });

        campoCEPClienteCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCEPClienteCadastroActionPerformed(evt);
            }
        });

        campoEndereçoClienteCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEndereçoClienteCadastroActionPerformed(evt);
            }
        });

        botaoExcluirCliente.setText("Excluir");

        botaoEditarCliente.setText("Editar");
        botaoEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelClienteLayout = new javax.swing.GroupLayout(painelCliente);
        painelCliente.setLayout(painelClienteLayout);
        painelClienteLayout.setHorizontalGroup(
            painelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelClienteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(painelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelClienteLayout.createSequentialGroup()
                                .addComponent(botaoCadastrarNovoCliente)
                                .addGap(336, 336, 336))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelClienteLayout.createSequentialGroup()
                                .addComponent(TextoCadastrar1)
                                .addGap(363, 363, 363))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelClienteLayout.createSequentialGroup()
                                .addComponent(TextoConsultar1)
                                .addGap(364, 364, 364))))
                    .addGroup(painelClienteLayout.createSequentialGroup()
                        .addGroup(painelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollTabelaCliente)
                            .addGroup(painelClienteLayout.createSequentialGroup()
                                .addComponent(textoEmailClienteCadastro)
                                .addGap(18, 18, 18)
                                .addComponent(campoEmailClienteCadastro))
                            .addGroup(painelClienteLayout.createSequentialGroup()
                                .addComponent(textoNomeClienteFiltro1)
                                .addGap(18, 18, 18)
                                .addComponent(campoNomeClienteConsultaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textoTelefoneClienteFiltro)
                                .addGap(18, 18, 18)
                                .addComponent(campoTelefoneConsultaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botaoPesquisarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelClienteLayout.createSequentialGroup()
                                .addGroup(painelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelClienteLayout.createSequentialGroup()
                                        .addComponent(textoNomeClienteCadastro)
                                        .addGap(18, 18, 18)
                                        .addComponent(campoNomeClienteCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 48, Short.MAX_VALUE)
                                        .addComponent(textoTelefoneClienteCadastro))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelClienteLayout.createSequentialGroup()
                                        .addComponent(textoEndereçoClienteCadastro)
                                        .addGap(18, 18, 18)
                                        .addComponent(campoEndereçoClienteCadastro)
                                        .addGap(18, 18, 18)
                                        .addComponent(textoCEPClienteCadastro)))
                                .addGap(18, 18, 18)
                                .addGroup(painelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoCEPClienteCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(campoTelefoneClienteCadastro))))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelClienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoEditarCliente)
                .addGap(18, 18, 18)
                .addComponent(botaoExcluirCliente)
                .addContainerGap())
        );
        painelClienteLayout.setVerticalGroup(
            painelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TextoCadastrar1)
                .addGap(18, 18, 18)
                .addGroup(painelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNomeClienteCadastro)
                    .addComponent(campoNomeClienteCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTelefoneClienteCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoTelefoneClienteCadastro))
                .addGap(18, 18, 18)
                .addGroup(painelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoEndereçoClienteCadastro)
                    .addComponent(textoCEPClienteCadastro)
                    .addComponent(campoCEPClienteCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoEndereçoClienteCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(painelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoEmailClienteCadastro)
                    .addComponent(campoEmailClienteCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botaoCadastrarNovoCliente)
                .addGap(18, 18, 18)
                .addComponent(TextoConsultar1)
                .addGap(18, 18, 18)
                .addGroup(painelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNomeClienteConsultaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNomeClienteFiltro1)
                    .addComponent(textoTelefoneClienteFiltro)
                    .addComponent(botaoPesquisarCliente)
                    .addComponent(campoTelefoneConsultaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollTabelaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(painelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoExcluirCliente)
                    .addComponent(botaoEditarCliente))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        opcoesBanco.addTab("Cliente", painelCliente);

        textoDataConsultaCadastro.setText("Data:");

        campoDataConsultaCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDataConsultaCadastroActionPerformed(evt);
            }
        });

        textoHorarioConsultaCadastro.setText("Horário");

        campoHorarioConsultaCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoHorarioConsultaCadastroActionPerformed(evt);
            }
        });

        textoAnimalConsultaCadastro.setText("Animal:");

        campoAnimalConsultaCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        campoAnimalConsultaCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoAnimalConsultaCadastroActionPerformed(evt);
            }
        });

        textoTerminadoConsultaCadastro.setText("Terminado:");

        textoVeterinárioConsultaCadastro.setText("Veterinário:");

        campoTerminadoConsultaCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        campoVeterinárioConsultaCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        botaoCadastrarNovaConsulta.setText("Cadastrar novo");

        tabelaConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollTabelaConsulta.setViewportView(tabelaConsulta);

        TextoCadastrar2.setText("Cadastrar:");

        TextoConsultar2.setText("Consultar:");

        textoNomeAnimalFiltro1.setText("Nome do animal:");

        textoNomeClienteFiltro2.setText("Nome do veterinário:");

        botaoPesquisarFiltroConsulta.setText("🔎");

        textoComentarioConsultaCadastro.setText("Comentário:");

        campoComentarioConsultaCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoComentarioConsultaCadastroActionPerformed(evt);
            }
        });

        botaoExcluirConsulta.setText("Excluir");
        botaoExcluirConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirConsultaActionPerformed(evt);
            }
        });

        botaoEditarConsulta.setText("Editar");
        botaoEditarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelConsultaLayout = new javax.swing.GroupLayout(painelConsulta);
        painelConsulta.setLayout(painelConsultaLayout);
        painelConsultaLayout.setHorizontalGroup(
            painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelConsultaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelConsultaLayout.createSequentialGroup()
                                .addComponent(botaoCadastrarNovaConsulta)
                                .addGap(336, 336, 336))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelConsultaLayout.createSequentialGroup()
                                .addComponent(TextoCadastrar2)
                                .addGap(363, 363, 363))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelConsultaLayout.createSequentialGroup()
                                .addComponent(TextoConsultar2)
                                .addGap(364, 364, 364))))
                    .addGroup(painelConsultaLayout.createSequentialGroup()
                        .addGroup(painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollTabelaConsulta)
                            .addGroup(painelConsultaLayout.createSequentialGroup()
                                .addComponent(textoNomeAnimalFiltro1)
                                .addGap(18, 18, 18)
                                .addComponent(campoNomeAnimalConsultaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textoNomeClienteFiltro2)
                                .addGap(18, 18, 18)
                                .addComponent(campoVeterinárioConsultaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botaoPesquisarFiltroConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                            .addGroup(painelConsultaLayout.createSequentialGroup()
                                .addGroup(painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelConsultaLayout.createSequentialGroup()
                                        .addComponent(textoTerminadoConsultaCadastro)
                                        .addGap(18, 18, 18)
                                        .addComponent(campoTerminadoConsultaCadastro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelConsultaLayout.createSequentialGroup()
                                        .addComponent(textoAnimalConsultaCadastro)
                                        .addGap(18, 18, 18)
                                        .addComponent(campoAnimalConsultaCadastro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelConsultaLayout.createSequentialGroup()
                                        .addComponent(textoDataConsultaCadastro)
                                        .addGap(18, 18, 18)
                                        .addComponent(campoDataConsultaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(textoHorarioConsultaCadastro)
                                        .addGap(18, 18, 18)
                                        .addComponent(campoHorarioConsultaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelConsultaLayout.createSequentialGroup()
                                        .addComponent(textoVeterinárioConsultaCadastro)
                                        .addGap(18, 18, 18)
                                        .addComponent(campoVeterinárioConsultaCadastro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(painelConsultaLayout.createSequentialGroup()
                                        .addComponent(textoComentarioConsultaCadastro)
                                        .addGap(18, 18, 18)
                                        .addComponent(campoComentarioConsultaCadastro)))))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelConsultaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoEditarConsulta)
                .addGap(18, 18, 18)
                .addComponent(botaoExcluirConsulta)
                .addContainerGap())
        );
        painelConsultaLayout.setVerticalGroup(
            painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TextoCadastrar2)
                .addGap(18, 18, 18)
                .addGroup(painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoDataConsultaCadastro)
                    .addComponent(campoDataConsultaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoHorarioConsultaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoHorarioConsultaCadastro)
                    .addComponent(textoVeterinárioConsultaCadastro)
                    .addComponent(campoVeterinárioConsultaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelConsultaLayout.createSequentialGroup()
                        .addGroup(painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoAnimalConsultaCadastro)
                            .addComponent(campoAnimalConsultaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoComentarioConsultaCadastro))
                        .addGap(18, 18, 18)
                        .addGroup(painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoTerminadoConsultaCadastro)
                            .addComponent(campoTerminadoConsultaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(campoComentarioConsultaCadastro))
                .addGap(21, 21, 21)
                .addComponent(botaoCadastrarNovaConsulta)
                .addGap(18, 18, 18)
                .addComponent(TextoConsultar2)
                .addGap(18, 18, 18)
                .addGroup(painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNomeAnimalConsultaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNomeAnimalFiltro1)
                    .addComponent(textoNomeClienteFiltro2)
                    .addComponent(botaoPesquisarFiltroConsulta)
                    .addComponent(campoVeterinárioConsultaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(scrollTabelaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoExcluirConsulta)
                    .addComponent(botaoEditarConsulta))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        opcoesBanco.addTab("Consulta", painelConsulta);

        textoNomeEspecieCadastro.setText("Nome da espécie:");

        campoNomeEspecieCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeEspecieCadastroActionPerformed(evt);
            }
        });

        botaoCadastrarNovaEspecie.setText("Cadastrar novo");

        tabelaEspecie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollTabelaEspecie.setViewportView(tabelaEspecie);

        TextoCadastrar3.setText("Cadastrar:");

        TextoConsultar3.setText("Consultar:");

        textoNomeEspecieFiltro.setText("Nome da espécie:");

        botaoPesquisarFiltroEspecie.setText("🔎");

        botaoExcluirEspecie.setText("Excluir");

        botaoEditarEspecie.setText("Editar");

        javax.swing.GroupLayout painelEspecieLayout = new javax.swing.GroupLayout(painelEspecie);
        painelEspecie.setLayout(painelEspecieLayout);
        painelEspecieLayout.setHorizontalGroup(
            painelEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEspecieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEspecieLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(painelEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEspecieLayout.createSequentialGroup()
                                .addComponent(TextoCadastrar3)
                                .addGap(363, 363, 363))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEspecieLayout.createSequentialGroup()
                                .addComponent(botaoCadastrarNovaEspecie)
                                .addGap(337, 337, 337))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEspecieLayout.createSequentialGroup()
                                .addComponent(botaoEditarEspecie)
                                .addGap(18, 18, 18)
                                .addComponent(botaoExcluirEspecie)
                                .addContainerGap())))
                    .addGroup(painelEspecieLayout.createSequentialGroup()
                        .addGroup(painelEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollTabelaEspecie)
                            .addGroup(painelEspecieLayout.createSequentialGroup()
                                .addComponent(textoNomeEspecieCadastro)
                                .addGap(18, 18, 18)
                                .addComponent(campoNomeEspecieCadastro))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEspecieLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(TextoConsultar3)
                                .addGap(358, 358, 358))
                            .addGroup(painelEspecieLayout.createSequentialGroup()
                                .addComponent(textoNomeEspecieFiltro)
                                .addGap(18, 18, 18)
                                .addComponent(campoEspecieConsultaEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botaoPesquisarFiltroEspecie, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        painelEspecieLayout.setVerticalGroup(
            painelEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEspecieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TextoCadastrar3)
                .addGap(18, 18, 18)
                .addGroup(painelEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNomeEspecieCadastro)
                    .addComponent(campoNomeEspecieCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botaoCadastrarNovaEspecie)
                .addGap(18, 18, 18)
                .addComponent(TextoConsultar3)
                .addGap(18, 18, 18)
                .addGroup(painelEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoEspecieConsultaEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNomeEspecieFiltro)
                    .addComponent(botaoPesquisarFiltroEspecie))
                .addGap(18, 18, 18)
                .addComponent(scrollTabelaEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(painelEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoExcluirEspecie)
                    .addComponent(botaoEditarEspecie))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        opcoesBanco.addTab("Especie", painelEspecie);

        tabelaExame.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollTabelaExame.setViewportView(tabelaExame);

        botaoPesquisarFiltroExame.setText("🔎");

        campoExameConsultaExame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoExameConsultaExameActionPerformed(evt);
            }
        });

        TextoConsultar4.setText("Consultar:");

        textoNomeExameFiltro.setText("Nome do exame:");

        botaoCadastrarNovoExame.setText("Cadastrar novo");

        campoNomeExameCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeExameCadastroActionPerformed(evt);
            }
        });

        TextoCadastrar4.setText("Cadastrar:");

        textoNomeExameCadastro.setText("Nome do exame:");

        textoIDConsultaCadastroExame.setText("ID da consulta:");

        campoIDConsultaCadastroExame.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        botaoExcluirExame.setText("Excluir");

        botaoEditarExame.setText("Editar");

        javax.swing.GroupLayout painelExameLayout = new javax.swing.GroupLayout(painelExame);
        painelExame.setLayout(painelExameLayout);
        painelExameLayout.setHorizontalGroup(
            painelExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelExameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelExameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(painelExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelExameLayout.createSequentialGroup()
                                .addComponent(TextoCadastrar4)
                                .addGap(363, 363, 363))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelExameLayout.createSequentialGroup()
                                .addComponent(botaoCadastrarNovoExame)
                                .addGap(337, 337, 337))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelExameLayout.createSequentialGroup()
                                .addComponent(botaoEditarExame)
                                .addGap(18, 18, 18)
                                .addComponent(botaoExcluirExame)
                                .addContainerGap())))
                    .addGroup(painelExameLayout.createSequentialGroup()
                        .addGroup(painelExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollTabelaExame)
                            .addGroup(painelExameLayout.createSequentialGroup()
                                .addComponent(textoNomeExameCadastro)
                                .addGap(18, 18, 18)
                                .addComponent(campoNomeExameCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textoIDConsultaCadastroExame)
                                .addGap(18, 18, 18)
                                .addComponent(campoIDConsultaCadastroExame, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelExameLayout.createSequentialGroup()
                                .addGap(0, 400, Short.MAX_VALUE)
                                .addComponent(TextoConsultar4)
                                .addGap(358, 358, 358))
                            .addGroup(painelExameLayout.createSequentialGroup()
                                .addComponent(textoNomeExameFiltro)
                                .addGap(18, 18, 18)
                                .addComponent(campoExameConsultaExame, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botaoPesquisarFiltroExame, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        painelExameLayout.setVerticalGroup(
            painelExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelExameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TextoCadastrar4)
                .addGap(18, 18, 18)
                .addGroup(painelExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNomeExameCadastro)
                    .addComponent(campoNomeExameCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoIDConsultaCadastroExame)
                    .addComponent(campoIDConsultaCadastroExame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botaoCadastrarNovoExame)
                .addGap(18, 18, 18)
                .addComponent(TextoConsultar4)
                .addGap(18, 18, 18)
                .addGroup(painelExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoExameConsultaExame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNomeExameFiltro)
                    .addComponent(botaoPesquisarFiltroExame))
                .addGap(18, 18, 18)
                .addComponent(scrollTabelaExame, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(painelExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoExcluirExame)
                    .addComponent(botaoEditarExame))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        opcoesBanco.addTab("Exames", painelExame);

        textoNomeVeterinarioCadastro.setText("Nome do veterinário:");

        campoNomeVeterinarioCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeVeterinarioCadastroActionPerformed(evt);
            }
        });

        textoTelefoneVeterinarioCadastro.setText("Telefone:");

        campoTelefoneVeterinarioCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTelefoneVeterinarioCadastroActionPerformed(evt);
            }
        });

        textoEmailVeterinarioCadastro.setText("Email:");

        botaoCadastrarNovoVeterinario.setText("Cadastrar novo");

        tabelaVeterinario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollTabelaVeterinario.setViewportView(tabelaVeterinario);

        TextoCadastrar5.setText("Cadastrar:");

        TextoConsultar5.setText("Consultar:");

        textoNomeVeterinárioFiltro.setText("Nome do veterinário:");

        campoTelefoneVeterinárioFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTelefoneVeterinárioFiltroActionPerformed(evt);
            }
        });

        textoTelefoneVeterinárioFiltro.setText("Telefone:");

        botaoPesquisarVeterinario.setText("🔎");

        campoEmailVeterinarioCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEmailVeterinarioCadastroActionPerformed(evt);
            }
        });

        botaoExcluirVet.setText("Excluir");

        botaoEditarVet.setText("Editar");

        javax.swing.GroupLayout painelVeterinarioLayout = new javax.swing.GroupLayout(painelVeterinario);
        painelVeterinario.setLayout(painelVeterinarioLayout);
        painelVeterinarioLayout.setHorizontalGroup(
            painelVeterinarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelVeterinarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelVeterinarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelVeterinarioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(TextoCadastrar5)
                        .addGap(363, 363, 363))
                    .addGroup(painelVeterinarioLayout.createSequentialGroup()
                        .addGroup(painelVeterinarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollTabelaVeterinario)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelVeterinarioLayout.createSequentialGroup()
                                .addComponent(textoNomeVeterinarioCadastro)
                                .addGap(18, 18, 18)
                                .addComponent(campoNomeVeterinarioCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(textoTelefoneVeterinarioCadastro)
                                .addGap(18, 18, 18)
                                .addComponent(campoTelefoneVeterinarioCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelVeterinarioLayout.createSequentialGroup()
                                .addComponent(textoEmailVeterinarioCadastro)
                                .addGap(18, 18, 18)
                                .addComponent(campoEmailVeterinarioCadastro)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelVeterinarioLayout.createSequentialGroup()
                        .addGroup(painelVeterinarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelVeterinarioLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(painelVeterinarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelVeterinarioLayout.createSequentialGroup()
                                        .addComponent(botaoCadastrarNovoVeterinario)
                                        .addGap(330, 330, 330))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelVeterinarioLayout.createSequentialGroup()
                                        .addComponent(TextoConsultar5)
                                        .addGap(358, 358, 358))))
                            .addGroup(painelVeterinarioLayout.createSequentialGroup()
                                .addComponent(textoNomeVeterinárioFiltro)
                                .addGap(18, 18, 18)
                                .addComponent(campoNomeVeterinárioFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textoTelefoneVeterinárioFiltro)
                                .addGap(18, 18, 18)
                                .addComponent(campoTelefoneVeterinárioFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botaoPesquisarVeterinario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelVeterinarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoEditarVet)
                .addGap(18, 18, 18)
                .addComponent(botaoExcluirVet)
                .addContainerGap())
        );
        painelVeterinarioLayout.setVerticalGroup(
            painelVeterinarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelVeterinarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TextoCadastrar5)
                .addGap(18, 18, 18)
                .addGroup(painelVeterinarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNomeVeterinarioCadastro)
                    .addComponent(campoNomeVeterinarioCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTelefoneVeterinarioCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoTelefoneVeterinarioCadastro))
                .addGap(18, 18, 18)
                .addGroup(painelVeterinarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoEmailVeterinarioCadastro)
                    .addComponent(campoEmailVeterinarioCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botaoCadastrarNovoVeterinario)
                .addGap(18, 18, 18)
                .addComponent(TextoConsultar5)
                .addGap(18, 18, 18)
                .addGroup(painelVeterinarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNomeVeterinárioFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNomeVeterinárioFiltro)
                    .addComponent(textoTelefoneVeterinárioFiltro)
                    .addComponent(botaoPesquisarVeterinario)
                    .addComponent(campoTelefoneVeterinárioFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollTabelaVeterinario, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(painelVeterinarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoExcluirVet)
                    .addComponent(botaoEditarVet))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        opcoesBanco.addTab("Veterinario", painelVeterinario);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(opcoesBanco)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opcoesBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 675, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoEmailVeterinarioCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEmailVeterinarioCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoEmailVeterinarioCadastroActionPerformed

    private void campoTelefoneVeterinarioCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTelefoneVeterinarioCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTelefoneVeterinarioCadastroActionPerformed

    private void campoNomeVeterinarioCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeVeterinarioCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeVeterinarioCadastroActionPerformed

    private void campoNomeExameCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeExameCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeExameCadastroActionPerformed

    private void campoExameConsultaExameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoExameConsultaExameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoExameConsultaExameActionPerformed

    private void campoNomeEspecieCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeEspecieCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeEspecieCadastroActionPerformed

    private void botaoExcluirConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoExcluirConsultaActionPerformed

    private void campoComentarioConsultaCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoComentarioConsultaCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoComentarioConsultaCadastroActionPerformed

    private void campoAnimalConsultaCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoAnimalConsultaCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoAnimalConsultaCadastroActionPerformed

    private void campoHorarioConsultaCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoHorarioConsultaCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoHorarioConsultaCadastroActionPerformed

    private void campoDataConsultaCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDataConsultaCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDataConsultaCadastroActionPerformed

    private void campoEndereçoClienteCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEndereçoClienteCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoEndereçoClienteCadastroActionPerformed

    private void campoCEPClienteCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCEPClienteCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCEPClienteCadastroActionPerformed

    private void campoEmailClienteCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEmailClienteCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoEmailClienteCadastroActionPerformed

    private void campoTelefoneClienteCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTelefoneClienteCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTelefoneClienteCadastroActionPerformed

    private void campoNomeClienteCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeClienteCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeClienteCadastroActionPerformed

    private void campoTelefoneVeterinárioFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTelefoneVeterinárioFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTelefoneVeterinárioFiltroActionPerformed

    private void botaoEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoEditarClienteActionPerformed

    private void botaoEditarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoEditarConsultaActionPerformed

    private void campoSexoAnimalCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoSexoAnimalCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoSexoAnimalCadastroActionPerformed

    private void campoDataAnimalCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDataAnimalCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDataAnimalCadastroActionPerformed

    private void campoNomeAnimalCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeAnimalCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeAnimalCadastroActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TextoCadastrar;
    private javax.swing.JLabel TextoCadastrar1;
    private javax.swing.JLabel TextoCadastrar2;
    private javax.swing.JLabel TextoCadastrar3;
    private javax.swing.JLabel TextoCadastrar4;
    private javax.swing.JLabel TextoCadastrar5;
    private javax.swing.JLabel TextoConsultar;
    private javax.swing.JLabel TextoConsultar1;
    private javax.swing.JLabel TextoConsultar2;
    private javax.swing.JLabel TextoConsultar3;
    private javax.swing.JLabel TextoConsultar4;
    private javax.swing.JLabel TextoConsultar5;
    private javax.swing.JButton botaoCadastrarNovaConsulta;
    private javax.swing.JButton botaoCadastrarNovaEspecie;
    private javax.swing.JButton botaoCadastrarNovoAnimal;
    private javax.swing.JButton botaoCadastrarNovoCliente;
    private javax.swing.JButton botaoCadastrarNovoExame;
    private javax.swing.JButton botaoCadastrarNovoVeterinario;
    private javax.swing.JButton botaoEditarAnimal;
    private javax.swing.JButton botaoEditarCliente;
    private javax.swing.JButton botaoEditarConsulta;
    private javax.swing.JButton botaoEditarEspecie;
    private javax.swing.JButton botaoEditarExame;
    private javax.swing.JButton botaoEditarVet;
    private javax.swing.JButton botaoExcluirAnimal;
    private javax.swing.JButton botaoExcluirCliente;
    private javax.swing.JButton botaoExcluirConsulta;
    private javax.swing.JButton botaoExcluirEspecie;
    private javax.swing.JButton botaoExcluirExame;
    private javax.swing.JButton botaoExcluirVet;
    private javax.swing.JButton botaoPesquisarCliente;
    private javax.swing.JButton botaoPesquisarFiltroAnimal;
    private javax.swing.JButton botaoPesquisarFiltroConsulta;
    private javax.swing.JButton botaoPesquisarFiltroEspecie;
    private javax.swing.JButton botaoPesquisarFiltroExame;
    private javax.swing.JButton botaoPesquisarVeterinario;
    private javax.swing.JComboBox<String> campoAnimalConsultaCadastro;
    private javax.swing.JTextField campoCEPClienteCadastro;
    private javax.swing.JComboBox<String> campoClienteAnimalCadastro;
    private javax.swing.JTextField campoComentarioConsultaCadastro;
    private javax.swing.JTextField campoDataAnimalCadastro;
    private javax.swing.JTextField campoDataConsultaCadastro;
    private javax.swing.JTextField campoEmailClienteCadastro;
    private javax.swing.JTextField campoEmailVeterinarioCadastro;
    private javax.swing.JTextField campoEndereçoClienteCadastro;
    private javax.swing.JComboBox<String> campoEspecieAnimalCadastro;
    private javax.swing.JTextField campoEspecieConsultaEspecie;
    private javax.swing.JTextField campoExameConsultaExame;
    private javax.swing.JTextField campoHorarioConsultaCadastro;
    private javax.swing.JComboBox<String> campoIDConsultaCadastroExame;
    private javax.swing.JTextField campoNomeAnimalCadastro;
    private javax.swing.JTextField campoNomeAnimalConsultaAnimal;
    private javax.swing.JTextField campoNomeAnimalConsultaConsulta;
    private javax.swing.JTextField campoNomeClienteCadastro;
    private javax.swing.JTextField campoNomeClienteConsultaAnimal;
    private javax.swing.JTextField campoNomeClienteConsultaCliente;
    private javax.swing.JTextField campoNomeEspecieCadastro;
    private javax.swing.JTextField campoNomeExameCadastro;
    private javax.swing.JTextField campoNomeVeterinarioCadastro;
    private javax.swing.JTextField campoNomeVeterinárioFiltro;
    private javax.swing.JComboBox<String> campoSexoAnimalCadastro;
    private javax.swing.JTextField campoTelefoneClienteCadastro;
    private javax.swing.JTextField campoTelefoneConsultaCliente;
    private javax.swing.JTextField campoTelefoneVeterinarioCadastro;
    private javax.swing.JTextField campoTelefoneVeterinárioFiltro;
    private javax.swing.JComboBox<String> campoTerminadoConsultaCadastro;
    private javax.swing.JComboBox<String> campoVeterinárioConsultaCadastro;
    private javax.swing.JTextField campoVeterinárioConsultaConsulta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane opcoesBanco;
    private javax.swing.JPanel painelAnimal;
    private javax.swing.JPanel painelCliente;
    private javax.swing.JPanel painelConsulta;
    private javax.swing.JPanel painelEspecie;
    private javax.swing.JPanel painelExame;
    private javax.swing.JPanel painelVeterinario;
    private javax.swing.JScrollPane scrollTabelaAnimal;
    private javax.swing.JScrollPane scrollTabelaCliente;
    private javax.swing.JScrollPane scrollTabelaConsulta;
    private javax.swing.JScrollPane scrollTabelaEspecie;
    private javax.swing.JScrollPane scrollTabelaExame;
    private javax.swing.JScrollPane scrollTabelaVeterinario;
    private javax.swing.JTable tabelaAnimal;
    private javax.swing.JTable tabelaCliente;
    private javax.swing.JTable tabelaConsulta;
    private javax.swing.JTable tabelaEspecie;
    private javax.swing.JTable tabelaExame;
    private javax.swing.JTable tabelaVeterinario;
    private javax.swing.JLabel textoAnimalConsultaCadastro;
    private javax.swing.JLabel textoCEPClienteCadastro;
    private javax.swing.JLabel textoClienteAnimalCadastro;
    private javax.swing.JLabel textoComentarioConsultaCadastro;
    private javax.swing.JLabel textoDataAnimalCadastro;
    private javax.swing.JLabel textoDataConsultaCadastro;
    private javax.swing.JLabel textoEmailClienteCadastro;
    private javax.swing.JLabel textoEmailVeterinarioCadastro;
    private javax.swing.JLabel textoEndereçoClienteCadastro;
    private javax.swing.JLabel textoEspecieAnimalCadastro;
    private javax.swing.JLabel textoHorarioConsultaCadastro;
    private javax.swing.JLabel textoIDConsultaCadastroExame;
    private javax.swing.JLabel textoNomeAnimalCadastro;
    private javax.swing.JLabel textoNomeAnimalFiltro;
    private javax.swing.JLabel textoNomeAnimalFiltro1;
    private javax.swing.JLabel textoNomeClienteCadastro;
    private javax.swing.JLabel textoNomeClienteFiltro;
    private javax.swing.JLabel textoNomeClienteFiltro1;
    private javax.swing.JLabel textoNomeClienteFiltro2;
    private javax.swing.JLabel textoNomeEspecieCadastro;
    private javax.swing.JLabel textoNomeEspecieFiltro;
    private javax.swing.JLabel textoNomeExameCadastro;
    private javax.swing.JLabel textoNomeExameFiltro;
    private javax.swing.JLabel textoNomeVeterinarioCadastro;
    private javax.swing.JLabel textoNomeVeterinárioFiltro;
    private javax.swing.JLabel textoSexoAnimalCadastro;
    private javax.swing.JLabel textoTelefoneClienteCadastro;
    private javax.swing.JLabel textoTelefoneClienteFiltro;
    private javax.swing.JLabel textoTelefoneVeterinarioCadastro;
    private javax.swing.JLabel textoTelefoneVeterinárioFiltro;
    private javax.swing.JLabel textoTerminadoConsultaCadastro;
    private javax.swing.JLabel textoVeterinárioConsultaCadastro;
    // End of variables declaration//GEN-END:variables
}
