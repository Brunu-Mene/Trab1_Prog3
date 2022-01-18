import java.util.List;

public class Candidatos{
    private int numero_candidato,votos_nominais,numero_partido;
    private String nome_candidato,nome_urna,data_nasc,situacao;
    private boolean sexo,destino_voto;

    public Candidatos(int numero_candidato, int votos_nominais,String situacao, String nome_candidato, String nome_urna, String sexo, String data_nasc, String destino_voto,int numero_partido) {
        this.numero_partido = numero_partido;
        this.numero_candidato = numero_candidato;
        this.situacao = situacao;
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
        this.data_nasc = data_nasc;
    }

    public Candidatos(){

    }

    public void Numero_de_vagas (List<Candidatos> candidatos){
        int n_Vagas = 0;
        for(Candidatos elem: candidatos){
           if(elem.situacao.equals("Eleito")){
               n_Vagas++;
           }
        }
        System.out.printf("%d\n",n_Vagas);
    }

    @Override
    public String toString(){
        return  "Numero Candidato: " + this.numero_candidato + 
                ", Votos Nominais: " + this.votos_nominais + 
                ", Situacao: " + this.situacao + 
                ", Nome do Candidato: " + this.nome_candidato + 
                ", Nome na Urna: " + this.nome_urna +
                ", Sexo: " + this.sexo + 
                ", Data de Nascimento: " + this.data_nasc + 
                ", Destino Voto: " + this.destino_voto +
                ", Numero Partido: " + this.numero_partido;
    }

}
