package Trabalho1;

import java.util.Comparator;

public class Partidos {
    private int numero_partido, votos_Legenda, votos_Total;
    private String nome, sigla;

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

    public String getSigla(){
        return this.sigla;
    }

    public String getNome(){
        return this.nome;
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
        return p2.getVotosTotal() - p1.getVotosTotal();
    }
}

class Compara_Vt_Vl_Np implements Comparator<Partidos> {
    public int compare(Partidos p1, Partidos p2){
        if(p1.getVotosLegenda() == p2.getVotosLegenda()){
            if(p1.getVotosTotal() - p1.getVotosLegenda() == p2.getVotosTotal() - p2.getVotosLegenda()) 
                return p1.getNumero() - p2.getNumero();
            return (p2.getVotosTotal() - p2.getVotosLegenda()) - (p1.getVotosTotal() - p1.getVotosLegenda());
        }
        return p2.getVotosLegenda() - p1.getVotosLegenda();
    }
}
