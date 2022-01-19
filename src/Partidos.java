import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    public void Votos_de_Legenda(List<Partidos> list_Partidos){
        Collections.sort(list_Partidos, new Compara_Vt_Vl_Np());
        int i=1;
        System.out.println("Votação dos partidos (apenas votos de legenda):");
        for(Partidos elem: list_Partidos){
            double pc = 100*(Double.valueOf(elem.getVotosLegenda())/Double.valueOf(elem.getVotosTotal()));
            System.out.print(i + elem.toString() + elem.votos_Legenda + " votos de legenda (");
            System.out.printf("%.2f%% do total do partido)\n",pc);
            i++;
        }
        System.out.println();
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
        return " - " + this.sigla + " - " + this.numero_partido + ", ";
    }
}

class Compara_Vt_Np implements Comparator<Partidos> {
    public int compare(Partidos p1, Partidos p2){
        if(p1.getVotosTotal() == p2.getVotosTotal()){
            return p2.getNumero() - p1.getNumero();
        }
        else if(p1.getVotosTotal() > p2.getVotosTotal()) return -1;
        else return 1;
    }
}

class Compara_Vt_Vl_Np implements Comparator<Partidos> {
    public int compare(Partidos p1, Partidos p2){
        if(p1.getVotosLegenda() == p2.getVotosLegenda()){
            if(p1.getVotosTotal() > p2.getVotosTotal()) return -1;
            else if(p1.getVotosTotal() < p2.getVotosTotal()) return 1;
            else return p2.getNumero() - p1.getNumero();
        }
        else if(p1.getVotosLegenda() > p2.getVotosLegenda()) return -1;
        else return 1;
    }
}
