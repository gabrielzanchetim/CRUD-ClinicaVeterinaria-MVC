package model;

public class Cliente {
    private int id;
    private String nome;
    private String endereco;
    private String cep;
    private String email;
    private String telefone;
    
    //private List<Animal> animais;
    
// Bug001: A ordem dos parâmetros no construtor estava trocada, ao carregar os dados novamente do banco os campos ficavam
//   invertidos na JTable

    public Cliente(int id, String nome, String endereco, String cep, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.email = email;
        this.telefone = telefone;             
    }
    
    public int getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email){
        if(!email.equals("")){
            this.email = email;
        }
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    @Override
    public String toString() {        
        String desc = "Cliente{" + "nome=" + nome + ", endereco=" + endereco + ", cep=" + cep + ", email=" + email+ ", telefone=" + telefone + '}';
        return desc;
    }    
}
