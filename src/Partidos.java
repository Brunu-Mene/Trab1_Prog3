import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

    public void votos_Partido(List<Candidatos> list_Candidatos, Partidos []vet_Partidos, List<Partidos> list_Partidos){
        int [][]matPartidos = new int[100][2];
        
        for(int i = 0; i<100 ;i++){
            matPartidos[i][0] = 0;
            matPartidos[i][1] = 0;
        }
        for(Candidatos elem: list_Candidatos){
            if(elem.getSituacao() == 'E') matPartidos[elem.getNumero()][1]++;
            matPartidos[elem.getNumero()][0] += elem.getVotos_Nominais(); 
        }
        for(Partidos elem: vet_Partidos){
            if(elem != null){
                Partidos partido = new Partidos(elem.numero_partido,
                    elem.votos_Legenda, elem.nome, elem.sigla,
                    elem.votos_Legenda+matPartidos[elem.numero_partido][0]);
                list_Partidos.add(partido);
            }
        }
        Collections.sort(list_Partidos, new Compara_Vt_Np());
        int i=1;
        System.out.println("Votação dos partidos e número de candidatos eleitos:");
        for(Partidos elem: list_Partidos){
            System.out.println(i + elem.toString() + elem.votos_Total + 
            " votos (" + matPartidos[elem.numero_partido][0] + 
            " nominais e " + vet_Partidos[elem.numero_partido].votos_Legenda + 
            "), " + matPartidos[elem.numero_partido][1] + 
            " candidatos eleito");
            i++;
        }
        System.out.println();
    }

    //duvida
    public void Votos_de_Legenda(List<Partidos> list_Partidos){
        Collections.sort(list_Partidos, new Compara_Vt_Vl_Np());
        int i=1;
        System.out.println("Votação dos partidos (apenas votos de legenda):");
        for(Partidos elem: list_Partidos){
            System.out.print(i + elem.toString() + elem.votos_Legenda + " votos de legenda (");
            if(elem.votos_Legenda != 0){
                double pc = 100*(Double.valueOf(elem.votos_Legenda)/Double.valueOf(elem.votos_Total));
                System.out.printf("%.2f%% do total do partido)\n",pc);
            }else{
                System.out.println("proporção não calculada, 0 voto no partido)");
            }
            i++;
        }
        System.out.println();
    }

    //mudar para classe candidatos
    /*public void Primeiro_Ultimo(List<Partidos> list_Partidos, List<Candidatos> list_Candidatos){
        List <Candidatos> candidatos_MaisVotados = new ArrayList<Candidatos>();
        List <Candidatos> candidatos_MenosVotados = new ArrayList<Candidatos>();
        Collections.sort(list_Candidatos);
        for(Partidos elem: list_Partidos){
            if(elem.votos_Total != 0){
                for(Candidatos candidato: list_Candidatos){
                    if(candidato.getNumero() == elem.numero_partido){
                        candidatos_MaisVotados.add(candidato);
                        break;
                    }
                }
                for(int i = list_Candidatos.size() - 1; i >= 0;  i--){
                    Candidatos aux = list_Candidatos.get(i);
                    if(aux.getNumero() == elem.numero_partido){
                        candidatos_MenosVotados.add(aux);
                        break;
                    }
                }
            }
        }
        Collections.sort(candidatos_MaisVotados, new Compara_Vn_Np());
        int i = 1;
        for(Candidatos elem: candidatos_MaisVotados){
            System.out.print(i+ " - " + elem.getSigla() + 
                                " - " + elem.getNumero() + 
                                ", " + elem.getNome_Urna() + 
                                " (" + elem.getNumero_candidato() + 
                                ", " + elem.getVotos_Nominais() + 
                                " votos" + ") ");
            for(Candidatos candidatos: candidatos_MenosVotados){
                if(candidatos.getNumero() == elem.getNumero() ){
                    System.out.println( " / " + candidatos.getNome_Urna() + 
                                        " (" + candidatos.getNumero_candidato() + 
                                        ", " + candidatos.getVotos_Nominais() + 
                                        " votos" + ") ");
                    break;
                }
            }
            i++;
        }
    }*/

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

    @Override
    public String toString(){
        return " - " + this.sigla + " - " + this.numero_partido + ", ";
    }
}

class Compara_Vt_Np implements Comparator<Partidos> {
    public int compare(Partidos p1, Partidos p2){
        if(p1.getVotosTotal() == p2.getVotosTotal()){
            return p1.getNumero() - p2.getNumero();
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
            else return p1.getNumero() - p2.getNumero();
        }
        else if(p1.getVotosLegenda() > p2.getVotosLegenda()) return -1;
        else return 1;
    }
}
