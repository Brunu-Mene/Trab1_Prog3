import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    public Candidatos() {}

    public void preenche_Lista(List<Candidatos> list_Candidatos, String caminho){
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
                        , Integer.parseInt(separador[8]));
                    list_Candidatos.add(candidato);
                }else i = true;
            }
            ca.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void Numero_de_vagas (List<Candidatos> list_Candidatos){
        int n_Vagas = 0;
        for(Candidatos elem: list_Candidatos){
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
