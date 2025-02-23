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

    public boolean partidoExiste(int num){
        return(partidos.get(num) != null);
    }

    public void relatorio1(){
        candidatos2.addAll(candidatos.values());
        for (Candidato c : candidatos2) {
            c.calculaIdade(this.data);
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
        Collections.sort(partidos2, new ComparadorPartidos());
        System.out.println("Votação dos partidos e número de candidatos eleitos:");
        for (Partido p : partidos2) {
            p.calculaQtdCandidatosEleitos();
            System.out.println(p);
        }
        System.out.println();
    }

    public void relatorio7(){
        List<Partido> listPartidos = new LinkedList<>();
        listPartidos.addAll(partidos2);
        for (Partido p : listPartidos) {
            if(p.getCandidatos().size() == 0){
                listPartidos.remove(p);
            }
            else p.ordenaCandidatos();
        }
        Collections.sort(listPartidos, new ComparadorPartCand());

        int i = 1;
        for (Partido p : listPartidos) {
            if(p.getQtdVotos() == 0) continue;

            Candidato c1 = p.getCandidatoPos(0);

            int size = p.getCandidatos().size();
            Candidato c2 = p.getCandidatoPos(size-1);
            while(c2.getQtdVotos() == 0){
                size--;
                c2 = p.getCandidatoPos(size-1);
            }

            System.out.println("Primeiro e último colocados de cada partido:");
            System.out.print(i + " - " + p.getSigla() + " - " + p.getNumero() + ", ");
            System.out.print(c1.getNome() + " (" + c1.getNumero() + ", " + c1.getQtdVotos() + " votos) / ");
            System.out.println(c1.getNome() + " (" + c1.getNumero() + ", " + c1.getQtdVotos() + " votos)");
            i++;
        }
        System.out.println();
    }

    public void relatorio8(){
        int menorque30 = 0, entre30e40 = 0, entre40e50 = 0, entre50e60 = 0, maiorque60 = 0;

        System.out.println("Eleitos, por faixa etária (na data da eleição):");
        for (Candidato c : eleitosCandidatos) {
            int idade = c.getIdade();
            if(idade < 30) menorque30++;
            else if(idade >= 30 && idade < 40) entre30e40++;
            else if(idade >= 40 && idade < 50) entre40e50++;
            else if(idade >= 50 && idade < 60) entre50e60++;
            else maiorque60++;
        }
        int total = eleitosCandidatos.size();
        System.out.printf("      Idade < 30: " + menorque30 + " (%.2f%%)\n", 100*((double)menorque30)/total);
        System.out.printf("30 <= Idade < 40: " + entre30e40 + " (%.2f%%)\n", 100*((double)entre30e40)/total);
        System.out.printf("40 <= Idade < 50: " + entre40e50 + " (%.2f%%)\n", 100*((double)entre40e50)/total);
        System.out.printf("50 <= Idade < 60: " + entre50e60 + " (%.2f%%)\n", 100*((double)entre50e60)/total);
        System.out.printf("60 <= Idade     : " + maiorque60 + " (%.2f%%)\n", 100*((double)maiorque60)/total);
        System.out.println();
    }

    public void relatorio9(){
        int homem = 0, mulher = 0;

        System.out.println("Eleitos, por faixa etária (na data da eleição):");
        for (Candidato c : eleitosCandidatos) {
            int genero = c.getGenero();
            if(genero == 2) homem++;
            else if(genero == 4) mulher++;
        }
        int total = eleitosCandidatos.size();
        System.out.println("Eleitos, por gênero:");
        System.out.printf("Feminino: %d (%.2f%%)\n", mulher, 100*((double)mulher)/total);
        System.out.printf("Masculino: %d (%.2f%%)\n", homem, 100*((double)homem)/total);
        System.out.println();
    }

    public void relatorio10(){
        int total = 0, legenda = 0;
        for (Partido p : partidos2) {
            total += p.getQtdVotos();
            legenda += p.getQtdVotosLegenda();
        }
        int nominal = total - legenda;
        System.out.printf("Total de votos válidos:    %d\n", total);
        System.out.printf("Total de votos nominais:   %d (%.2f%%)\n", nominal, 100*((double)nominal)/total);
        System.out.printf("Total de votos de legenda: %d (%.2f%%)\n", legenda, 100*((double)legenda)/total);
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
    public int getVagas() {
        return vagas;
    }
    public LocalDate getData() {
        return data;
    }

}
