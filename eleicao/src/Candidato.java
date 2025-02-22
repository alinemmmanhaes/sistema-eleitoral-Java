import java.time.LocalDate;

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
    }

    public void calculaIdade(LocalDate hoje){

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