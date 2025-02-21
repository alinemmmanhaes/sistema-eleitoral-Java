import java.util.HashMap;

public class Partido {
    private int numero;
    private String sigla;
    private Boolean federacao;
    private int qtdVotos;
    private int qtdVotosLegenda;
    private HashMap<Integer, Candidato> candidatos;

    public Partido(int numero, String sigla, Boolean federacao) {
        this.numero = numero;
        this.sigla = sigla;
        this.federacao = federacao;
        this.qtdVotos = 0;
        this.qtdVotosLegenda = 0;
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

    public HashMap<Integer, Candidato> getCandidatos() {
        return candidatos;
    }
    
}