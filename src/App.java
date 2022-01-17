import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;

public class App{
    public static void main(String[] args) {
        List<Partidos> partidos = new ArrayList<Partidos>();
        Partidos test;
        try{
            BufferedReader br = new BufferedReader(new FileReader("script/testes/cariacica/in/partidos.csv"));
            int i=0;
            while(br.ready()){
                String linha = br.readLine();
                if(i!=0){
                    String separador[] = linha.split(",");
                    test = new Partidos(Integer.parseInt(separador[0]),Integer.parseInt(separador[1]),separador[2],separador[3]);
                    partidos.add(test);
                }else i = 1;
            }
            br.close();
            for(Partidos elem: partidos){
                System.out.println(elem);
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}