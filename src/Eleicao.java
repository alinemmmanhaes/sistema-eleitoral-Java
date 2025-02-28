import java.util.HashMap;
import java.time.LocalDate;

public class Eleicao {
    private HashMap<Integer, Candidato> candidatos;
    private HashMap<Integer, Partido> partidos;
    private LocalDate data;

    public Eleicao(LocalDate data) {
        this.data = data;
        this.candidatos = new HashMap<>();
        this.partidos = new HashMap<>();
    }

    //para um partido de numero do parametro, aumenta seus votos de legenda
    public void partidoInsereVotos(int numeroPartido, int qtdVotos){
        Partido p = this.getPartido(numeroPartido);
        if(p != null) p.aumentaVotosLegenda(qtdVotos);
    }

    //para um candidato de numero do parametro, aumenta os votos do seu partido
    public void candidatoInsereVotos(int numeroCandidato, int qtdVotos){
        Candidato c = this.getCandidato(numeroCandidato);
        if(c != null){
            Partido p = c.getPartido();
            p.aumentaVotosNominal(c, qtdVotos);
        }
    }

    //insere candidato no hashmap de candidatos
    public void adicionaCandidato(Candidato c){
        this.candidatos.put(c.getNumero(), c);
    }
    
    //insere partido no hashmap de partidos
    public void adicionaPartido(Partido p){
        this.partidos.put(p.getNumero(), p);
    }

    //verifica se um partido existe
    public boolean partidoExiste(int num){
        return(partidos.get(num) != null);
    }

    //funções get, para retorno de informações armazenadas dentro de uma eleicao
    public HashMap<Integer, Candidato> getCandidatos() {
        return candidatos;
    }
    public Candidato getCandidato(int num){
        Candidato c = candidatos.get(num);
        return c;
    }
    public HashMap<Integer, Partido> getPartidos() {
        return partidos;
    }
    public Partido getPartido(int num){
        Partido p = partidos.get(num);
        return p;
    }
    public LocalDate getData() {
        return data;
    }

}
