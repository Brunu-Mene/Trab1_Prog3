import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Candidatos extends Partidos{
    private int numero_candidato,votos_nominais;
    private String nome_candidato,nome_urna,data_nasc;
    private char situacao;
    private boolean sexo,destino_voto;

    public Candidatos() {}

    public Candidatos(int numero_candidato, int votos_nominais,String situacao, String nome_candidato, String nome_urna, String sexo, String data_nasc, String destino_voto,int numero_partido, String sigla_partido) {
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
        this.data_nasc = data_nasc;
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

    public void Numero_de_vagas (List<Candidatos> list_Candidatos, List<Candidatos> list_candidatos_Eleitos){
        int n_Vagas = 0;
        for(Candidatos elem: list_Candidatos){
           if(elem.situacao == 'E'){
               list_candidatos_Eleitos.add(elem);
               n_Vagas++;
           }
        }
        System.out.printf("Número de vagas: %d\n\n",n_Vagas);
    }

    public void Eleitos(List<Candidatos> list_candidatos_Eleitos){
        int i=1;
        System.out.println("Vereadores eleitos:");
        for(Candidatos elem: list_candidatos_Eleitos){
            System.out.println(i + " - " + elem);
            i++;
        }
    }

    /*@Override
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
    }*/

    @Override
    public String toString(){
        return  this.nome_candidato + " / "
        + this.nome_urna + " (" + super.getSigla()
        + ", " + this.votos_nominais + " votos)";
    }

}
