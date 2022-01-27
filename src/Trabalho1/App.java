package Trabalho1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class App{
    public static void main(String[] args) {
        Locale.setDefault(new Locale("pt", "BR"));
        List<Candidatos> list_Candidatos = new ArrayList<Candidatos>();
        List<Candidatos> list_candidatos_Eleitos = new ArrayList<Candidatos>();
        List<Partidos> list_Partidos = new ArrayList<Partidos>();
        Partidos []vet_Partidos = new Partidos[100];
        Partidos partido = new Partidos();
        Candidatos candidato = new Candidatos();
        partido.preenche_Vetor(vet_Partidos, args[1]);
        candidato.preenche_Listas(list_Candidatos,list_candidatos_Eleitos,vet_Partidos, args[0]);

        candidato.Numero_de_vagas(list_candidatos_Eleitos.size());

        Collections.sort(list_Candidatos);
        Collections.sort(list_candidatos_Eleitos);
        candidato.Eleitos(list_candidatos_Eleitos);
        candidato.mais_Votados(list_Candidatos, list_candidatos_Eleitos.size());
        candidato.Eleitos_se_Majoritario(list_Candidatos,list_candidatos_Eleitos,list_candidatos_Eleitos.size());
        candidato.Nao_eleitos_se_Majoritario(list_Candidatos, list_candidatos_Eleitos.size());

        partido.votos_Partido(list_Candidatos, vet_Partidos, list_Partidos);

        partido.Votos_de_Legenda(list_Partidos);

        candidato.Primeiro_Ultimo(list_Partidos, list_Candidatos);

        candidato.distribuicao_Idade(list_candidatos_Eleitos,args[2]);
        candidato.distribuicao_Sexo(list_candidatos_Eleitos);
        partido.balanco_Votos(list_Partidos);
    }
}