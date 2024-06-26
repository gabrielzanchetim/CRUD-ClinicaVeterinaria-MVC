package model;

public class Exame {
    private int id;
    private String nome;
    private int id_consulta;

    public Exame(int id, String nome, int id_consulta) {
        this.id = id;
        this.nome = nome;
        this.id_consulta = id_consulta;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdConsulta() {
        return id_consulta;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setId_consulta (int id_consulta) {
        this.id_consulta = id_consulta;
    }

    @Override
    public String toString() {
        return "Exame{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
