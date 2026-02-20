public class Cidade {
    private String nome;
    private String estado;
    private int cep;

    public Cidade(String nome, String estado, int cep) {
        this.nome = nome;
        this.estado = estado;
        this.cep = cep;
    }

    public Cidade(){
        
    }

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    public int getCep() {
        return cep;
    }

    @Override
    public String toString() {
        return nome + " - " + estado + " (CEP: " + cep + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cidade cidade = (Cidade) obj;
        return cep == cidade.cep;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(cep);
    }  

}


