import java.util.Comparator;

public class ComparaPartidos implements Comparator<Partidos> {
    @Override
    public int compare(Partidos p1, Partidos p2){
        if(p1.getVotosTotal() == p2.getVotosTotal()){
            if(p1.getVotosLegenda() > p2.getVotosLegenda()) return -1;
            else return 1;
        }
        else if(p1.getVotosTotal() > p2.getVotosTotal()) return -1;
        else return 1;
    }
}
