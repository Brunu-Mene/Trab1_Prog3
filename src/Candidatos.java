public class Candidatos extends Partidos {
    private int numero_candidato,situacao,votos_nominais,sexo,destino_voto;
    private String nome_candidato,nome_urna,data_nasc;

    public Candidatos(int numero_candidato, int situacao, int votos_nominais, int sexo, int destino_voto,int numero_partido, String nome_candidato, String nome_urna, String data_nasc) {
        super(numero_partido);
        this.numero_candidato = numero_candidato;
        this.situacao = situacao;
        this.votos_nominais = votos_nominais;
        this.sexo = sexo;
        this.destino_voto = destino_voto;
        this.nome_candidato = nome_candidato;
        this.nome_urna = nome_urna;
        this.data_nasc = data_nasc;
    }

}
