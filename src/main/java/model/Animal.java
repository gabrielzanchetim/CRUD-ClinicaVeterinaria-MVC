package model;

public class Animal {
    private int id;
    private String nome;
    private int anoNasc;
    private String sexo;
    private int id_especie;
    private int id_cliente;
    
    public Animal(int id, String nome, int anoNasc, String sexo, int id_especie, int id_cliente) {
        this.id = id;
        this.nome = nome;
        this.anoNasc = anoNasc;
        this.sexo = sexo;
        this.id_especie = id_especie;
        this.id_cliente = id_cliente;
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getAnoNasc() {
        return anoNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public int getIdEspecie() {
        return id_especie;
    }

    public int getIdCliente() {
        return id_cliente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoNasc(int anoNasc) {
        this.anoNasc = anoNasc;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setIdEspecie(int id_especie) {
        this.id_especie = id_especie;
    }

    public void setIdCliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    @Override
    public String toString() {
        String desc = "Animal{" +
                "nome='" + nome + '\'' +
                ", anoNasc=" + anoNasc +
                ", sexo='" + sexo + '\'' +
                ", id_especie=" + id_especie +
                ", id_cliente=" + id_cliente +
                '}';
        return desc;
    }
}
