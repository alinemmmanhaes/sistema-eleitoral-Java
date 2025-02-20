import java.util.Date;

public class Candidato {
    private int numero;
    private String nome;
    private Partido partido;
    private Date nascimento;
    private Boolean eleito;
    private Boolean genero;
    private int qtdVotos;
    
    public Candidato(int numero, String nome, Partido partido, Date nascimento, Boolean eleito, Boolean genero) {
        this.numero = numero;
        this.nome = nome;
        this.partido = partido;
        this.nascimento = nascimento;
        this.eleito = eleito;
        this.genero = genero;
        this.qtdVotos = 0;
    }

    public int getNumeroCandidato() {
        return numero;
    }
    public String getNomeCandidato() {
        return nome;
    }
    public Partido getPartido() {
        return partido;
    }
    public java.util.Date getNascimentoCandidato() {
        return nascimento;
    }
    public Boolean getCandidatoEleito() {
        return eleito;
    }
    public Boolean getGeneroCandidato() {
        return genero;
    }
    public int getQtdVotosCandidato() {
        return qtdVotos;
    }
    public void aumentaQtdVotos(int qtdVotos) {
        this.qtdVotos += qtdVotos;
    }
}
