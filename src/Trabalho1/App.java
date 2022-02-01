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

        Relatorios relatorio = new Relatorios();
        Registrador registrador = new Registrador();
        
        registrador.preenche_Vetor_Partidos(vet_Partidos, args[1]);
        registrador.preenche_Listas_Candidatos(list_Candidatos,list_candidatos_Eleitos,vet_Partidos, args[0]);

        relatorio.Numero_de_vagas(list_candidatos_Eleitos.size());

        Collections.sort(list_Candidatos);
        Collections.sort(list_candidatos_Eleitos);
        relatorio.Eleitos(list_candidatos_Eleitos);
        relatorio.mais_Votados(list_Candidatos, list_candidatos_Eleitos.size());
        relatorio.Eleitos_se_Majoritario(list_Candidatos,list_candidatos_Eleitos,list_candidatos_Eleitos.size());
        relatorio.Nao_eleitos_se_Majoritario(list_Candidatos, list_candidatos_Eleitos.size());

        relatorio.votos_Partido(list_Candidatos, vet_Partidos, list_Partidos);

        relatorio.Votos_de_Legenda(list_Partidos);

        relatorio.Primeiro_Ultimo(list_Partidos, list_Candidatos);

        relatorio.distribuicao_Idade(list_candidatos_Eleitos,args[2]);
        relatorio.distribuicao_Sexo(list_candidatos_Eleitos);
        relatorio.balanco_Votos(list_Partidos);
    }
}