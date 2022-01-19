import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Candidatos extends Partidos implements Comparable<Candidatos>{
    private int numero_candidato,votos_nominais;
    private String nome_candidato,nome_urna;
    Date data_nasc;
    private char situacao;
    private boolean sexo,destino_voto;

    public Candidatos() {}

    public Candidatos(int numero_candidato, int votos_nominais,String situacao, String nome_candidato, String nome_urna, String sexo, String data_nasc, String destino_voto,int numero_partido, String sigla_partido){
        super(numero_partido, sigla_partido);
        this.numero_candidato = numero_candidato;
        if(situacao.equals("Eleito")){
            this.situacao = 'E';
        }else if(situacao.equals("Suplente")){
            this.situacao = 'S';
        }else{
            this.situacao = 'N';
        }
        this.votos_nominais = votos_nominais;
        if(sexo.equals("F")){
            this.sexo = true;
        }else{
            this.sexo = false;
        }
        if(destino_voto.equals("Válido")){
            this.destino_voto = true;
        }else{
            this.destino_voto = false;
        }
        this.nome_candidato = nome_candidato;
        this.nome_urna = nome_urna;

        //KKKKKKK Q Q TA ACONTECENDO
        try{
            SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
            this.data_nasc = sdformat.parse(data_nasc);
        }catch (ParseException ex){ }
    }

    public void preenche_Lista(List<Candidatos> list_Candidatos, Partidos []vet_Partidos, String caminho){
        try{
            BufferedReader ca = new BufferedReader(new FileReader(caminho));
            boolean i = false;
            while(ca.ready()){
                String linha = ca.readLine();
                if(i == true){
                    String separador[] = linha.split(",");
                    Candidatos candidato = new Candidatos(Integer.parseInt(separador[0])
                        , Integer.parseInt(separador[1])
                        , separador[2], separador[3]
                        , separador[4], separador[5]
                        , separador[6], separador[7]
                        , Integer.parseInt(separador[8])
                        , vet_Partidos[Integer.parseInt(separador[8])].getSigla());
                    list_Candidatos.add(candidato);
                }else i = true;
            }
            ca.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public int Numero_de_vagas (List<Candidatos> list_Candidatos, List<Candidatos> list_candidatos_Eleitos){
        int n_Vagas = 0;
        for(Candidatos elem: list_Candidatos){
           if(elem.situacao == 'E'){
               list_candidatos_Eleitos.add(elem);
               n_Vagas++;
           }
        }
        System.out.printf("Número de vagas: %d\n\n",n_Vagas);

        return n_Vagas;
    }

    public void Eleitos(List<Candidatos> list_candidatos_Eleitos){
        System.out.println("Vereadores eleitos:");
        printa_ListaCandidatos(list_candidatos_Eleitos);
    }

    public void mais_Votados(List<Candidatos> list_candidatos_Eleitos){
        Collections.sort(list_candidatos_Eleitos);
        System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");
        printa_ListaCandidatos(list_candidatos_Eleitos);
    }

    public void Eleitos_se_Majoritario(List<Candidatos> list_Candidatos,List<Candidatos> list_candidatos_Eleitos,int n_Vagas){
        Collections.sort(list_Candidatos);
        System.out.println("Candidatos não eleitos e que seriam eleitos se a votação fosse majoritária:");
        int i = 1;

        for(Candidatos elem: list_Candidatos){
            if(elem.situacao != 'E' && i <= n_Vagas){
                System.out.println(i + " - " + elem);
            }else if(i > n_Vagas) break;
            i++;
        }
        System.out.println();
    }
    
    public void Nao_eleitos_se_Majoritario(List<Candidatos> list_Candidatos, int n_Vagas){
        System.out.println("Candidatos eleitos no sistema proporcional vigente, e que não seriam eleitos se a votação fosse majoritária:");
        int i = 1;
        Candidatos candidatoAux = list_Candidatos.get(n_Vagas - 1);
        for(Candidatos elem: list_Candidatos){
            if(elem.situacao == 'E'){
                if(elem.votos_nominais < candidatoAux.votos_nominais){
                    System.out.println(i + " - " + elem);
                }
            }
            i++;
        }
        System.out.println();
    }

    public void votos_Partido(List<Candidatos> list_Candidatos, Partidos []vet_Partidos){
        int [][]matPartidos = new int[100][2];
        
        for(int i = 0; i<100 ;i++){
            matPartidos[i][0] = 0;
            matPartidos[i][1] = 0;
        }
        for(Candidatos elem: list_Candidatos){
            if(elem.situacao == 'E') matPartidos[elem.getNumero()][1]++;
            matPartidos[elem.getNumero()][0] += elem.votos_nominais; 
        }
        List<Partidos> list_Partidos = new ArrayList<Partidos>();
        for(Partidos elem: vet_Partidos){
            if(elem != null){
                Partidos partido = new Partidos(elem.getNumero(),
                    elem.getVotosLegenda(), elem.getNome(), elem.getSigla(),
                    elem.getVotosLegenda()+matPartidos[elem.getNumero()][0]);
                list_Partidos.add(partido);
            }
        }
        Collections.sort(list_Partidos, new ComparaPartidos());
        int i=1;
        System.out.println("Votação dos partidos e número de candidatos eleitos:");
        for(Partidos elem: list_Partidos){
            System.out.printf("%d - %s - %d, %d votos (%d nominais e %d de legenda), %d candidatos eleito\n",
            i,elem.getSigla(),elem.getNumero(),elem.getVotosTotal(),matPartidos[elem.getNumero()][0],
            vet_Partidos[elem.getNumero()].getVotosLegenda(), matPartidos[elem.getNumero()][1]);
            i++;
        }
        System.out.println();
    }

    private void printa_ListaCandidatos(List<Candidatos> list_Candidatos){
        int i = 1;
        for(Candidatos elem: list_Candidatos){
            System.out.println(i + " - " + elem);
            i++;
        }
        System.out.println();
    }

    @Override
    public String toString(){
        return  this.nome_candidato + " / "
        + this.nome_urna + " (" + super.getSigla()
        + ", " + this.votos_nominais + " votos)";
    }

    //adicionar um criterio de desempata que escolha o candidato mais velho por cima;
    //se a data de c > this, retonar 1, else return 0
    @Override
    public int compareTo(Candidatos c){
        if(c.votos_nominais - this.votos_nominais == 0){
            return c.data_nasc.compareTo(this.data_nasc);
        }
        else return c.votos_nominais - this.votos_nominais;
    }

}
