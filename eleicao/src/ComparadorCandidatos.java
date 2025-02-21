import java.util.Comparator;

public class ComparadorCandidatos implements Comparator<Candidato>{
    @Override
    public int compare(Candidato c1, Candidato c2) {
        return -1*(c1.getQtdVotos() - c2.getQtdVotos());
    }
}