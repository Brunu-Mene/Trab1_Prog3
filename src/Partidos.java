public class Partidos {
    private int numero_partido, votos_Legenda;
    private String nome, sigla;

    public Partidos(int numero_partido, int votos_Legenda, String nome, String sigla) {
        this.numero_partido = numero_partido;
        this.votos_Legenda = votos_Legenda;
        this.nome = nome;
        this.sigla = sigla;
    }

    public Partidos(){

    }

    @Override
    public String toString(){
        return  "Numero Partido: " + this.numero_partido + 
                ", Votos Legenda: " + this.votos_Legenda + 
                ", Nome do Partido: " + this.nome + 
                ", Sigla: " + this.sigla;
    }
}
