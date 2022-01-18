import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Partidos {
    private int numero_partido, votos_Legenda;
    private String nome, sigla;

    public Partidos(int numero_partido, int votos_Legenda, String nome, String sigla) {
        this.numero_partido = numero_partido;
        this.votos_Legenda = votos_Legenda;
        this.nome = nome;
        this.sigla = sigla;
    }

    public Partidos() {}

    public void preenche_Lista(List<Partidos> list_Partidos, String caminho){
        try{
            BufferedReader pr = new BufferedReader(new FileReader(caminho));
            boolean i = false;
            while(pr.ready()){
                String linha = pr.readLine();
                if(i == true){
                    String separador[] = linha.split(",");
                    Partidos partido = new Partidos(Integer.parseInt(separador[0])
                    , Integer.parseInt(separador[1])
                    , separador[2]
                    , separador[3]);
                list_Partidos.add(partido);
                }else i = true;
            }
            pr.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return  "Numero Partido: " + this.numero_partido + 
                ", Votos Legenda: " + this.votos_Legenda + 
                ", Nome do Partido: " + this.nome + 
                ", Sigla: " + this.sigla;
    }
}
