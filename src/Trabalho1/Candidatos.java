package Trabalho1;

import java.util.Comparator;
import java.time.LocalDate;

public class Candidatos extends Partidos implements Comparable<Candidatos>{
    private int numero_candidato,votos_nominais;
    private String nome_candidato,nome_urna;
    private LocalDate data_nasc;
    private char situacao, sexo;

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

    public int getVotos_Nominais(){
        return this.votos_nominais;
    }

    public String getNome_Urna(){
        return this.nome_urna;
    }

    public int getNumero_Candidato(){
        return this.numero_candidato;
    }

    public char getSituacao(){
        return this.situacao;
    }

    public LocalDate getData_Nasc(){
        return this.data_nasc;
    }

    public char getSexo(){
        return this.sexo;
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

}

class Compara_Vn_Np implements Comparator<Candidatos>{
    public int compare(Candidatos c1, Candidatos c2){
        if(c1.getVotos_Nominais() == c2.getVotos_Nominais()){
            return c1.getNumero() - c2.getNumero();
        }
        return c2.getVotos_Nominais() - c1.getVotos_Nominais();
    }
}
