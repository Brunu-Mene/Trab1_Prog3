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
            BufferedReader br = new BufferedReader(new FileReader("src/teste.csv"));
            while(br.ready()){
                String linha = br.readLine();
                String separador[] = linha.split(",");
                test = new Partidos(Integer.parseInt(separador[0]),Integer.parseInt(separador[1]),separador[2],separador[3]);
                partidos.add(test);
                System.out.println(partidos.get(0));
            }
            br.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}