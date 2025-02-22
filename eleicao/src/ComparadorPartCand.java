import java.util.Comparator;

public class ComparadorPartCand implements Comparator<Partido>{
    private Comparator<Candidato> comp = new ComparadorCandidatos();

    @Override
    public int compare(Partido p1, Partido p2) {
        int resposta = comp.compare(p1.getCandidatoPos(0), p2.getCandidatoPos(0));
        if(resposta == 0){
            return (p1.getNumero() - p2.getNumero());
        }
        return resposta;
    }
}