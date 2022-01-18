import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;

public class App{
    public static void main(String[] args) {
        List<Partidos> list_Partidos = new ArrayList<Partidos>();
        List<Candidatos> list_Candidatos = new ArrayList<Candidatos>();
        Partidos partido = new Partidos();
        Candidatos candidato = new Candidatos();
        try{
            BufferedReader pr = new BufferedReader(new FileReader("script/testes/cariacica/in/partidos.csv"));
            BufferedReader ca = new BufferedReader(new FileReader("script/testes/cariacica/in/candidatos.csv"));
            boolean i = false;
            while(pr.ready()){
                String linha = pr.readLine();
                if(i == true){
                    String separador[] = linha.split(",");
                    partido = new Partidos(Integer.parseInt(separador[0])
                        , Integer.parseInt(separador[1])
                        , separador[2]
                        , separador[3]);
                    list_Partidos.add(partido);
                }else i = true;
            }
            i = false;
            while(ca.ready()){
                String linha = ca.readLine();
                if(i == true){
                    String separador[] = linha.split(",");
                    candidato = new Candidatos(Integer.parseInt(separador[0])
                        , Integer.parseInt(separador[1])
                        , separador[2], separador[3]
                        , separador[4], separador[5]
                        , separador[6], separador[7]
                        , Integer.parseInt(separador[8]));
                    list_Candidatos.add(candidato);
                }else i = true;
            }
            pr.close();
            ca.close();
            // for(Partidos elem: partidos){
            //     System.out.println(elem);
            // }
            // for(Candidatos elem: candidatos){
            //     System.out.println(elem);
            // }
            //Candidatos kkkk = new Candidatos();
            candidato.Numero_de_vagas(list_Candidatos);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}