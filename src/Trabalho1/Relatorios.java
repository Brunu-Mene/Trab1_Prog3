package Trabalho1;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Relatorios {

    public void Numero_de_vagas (int n_Vagas){
        System.out.printf("Número de vagas: %d\n\n",n_Vagas);
    }

    public void Eleitos(List<Candidatos> list_candidatos_Eleitos){
        System.out.println("Vereadores eleitos:");
        int i = 1;
        for(Candidatos elem: list_candidatos_Eleitos){
            System.out.println(i + " - " + elem);
            i++;
        }
        System.out.println();
    }

    public void mais_Votados(List<Candidatos> list_candidatos, int n_Eleitos){
        int i=1;
        System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");
        for(Candidatos elem: list_candidatos){
            if(i > n_Eleitos){
                break;
            }else{
                System.out.println(i + " - " + elem);
            }
            i++;
        }
        System.out.println();
    }

    public void Eleitos_se_Majoritario(List<Candidatos> list_Candidatos,List<Candidatos> list_candidatos_Eleitos,int n_Vagas){
        System.out.println("Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n(com sua posição no ranking de mais votados)");
        int i = 1;

        for(Candidatos elem: list_Candidatos){
            if(elem.getSituacao() != 'E' && i <= n_Vagas){
                System.out.println(i + " - " + elem);
            }else if(i > n_Vagas) break;
            i++;
        }
        System.out.println();
    }

    public void Nao_eleitos_se_Majoritario(List<Candidatos> list_Candidatos, int n_Vagas){
        System.out.println("Eleitos, que se beneficiaram do sistema proporcional:\n(com sua posição no ranking de mais votados)");
        int i = 1;
        Candidatos candidatoAux = list_Candidatos.get(n_Vagas - 1);
        for(Candidatos elem: list_Candidatos){
            if(elem.getSituacao() == 'E'){
                if(elem.getVotos_Nominais() < candidatoAux.getVotos_Nominais()){
                    System.out.println(i + " - " + elem);
                }
            }
            i++;
        }
        System.out.println();
    }

    public void votos_Partido(List<Candidatos> list_Candidatos, Partidos []vet_Partidos, List<Partidos> list_Partidos){
        int [][]matPartidos = new int[100][2];
        
        for(int i = 0; i<100 ;i++){
            matPartidos[i][0] = 0;
            matPartidos[i][1] = 0;
        }
        for(Candidatos elem: list_Candidatos){
            if(elem.getSituacao() == 'E') matPartidos[elem.getNumero()][1]++;
            matPartidos[elem.getNumero()][0] += elem.getVotos_Nominais(); 
        }
        for(Partidos elem: vet_Partidos){
            if(elem != null){
                Partidos partido = new Partidos(elem.getNumero(),
                    elem.getVotosLegenda(), elem.getNome(), elem.getSigla(),
                    elem.getVotosLegenda()+matPartidos[elem.getNumero()][0]);
                list_Partidos.add(partido);
            }
        }
        Collections.sort(list_Partidos, new Compara_Vt_Np());
        int i=1;
        System.out.println("Votação dos partidos e número de candidatos eleitos:");
        for(Partidos elem: list_Partidos){
            if(matPartidos[elem.getNumero()][0] > 1){
                System.out.print(i + elem.toString() + elem.getVotosTotal() + 
                " votos (" + matPartidos[elem.getNumero()][0] + 
                " nominais e " + vet_Partidos[elem.getNumero()].getVotosLegenda() + 
                " de legenda), " + matPartidos[elem.getNumero()][1]);
                }
            else{
                System.out.print(i + elem.toString() + elem.getVotosTotal() + 
                " voto (" + matPartidos[elem.getNumero()][0] + 
                " nominal e " + vet_Partidos[elem.getNumero()].getVotosLegenda() + 
                " de legenda), " + matPartidos[elem.getNumero()][1]);
            }
            if(matPartidos[elem.getNumero()][1] > 1){
                System.out.println(" candidatos eleitos");
            }else{
                System.out.println(" candidato eleito");
            }
            i++;
        }
        System.out.println();
    }

    public void Votos_de_Legenda(List<Partidos> list_Partidos){
        Collections.sort(list_Partidos, new Compara_Vt_Vl_Np());
        int i=1;
        System.out.println("Votação dos partidos (apenas votos de legenda):");
        for(Partidos elem: list_Partidos){
            System.out.print(i + elem.toString() + elem.getVotosLegenda());
            if(elem.getVotosLegenda() > 1)  System.out.print(" votos de legenda (");
            else System.out.print(" voto de legenda (");
            if(elem.getVotosLegenda() != 0){
                double pc = 100*(Double.valueOf(elem.getVotosLegenda())/Double.valueOf(elem.getVotosTotal()));
                System.out.printf("%.2f%% do total do partido)\n",pc);
            }else{
                System.out.println("proporção não calculada, " + elem.getVotosTotal() + " voto no partido)");
            }
            i++;
        }
        System.out.println();
    }

    public void Primeiro_Ultimo(List<Partidos> list_Partidos, List<Candidatos> list_Candidatos){
        List <Candidatos> candidatos_MaisVotados = new ArrayList<Candidatos>();
        List <Candidatos> candidatos_MenosVotados = new ArrayList<Candidatos>();
        for(Partidos elem: list_Partidos){
            if(elem.getVotosTotal() != 0){
                for(Candidatos candidato: list_Candidatos){
                    if(candidato.getNumero() == elem.getNumero()){
                        candidatos_MaisVotados.add(candidato);
                        break;
                    }
                }
                for(int i = list_Candidatos.size() - 1; i >= 0;  i--){
                    Candidatos aux = list_Candidatos.get(i);
                    if(aux.getNumero() == elem.getNumero()){
                        candidatos_MenosVotados.add(aux);
                        break;
                    }
                }
            }
        }
        Collections.sort(candidatos_MaisVotados, new Compara_Vn_Np());
        int i = 1;
        System.out.println("Primeiro e último colocados de cada partido:");
        for(Candidatos elem: candidatos_MaisVotados){
            System.out.print(i+ " - " + elem.getSigla() + 
                                " - " + elem.getNumero() + 
                                ", " + elem.getNome_Urna() + 
                                " (" + elem.getNumero_Candidato() + 
                                ", " + elem.getVotos_Nominais());
            if(elem.getVotos_Nominais() > 1){
                    System.out.print(" votos" + ")");
            }else{
                System.out.print(" voto" + ")");
            }
            for(Candidatos candidatos: candidatos_MenosVotados){
                if(candidatos.getNumero() == elem.getNumero() ){
                    System.out.print(" / " + candidatos.getNome_Urna() + 
                                            " (" + candidatos.getNumero_Candidato() + 
                                            ", " + candidatos.getVotos_Nominais());
                    if(candidatos.getVotos_Nominais() > 1)
                        System.out.println(" votos" + ")");
                    else
                        System.out.println(" voto" + ")");
                    break;
                }
            }
            i++;
        }
        System.out.println();
    }

    public void distribuicao_Idade(List<Candidatos> list_candidatos_Eleitos, String data_Eleicao){
        int intervalo1 = 0, intervalo2 = 0, intervalo3 = 0, intervalo4 = 0, intervalo5 = 0;
        String []separador = data_Eleicao.split("/");
        LocalDate eleicao = LocalDate.of(Integer.parseInt(separador[2]),Integer.parseInt(separador[1]),Integer.parseInt(separador[0]));
        for(Candidatos elem: list_candidatos_Eleitos){
            Period period = Period.between(elem.getData_Nasc(), eleicao);
            int ano = period.getYears();
            if(ano<30) intervalo1++;
            else if(ano >= 30 && ano < 40) intervalo2++;
            else if(ano >= 40 && ano < 50) intervalo3++;
            else if(ano >= 50 && ano < 60) intervalo4++;
            else intervalo5++;
        }
        System.out.println("Eleitos, por faixa etária (na data da eleição):");
        System.out.printf("      Idade < 30: %d (%.2f%%)\n",intervalo1,100*Double.valueOf(intervalo1)/Double.valueOf(list_candidatos_Eleitos.size()));
        System.out.printf("30 <= Idade < 40: %d (%.2f%%)\n",intervalo2,100*Double.valueOf(intervalo2)/Double.valueOf(list_candidatos_Eleitos.size()));
        System.out.printf("40 <= Idade < 50: %d (%.2f%%)\n",intervalo3,100*Double.valueOf(intervalo3)/Double.valueOf(list_candidatos_Eleitos.size()));
        System.out.printf("50 <= Idade < 60: %d (%.2f%%)\n",intervalo4,100*Double.valueOf(intervalo4)/Double.valueOf(list_candidatos_Eleitos.size()));
        System.out.printf("60 <= Idade     : %d (%.2f%%)\n\n",intervalo5,100*Double.valueOf(intervalo5)/Double.valueOf(list_candidatos_Eleitos.size()));
    }

    public void distribuicao_Sexo(List<Candidatos> list_candidatos_Eleitos){
        int candidatos_M = 0;
        int candidatos_F = 0;
        for(Candidatos elem: list_candidatos_Eleitos){
            if(elem.getSexo() == 'F')    candidatos_F++;
            else if(elem.getSexo() == 'M') candidatos_M++;
        }
        System.out.println("Eleitos, por sexo:");
        System.out.printf("Feminino:  %d (%.2f%%)\n",candidatos_F,100*(Double.valueOf(candidatos_F)/Double.valueOf(list_candidatos_Eleitos.size())));
        System.out.printf("Masculino: %d (%.2f%%)\n\n",candidatos_M,100*(Double.valueOf(candidatos_M)/Double.valueOf(list_candidatos_Eleitos.size())));
    }

    public void balanco_Votos(List <Partidos> list_Partidos){
        int total_votos = 0, total_votos_nominais = 0, total_votos_legenda = 0;
        for(Partidos elem: list_Partidos){
            total_votos += elem.getVotosTotal();
            total_votos_nominais += (elem.getVotosTotal() - elem.getVotosLegenda());
            total_votos_legenda += elem.getVotosLegenda();
        }
        System.out.println("Total de votos válidos:    " + total_votos);
        System.out.printf("Total de votos nominais:   %d (%.2f%%)\n",total_votos_nominais, 100*(Double.valueOf(total_votos_nominais)/Double.valueOf(total_votos)));
        System.out.printf("Total de votos de legenda: %d (%.2f%%)\n\n\n",total_votos_legenda, 100*(Double.valueOf(total_votos_legenda)/Double.valueOf(total_votos)));
    }
    
}
