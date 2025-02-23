import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Candidato {
    private int numero;
    private String nome;
    private Partido partido;
    private LocalDate nascimento;
    private int idade;
    private Boolean eleito;
    private int genero;
    private int qtdVotos;
    
    public Candidato(int numero, String nome, Partido partido, LocalDate nascimento, Boolean eleito, int genero) {
        this.numero = numero;
        this.nome = nome;
        this.partido = partido;
        this.nascimento = nascimento;
        this.eleito = eleito;
        this.genero = genero;
        this.qtdVotos = 0;

        this.partido.adicionaCandidato(this);
    }

    public void calculaIdade(LocalDate hoje){
        this.idade = (int) ChronoUnit.YEARS.between(hoje, this.nascimento);
    }

    public int getNumero() {
        return numero;
    }
    public String getNome() {
        return nome;
    }
    public Partido getPartido() {
        return partido;
    }
    public LocalDate getNascimento() {
        return nascimento;
    }
    public int getIdade(){
        return idade;
    }
    public Boolean getEleito() {
        return eleito;
    }
    public int getGenero() {
        return genero;
    }
    public int getQtdVotos() {
        return qtdVotos;
    }
    
    public void aumentaQtdVotos(int qtdVotos) {
        this.qtdVotos += qtdVotos;
    }

    @Override
    public String toString() {
        return nome + " (" + partido.getSigla() + ", " + qtdVotos + " votos)\n";
    }
}