import java.util.Comparator;

public class ComparadorCandidatos implements Comparator<Candidato>{
    //compara dois candidados, avaliando suas quantidades de votos, e em ultimo caso, as suas idades
    @Override
    public int compare(Candidato c1, Candidato c2) {
        int diffVotos = c1.getQtdVotos() - c2.getQtdVotos();
        if(diffVotos == 0){
            return -1*(c1.getIdade() - c2.getIdade());
        }
        return -1*(diffVotos);
    }
}