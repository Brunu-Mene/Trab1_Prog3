import java.util.ArrayList;
import java.util.List;

public class App{
    public static void main(String[] args) {
        //List<Partidos> list_Partidos = new ArrayList<Partidos>();
        List<Candidatos> list_Candidatos = new ArrayList<Candidatos>();
        List<Candidatos> list_candidatos_Eleitos = new ArrayList<Candidatos>();
        Partidos []vet_Partidos = new Partidos[100];
        Partidos partido = new Partidos();
        Candidatos candidato = new Candidatos();
        partido.preenche_Vetor(vet_Partidos, "script/testes/cariacica/in/partidos.csv");
        candidato.preenche_Lista(list_Candidatos, vet_Partidos, "script/testes/cariacica/in/candidatos.csv");

        /*for(Candidatos elem: list_Candidatos){
            System.out.println(elem);
        }*/
        /*for(Partidos elem: list_Partidos){
            System.out.println(elem);
        }*/

        candidato.Numero_de_vagas(list_Candidatos,list_candidatos_Eleitos);
        candidato.Eleitos(list_candidatos_Eleitos);
    }
}