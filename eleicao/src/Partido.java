import java.util.HashMap;

public class Partido {
    private int numero;
    private String sigla;
    private Boolean federacao;
    private int qtdVotos;
    private int qtdVotosLegenda;
    private int candidatosEleitos;
    private HashMap<Integer, Candidato> candidatos;

    public Partido(int numero, String sigla, Boolean federacao) {
        this.numero = numero;
        this.sigla = sigla;
        this.federacao = federacao;
        this.qtdVotos = 0;
        this.qtdVotosLegenda = 0;
        this.candidatosEleitos = 0;
        this.candidatos = new HashMap<>();
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

    public void calculaQtdCandidatosEleitos(){
        for (Candidato c : candidatos.values()) {
            if(c.getEleito()) candidatosEleitos++;
        }
    }

    @Override
    public String toString() {
        String saida = sigla + " - " + numero + ", " + qtdVotos;

        if(qtdVotos <2){
            saida += " voto (";
        }
        else saida += " votos (";
        saida += (qtdVotos-qtdVotosLegenda);

        if((qtdVotos-qtdVotosLegenda) <2){
            saida += " nominal e ";
        }
        else saida += " nominais e ";

        saida += qtdVotosLegenda + " de legenda), " + candidatosEleitos;

        if(candidatosEleitos <2){
            saida += " candidato eleito";
        }
        else saida += " candidatos eleitos";

        return saida ;
    }
    
}