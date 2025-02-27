import java.util.Comparator;

public class ComparadorPartidos implements Comparator<Partido>{
    @Override
    public int compare(Partido p1, Partido p2) {
        int diffVotos = p1.getQtdVotos() - p2.getQtdVotos();
        if(diffVotos == 0){
            return (p1.getNumero() - p2.getNumero());
        }
        return -1*(diffVotos);
    }
}
