import java.util.Comparator;

public class ComparadorPartidos implements Comparator<Partido>{
    //compara dois partidos a partir de seus votos, e em ultimo caso, por seus numeros
    @Override
    public int compare(Partido p1, Partido p2) {
        int diffVotos = p1.getQtdVotos() - p2.getQtdVotos();
        if(diffVotos == 0){
            return (p1.getNumero() - p2.getNumero());
        }
        return -1*(diffVotos);
    }
}
