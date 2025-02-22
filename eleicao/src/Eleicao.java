import java.util.Collections;
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
    private List<Candidato> candidatos2;
    private List<Partido> partidos2;

    public Eleicao(LocalDate data) {
        this.vagas = 0;
        this.data = data;
        this.candidatos = new HashMap<>();
        this.partidos = new HashMap<>();
        this.eleitosCandidatos = new LinkedList<>();
        this.candidatos2 = new LinkedList<>();
        this.partidos2 = new LinkedList<>();
    }

    public void adicionaCandidato(Candidato c){
        this.candidatos.put(c.getNumero(), c);
    }

    public void adicionaPartido(Partido p){
        this.partidos.put(p.getNumero(), p);
    }

    public void relatorio1(){
        candidatos2.addAll(candidatos.values());
        for (Candidato c : candidatos2) {
            if(c.getEleito()){
                eleitosCandidatos.add(c);
                this.vagas++;
            }
        }

        System.out.println("Número de vagas: " + this.vagas + "\n");

    }

    public void relatorio2(){
        Collections.sort(eleitosCandidatos, new ComparadorCandidatos());
        int i = 1;
        System.out.println("Vereadores eleitos:");
        for (Candidato c : eleitosCandidatos) {
            System.out.print(i + " - ");
            if(c.getPartido().getFederacao()) System.out.print("*");
            System.out.println(c);
            i++;
        }
        System.out.println();
    }

    public void relatorio3(){
        Collections.sort(candidatos2, new ComparadorCandidatos());
        int i = 1;
        System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");
        for (Candidato c : candidatos2) {
            System.out.print(i + " - ");
            if(c.getPartido().getFederacao()) System.out.print("*");
            System.out.println(c);

            if(i == this.vagas) break;
            i++;
        }
        System.out.println();
    }

    public void relatorio4(){
        int i = 1;
        System.out.println("Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:");
        for (Candidato c : candidatos2) {
            if(!(eleitosCandidatos.contains(c))){
                System.out.print(i + " - ");
                if(c.getPartido().getFederacao()) System.out.print("*");
                System.out.println(c);
            }
            if(i == this.vagas) break;
            i++;
        }
        System.out.println();
    }

    public void relatorio5(){
        int i = 1;
        System.out.println("Eleitos, que se beneficiaram do sistema proporcional:");
        System.out.println("(com sua posição no ranking de mais votados)");
        for (Candidato c : candidatos2) {
            if(i <= vagas) continue;
            i++;
            if(c.getEleito()){
                System.out.print(i + " - ");
                if(c.getPartido().getFederacao()) System.out.print("*");
                System.out.println(c);
            }
        }
        System.out.println();
    }

    public void relatorio6(){
        partidos2.addAll(partidos.values());
        //sort()
        System.out.println("Votação dos partidos e número de candidatos eleitos:");
        for (Partido p : partidos2) {
            p.calculaQtdCandidatosEleitos();
            System.out.println(p);
        }
        System.out.println();
    }

    public void relatorio7(){
        //votos partido != 0
        //candidatos partido != 0
        //ordena candidatos partido
        //ultimo tem que ter votos
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
