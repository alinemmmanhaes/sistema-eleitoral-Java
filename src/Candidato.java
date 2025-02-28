import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;

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

        this.partido.adicionaCandidato(this); //adiciona candidato ao seu partido, assim um terá acesso ao outro
    }

    //funções get, para retorno de informações armazenadas dentro de um candidato
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
    
    //aumenta a quantidade de votos armazenada no candidato
    public void aumentaQtdVotos(int qtdVotos) {
        this.qtdVotos += qtdVotos;
    }
    //calcula a idade do candidato, comparando a sua data de nascimento com hoje (data da eleição) 
    public void calculaIdade(LocalDate hoje){
        this.idade = Period.between(this.nascimento, hoje).getYears();
    }

    //formato de representação de um candidato em string, utilizado em varios relatórios para printar candidatos
    @Override
    public String toString() {
        NumberFormat brFormat = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));

        String s = nome + " (" + partido.getSigla() + ", " + brFormat.format(qtdVotos);
        if(qtdVotos < 2) s += " voto)";
        else s += " votos)";
        return s;
    }
}