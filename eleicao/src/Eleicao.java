import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.time.LocalDate;

public class Eleicao {
    private HashMap<Integer, Candidato> candidatos;
    private HashMap<Integer, Partido> partidos;
    private int vagas;
    private LocalDate data;
    private List<Candidato> eleitosCandidatos;

    public Eleicao(LocalDate data) {
        this.vagas = 0;
        this.data = data;
        this.candidatos = new HashMap<>();
        this.partidos = new HashMap<>();
        this.eleitosCandidatos = new LinkedList<>();
    }

    public void adicionaCandidato(Candidato c){
        this.candidatos.put(c.getNumero(), c);
    }

    public void adicionaPartido(Partido p){
        this.partidos.put(p.getNumero(), p);
    }

    public void relatorio1(){
        //listCandidatos.addAll(candidatos.values());
        for (Candidato c : this.candidatos.values()) {
            if(c.getEleito()){
                eleitosCandidatos.add(c);
                this.vagas++;
            }
        }

        System.out.println("Número de vagas: " + this.vagas + "\n");

    }

    public void relatorio2(){
        int i = 1;
        System.out.println("Vereadores eleitos:");
        for (Candidato c : eleitosCandidatos) {
            System.out.println(i + " - " + c);
            i++;
        }
        System.out.println();
    }

    public void relatorio3(){
        //sort()
        int i = 1;
        System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");
        for (Candidato c : candidatos.values()) {
            System.out.println(i + " - " + c);
            if(i == this.vagas) break;
            i++;
        }
        System.out.println();
    }

    public void relatorio4(){
        //sort()
        int i = 1;
        System.out.println("Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:");
        for (Candidato c : candidatos.values()) {
            if(!(eleitosCandidatos.contains(c))){
                System.out.println(i + " - " + c);
            }
            if(i == this.vagas) break;
            i++;
        }
        System.out.println();
    }

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

    public boolean partidoExiste(int num){
        return(partidos.get(num) != null);
    }

    public int getVagas() {
        return vagas;
    }

    public LocalDate getData() {
        return data;
    }

}
