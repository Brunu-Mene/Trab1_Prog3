import java.util.ArrayList;
import java.util.List;

//Mudei a logica da lista de partidos para um vetor como hash e coloquei a classe Partido como pai da candidato
//A gente precisava printar a sigla do partido dentro do toString da classe Candidatos pra especificação 2 entao
//iria ter q haver uma relação de herença ae, entao eu preencho o vetor hash e com base no numero do partido
//que a gente pega no arquivo de candidatos, eu consigo acessar o partido dessa posição, pegar a sigla dele e passar
//para a classe pai do candidato em questão

//Fiz uma lista de candidatos eleitos pra nao ter q percorrer a lista geral mais de 1 vez tanto pra somar quando salvar



public class App{
    public static void main(String[] args) {
        //List<Partidos> list_Partidos = new ArrayList<Partidos>();
        List<Candidatos> list_Candidatos = new ArrayList<Candidatos>();
        List<Candidatos> list_candidatos_Eleitos = new ArrayList<Candidatos>();
        Partidos []vet_Partidos = new Partidos[100];
        Partidos partido = new Partidos();
        Candidatos candidato = new Candidatos();
        partido.preenche_Vetor(vet_Partidos, "script/testes/cariacica/in/partidos.csv");
        candidato.preenche_Lista(list_Candidatos, vet_Partidos, "script/testes/cariacica/in/candidatos.csv");

        /*for(Candidatos elem: list_Candidatos){
            System.out.println(elem);
        }*/
        /*for(Partidos elem: list_Partidos){
            System.out.println(elem);
        }*/

        candidato.Numero_de_vagas(list_Candidatos,list_candidatos_Eleitos);
        candidato.Eleitos(list_candidatos_Eleitos);
        candidato.mais_Votados(list_candidatos_Eleitos);
    }
}