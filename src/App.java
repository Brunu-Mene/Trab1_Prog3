import java.util.ArrayList;
import java.util.List;

public class App{
    public static void main(String[] args) {
        List<Partidos> list_Partidos = new ArrayList<Partidos>();
        List<Candidatos> list_Candidatos = new ArrayList<Candidatos>();
        Partidos partido = new Partidos();
        Candidatos candidato = new Candidatos();
        partido.preenche_Lista(list_Partidos, "script/testes/cariacica/in/partidos.csv");
        candidato.preenche_Lista(list_Candidatos, "script/testes/cariacica/in/candidatos.csv");


        /*for(Candidatos elem: list_Candidatos){
            System.out.println(elem);
        }*/
        for(Partidos elem: list_Partidos){
            System.out.println(elem);
        }

        candidato.Numero_de_vagas(list_Candidatos);
    }
}