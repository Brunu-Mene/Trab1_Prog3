package Trabalho1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.Period;

public class Candidatos extends Partidos implements Comparable<Candidatos>{
    private int numero_candidato,votos_nominais;
    private String nome_candidato,nome_urna;
    LocalDate data_nasc;
    private char situacao, sexo;

    public Candidatos() {}

    public Candidatos(int numero_candidato, int votos_nominais,String situacao, String nome_candidato, String nome_urna, String sexo, String data_nasc,int numero_partido, String sigla_partido){
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
        this.sexo = sexo.charAt(0);
        this.nome_candidato = nome_candidato;
        this.nome_urna = nome_urna;
        
        String separador[] = data_nasc.split("/");
        this.data_nasc = LocalDate.of(Integer.parseInt(separador[2]),Integer.parseInt(separador[1]),Integer.parseInt(separador[0])); 
    }

    public void preenche_Listas(List<Candidatos> list_Candidatos,List<Candidatos> list_candidatos_Eleitos,Partidos []vet_Partidos, String caminho){
        try{
            BufferedReader ca = new BufferedReader(new FileReader(caminho));
            boolean i = false;
            while(ca.ready()){
                String linha = ca.readLine();
                if(i == true){
                    String separador[] = linha.split(",");
                    if(separador[7].equals("Válido")){
                        Candidatos candidato = new Candidatos(Integer.parseInt(separador[0])
                        , Integer.parseInt(separador[1])
                        , separador[2], separador[3]
                        , separador[4], separador[5]
                        , separador[6], Integer.parseInt(separador[8])
                        , vet_Partidos[Integer.parseInt(separador[8])].getSigla());
                        list_Candidatos.add(candidato);
                        if(candidato.situacao == 'E') list_candidatos_Eleitos.add(candidato);
                    }
                }else i = true;
            }
            ca.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    
    public void Numero_de_vagas (int n_Vagas){
        System.out.printf("Número de vagas: %d\n\n",n_Vagas);
    }

    public void Eleitos(List<Candidatos> list_candidatos_Eleitos){
        System.out.println("Vereadores eleitos:");
        int i = 1;
        for(Candidatos elem: list_candidatos_Eleitos){
            System.out.println(i + " - " + elem);
            i++;
        }
        System.out.println();
    }

    public void mais_Votados(List<Candidatos> list_candidatos, int n_Eleitos){
        int i=1;
        System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");
        for(Candidatos elem: list_candidatos){
            if(i > n_Eleitos){
                break;
            }else{
                System.out.println(i + " - " + elem);
            }
            i++;
        }
        System.out.println();
    }

    public void Eleitos_se_Majoritario(List<Candidatos> list_Candidatos,List<Candidatos> list_candidatos_Eleitos,int n_Vagas){
        System.out.println("Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n(com sua posição no ranking de mais votados)");
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
        System.out.println("Eleitos, que se beneficiaram do sistema proporcional:\n(com sua posição no ranking de mais votados)");
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

    public void Primeiro_Ultimo(List<Partidos> list_Partidos, List<Candidatos> list_Candidatos){
        List <Candidatos> candidatos_MaisVotados = new ArrayList<Candidatos>();
        List <Candidatos> candidatos_MenosVotados = new ArrayList<Candidatos>();
        for(Partidos elem: list_Partidos){
            if(elem.getVotosTotal() != 0){
                for(Candidatos candidato: list_Candidatos){
                    if(candidato.getNumero() == elem.getNumero()){
                        candidatos_MaisVotados.add(candidato);
                        break;
                    }
                }
                for(int i = list_Candidatos.size() - 1; i >= 0;  i--){
                    Candidatos aux = list_Candidatos.get(i);
                    if(aux.getNumero() == elem.getNumero()){
                        candidatos_MenosVotados.add(aux);
                        break;
                    }
                }
            }
        }
        Collections.sort(candidatos_MaisVotados, new Compara_Vn_Np());
        int i = 1;
        System.out.println("Primeiro e último colocados de cada partido:");
        for(Candidatos elem: candidatos_MaisVotados){
            System.out.print(i+ " - " + elem.getSigla() + 
                                " - " + elem.getNumero() + 
                                ", " + elem.nome_urna + 
                                " (" + elem.numero_candidato + 
                                ", " + elem.votos_nominais);
            if(elem.votos_nominais > 1){
                    System.out.print(" votos" + ")");
            }else{
                System.out.print(" voto" + ")");
            }
            for(Candidatos candidatos: candidatos_MenosVotados){
                if(candidatos.getNumero() == elem.getNumero() ){
                    System.out.print(" / " + candidatos.nome_urna + 
                                            " (" + candidatos.numero_candidato + 
                                            ", " + candidatos.votos_nominais);
                    if(candidatos.votos_nominais > 1)
                        System.out.println(" votos" + ")");
                    else
                        System.out.println(" voto" + ")");
                    break;
                }
            }
            i++;
        }
        System.out.println();
    }

    public void distribuicao_Idade(List<Candidatos> list_candidatos_Eleitos, String data_Eleicao){
        int intervalo1 = 0, intervalo2 = 0, intervalo3 = 0, intervalo4 = 0, intervalo5 = 0;
        String []separador = data_Eleicao.split("/");
        LocalDate eleicao = LocalDate.of(Integer.parseInt(separador[2]),Integer.parseInt(separador[1]),Integer.parseInt(separador[0]));
        for(Candidatos elem: list_candidatos_Eleitos){
            Period period = Period.between(elem.data_nasc, eleicao);
            int ano = period.getYears();
            if(ano<30) intervalo1++;
            else if(ano >= 30 && ano < 40) intervalo2++;
            else if(ano >= 40 && ano < 50) intervalo3++;
            else if(ano >= 50 && ano < 60) intervalo4++;
            else intervalo5++;
        }
        System.out.println("Eleitos, por faixa etária (na data da eleição):");
        System.out.printf("      Idade < 30: %d (%.2f%%)\n",intervalo1,100*Double.valueOf(intervalo1)/Double.valueOf(list_candidatos_Eleitos.size()));
        System.out.printf("30 <= Idade < 40: %d (%.2f%%)\n",intervalo2,100*Double.valueOf(intervalo2)/Double.valueOf(list_candidatos_Eleitos.size()));
        System.out.printf("40 <= Idade < 50: %d (%.2f%%)\n",intervalo3,100*Double.valueOf(intervalo3)/Double.valueOf(list_candidatos_Eleitos.size()));
        System.out.printf("50 <= Idade < 60: %d (%.2f%%)\n",intervalo4,100*Double.valueOf(intervalo4)/Double.valueOf(list_candidatos_Eleitos.size()));
        System.out.printf("60 <= Idade     : %d (%.2f%%)\n\n",intervalo5,100*Double.valueOf(intervalo5)/Double.valueOf(list_candidatos_Eleitos.size()));
    }

    public void distribuicao_Sexo(List<Candidatos> list_candidatos_Eleitos){
        int candidatos_M = 0;
        int candidatos_F = 0;
        for(Candidatos elem: list_candidatos_Eleitos){
            if(elem.sexo == 'F')    candidatos_F++;
            else if(elem.sexo == 'M') candidatos_M++;
        }
        System.out.println("Eleitos, por sexo:");
        System.out.printf("Feminino:  %d (%.2f%%)\n",candidatos_F,100*(Double.valueOf(candidatos_F)/Double.valueOf(list_candidatos_Eleitos.size())));
        System.out.printf("Masculino: %d (%.2f%%)\n\n",candidatos_M,100*(Double.valueOf(candidatos_M)/Double.valueOf(list_candidatos_Eleitos.size())));
    }

    @Override
    public String toString(){
        if(this.votos_nominais != 1){
            return  this.nome_candidato + " / "
            + this.nome_urna + " (" + super.getSigla()
            + ", " + this.votos_nominais + " votos)";
        }
        return  this.nome_candidato + " / "
        + this.nome_urna + " (" + super.getSigla()
        + ", " + this.votos_nominais + " voto)";
    }

    @Override
    public int compareTo(Candidatos c){
        if(c.votos_nominais - this.votos_nominais == 0){
            return c.data_nasc.compareTo(this.data_nasc)*(-1);
        }
        return c.votos_nominais - this.votos_nominais;
    }

    public int getVotos_Nominais(){
        return this.votos_nominais;
    }

    public char getSituacao(){
        return this.situacao;
    }

}

class Compara_Vn_Np implements Comparator<Candidatos>{
    public int compare(Candidatos c1, Candidatos c2){
        if(c1.getVotos_Nominais() == c2.getVotos_Nominais()){
            return c1.getNumero() - c2.getNumero();
        }
        return c2.getVotos_Nominais() - c1.getVotos_Nominais();
    }
}
