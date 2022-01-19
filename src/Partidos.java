import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Partidos {
    private int numero_partido, votos_Legenda, votos_Total;
    private String nome, sigla;

    public Partidos() {}

    public Partidos(int numero_partido, String sigla){
        this.numero_partido = numero_partido;
        this.sigla = sigla;
    }

    public Partidos(int numero_partido, int votos_Legenda, String nome, String sigla, int votos_Total) {
        this.numero_partido = numero_partido;
        this.votos_Legenda = votos_Legenda;
        this.nome = nome;
        this.sigla = sigla;
        this.votos_Total = votos_Total;
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
                    , separador[3], 0);
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

    public int getNumero(){
        return this.numero_partido;
    }

    public int getVotosLegenda(){
        return this.votos_Legenda;
    }

    public int getVotosTotal(){
        return this.votos_Total;
    }
    
    public String getNome(){
        return this.nome;
    }

    @Override
    public String toString(){
        return  "Numero Partido: " + this.numero_partido + 
                ", Votos Legenda: " + this.votos_Legenda + 
                ", Nome do Partido: " + this.nome + 
                ", Sigla: " + this.sigla;
    }
}
