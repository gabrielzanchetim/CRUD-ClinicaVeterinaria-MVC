package model;

import java.sql.Date;

public class Consulta {
    private int id;
    private Date data;
    private String horario;
    private String comentario;
    private int id_animal;
    private int id_vet;
    private int terminado;

    public Consulta(int id, Date data, String horario, String comentario, int id_animal, int id_vet, int terminado) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.comentario = comentario;
        this.id_animal = id_animal;
        this.id_vet = id_vet;
        this.terminado = terminado;
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public String getComentario() {
        return comentario;
    }

    public int getIdAnimal() {
        return id_animal;
    }

    public int getIdVet() {
        return id_vet;
    }

    public int getTerminado() {
        return terminado;
    }
    
    public void setData(Date data) {
        this.data = data;
    }

    public void setIdAnimal(int id_animal) {
        this.id_animal = id_animal;
    }
    
    public void setIdVet(int id_vet) {
        this.id_vet = id_vet;
    }
    
    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public void setTerminado(int terminado) {
        this.terminado = terminado;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "data=" + data +
                ", horario='" + horario + '\'' +
                ", comentario='" + comentario + '\'' +
                ", id_animal=" + id_animal +
                ", id_vet=" + id_vet +
                ", terminado=" + terminado +
                '}';
    }
}
