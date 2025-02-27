import java.text.NumberFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Partido {
    private int numero;
    private String sigla;
    private Boolean federacao;
    private int qtdVotos;
    private int qtdVotosLegenda;
    private int candidatosEleitos;
    private HashMap<Integer, Candidato> candidatos;
    private List<Candidato> listCandidatos;

    public Partido(int numero, String sigla, Boolean federacao) {
        this.numero = numero;
        this.sigla = sigla;
        this.federacao = federacao;
        this.qtdVotos = 0;
        this.qtdVotosLegenda = 0;
        this.candidatosEleitos = 0;
        this.candidatos = new HashMap<>();
        this.listCandidatos = new LinkedList<>();
    }

    public int getNumero() {
        return numero;
    }
    public String getSigla() {
        return sigla;
    }
    public Boolean getFederacao() {
        return federacao;
    }
    public int getQtdVotos() {
        return qtdVotos;
    }
    public int getQtdVotosLegenda() {
        return qtdVotosLegenda;
    }
    public int getQtdCandidatosEleitos() {
        return candidatosEleitos;
    }
    public HashMap<Integer, Candidato> getCandidatos() {
        return candidatos;
    }
    public Candidato getCandidatoPos(int i){
        return listCandidatos.get(i);
    }

    public void adicionaCandidato(Candidato c){
        this.candidatos.put(c.getNumero(), c);
    }
    public void calculaQtdCandidatosEleitos(){
        for (Candidato c : candidatos.values()) {
            if(c.getEleito()) candidatosEleitos++;
        }
    }
    public void ordenaCandidatos(){
        listCandidatos.addAll(candidatos.values());
        Collections.sort(listCandidatos, new ComparadorCandidatos());
    }
    public void aumentaVotosLegenda(int qtd){
        this.qtdVotosLegenda += qtd;
        this.qtdVotos += qtd;
    }
    public void aumentaVotosNominal(Candidato c, int qtd){
        if(this.candidatos.containsValue(c)){
            c.aumentaQtdVotos(qtd);
            this.qtdVotos += qtd;
        }
    }

    @Override
    public String toString() {
        NumberFormat brFormat = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));

        String saida = sigla + " - " + numero + ", " + brFormat.format(qtdVotos);

        if(qtdVotos <2){
            saida += " voto (";
        }
        else saida += " votos (";
        saida += brFormat.format(qtdVotos-qtdVotosLegenda);

        if((qtdVotos-qtdVotosLegenda) <2){
            saida += " nominal e ";
        }
        else saida += " nominais e ";

        saida += brFormat.format(qtdVotosLegenda) + " de legenda), " + candidatosEleitos;

        if(candidatosEleitos <2){
            saida += " candidato eleito";
        }
        else saida += " candidatos eleitos";

        return saida ;
    }
    
}