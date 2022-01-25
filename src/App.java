import java.util.ArrayList;
import java.util.List;

/**





    TESTAR OS CRITÉRIOS DE DESEMPATE
    COLOCAR AS ORDENAÇÕES NA MAIN, TALVEZ QU3EM SABE





 */

public class App{
    public static void main(String[] args) {
        List<Candidatos> list_Candidatos = new ArrayList<Candidatos>();
        List<Candidatos> list_candidatos_Eleitos = new ArrayList<Candidatos>();
        List<Partidos> list_Partidos = new ArrayList<Partidos>();
        Partidos []vet_Partidos = new Partidos[100];
        Partidos partido = new Partidos();
        Candidatos candidato = new Candidatos();
        partido.preenche_Vetor(vet_Partidos, "script/testes/vitória/in/partidos.csv");
        candidato.preenche_Lista(list_Candidatos, vet_Partidos, "script/testes/vitória/in/candidatos.csv");

        candidato.Numero_de_vagas(list_Candidatos,list_candidatos_Eleitos);
        candidato.Eleitos(list_candidatos_Eleitos);
        candidato.mais_Votados(list_Candidatos, list_candidatos_Eleitos.size());
        candidato.Eleitos_se_Majoritario(list_Candidatos,list_candidatos_Eleitos,list_candidatos_Eleitos.size());
        candidato.Nao_eleitos_se_Majoritario(list_Candidatos, list_candidatos_Eleitos.size());
        partido.votos_Partido(list_Candidatos, vet_Partidos, list_Partidos);
        partido.Votos_de_Legenda(list_Partidos);
        candidato.Primeiro_Ultimo(list_Partidos, list_Candidatos);
        candidato.distribuicao_Idade(list_candidatos_Eleitos);
        candidato.distribuicao_Sexo(list_candidatos_Eleitos);
        partido.balanco_Votos(list_Partidos);
    }
}