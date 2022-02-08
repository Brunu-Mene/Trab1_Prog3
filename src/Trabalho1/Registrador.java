package Trabalho1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Registrador {
    
    public void preenche_Listas_Candidatos(List<Candidatos> list_Candidatos,List<Candidatos> list_candidatos_Eleitos,Partidos []vetHash_Partidos, String caminho){
        try{
            BufferedReader ca = new BufferedReader(new FileReader(caminho));
            boolean i = false;
            while(ca.ready()){
                String linha = ca.readLine();
                if(i == true){
                    String separador[] = linha.split(",");
                    if(separador[7].equals("Válido")){
                        Candidatos candidato = new Candidatos(Integer.parseInt(separador[0])
                        , Integer.parseInt(separador[1])
                        , separador[2], separador[3]
                        , separador[4], separador[5]
                        , separador[6], Integer.parseInt(separador[8])
                        , vetHash_Partidos[Integer.parseInt(separador[8])].getSigla());
                        list_Candidatos.add(candidato);
                        if(candidato.getSituacao() == 'E') list_candidatos_Eleitos.add(candidato);
                    }
                }else i = true;
            }
            ca.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void preenche_VetorHash_Partidos(Partidos []vetHash_Partidos, String caminho){
        try{
            BufferedReader pr = new BufferedReader(new FileReader(caminho));
            boolean i = false;
            while(pr.ready()){
                String linha = pr.readLine();
                if(i == true){
                    String separador[] = linha.split(",");
                    vetHash_Partidos[Integer.parseInt(separador[0])] = new Partidos(Integer.parseInt(separador[0])
                    , Integer.parseInt(separador[1])
                    , separador[2]
                    , separador[3], 0);
                }else i = true;
            }
            pr.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void preenche_Lista_Votos_Partidos(List<Candidatos> list_Candidatos, Partidos []vetHash_Partidos, List<Partidos> list_Partidos){
        int [][]matHash_Votos_Partidos = new int[100][1];

        for(int i = 0; i<100 ;i++){
            matHash_Votos_Partidos[i][0] = 0;
        }
        for(Candidatos elem: list_Candidatos){
            matHash_Votos_Partidos[elem.getNumero()][0] += elem.getVotos_Nominais(); 
        }
        for(Partidos elem: vetHash_Partidos){
            if(elem != null){
                Partidos partido = new Partidos(elem.getNumero(),
                    elem.getVotosLegenda(), elem.getNome(), elem.getSigla(),
                    elem.getVotosLegenda()+matHash_Votos_Partidos[elem.getNumero()][0]);
                list_Partidos.add(partido);
            }
        }
    }

}
