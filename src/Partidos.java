import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Partidos {
    private int numero_partido, votos_Legenda;
    private String nome, sigla;

    public Partidos() {}

    public Partidos(int numero_partido, String sigla){
        this.numero_partido = numero_partido;
        this.sigla = sigla;
    }

    public Partidos(int numero_partido, int votos_Legenda, String nome, String sigla) {
        this.numero_partido = numero_partido;
        this.votos_Legenda = votos_Legenda;
        this.nome = nome;
        this.sigla = sigla;
    }

    public void preenche_Vetor(Partidos []vet_Partidos, String caminho){
        try{
            BufferedReader pr = new BufferedReader(new FileReader(caminho));
            boolean i = false;
            while(pr.ready()){
                String linha = pr.readLine();
                if(i == true){
                    String separador[] = linha.split(",");
                    vet_Partidos[Integer.parseInt(separador[0])] = new Partidos(Integer.parseInt(separador[0])
                    , Integer.parseInt(separador[1])
                    , separador[2]
                    , separador[3]);
                }else i = true;
            }
            pr.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public String getSigla(){
        return this.sigla;
    }

    @Override
    public String toString(){
        return  "Numero Partido: " + this.numero_partido + 
                ", Votos Legenda: " + this.votos_Legenda + 
                ", Nome do Partido: " + this.nome + 
                ", Sigla: " + this.sigla;
    }
}
