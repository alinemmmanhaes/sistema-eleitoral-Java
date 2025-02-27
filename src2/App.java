import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        //LocalDate diaEleicao = LocalDate.parse(args[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate diaEleicao = LocalDate.parse("06/10/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Eleicao eleicao = new Eleicao(diaEleicao);
        //int cidade = Integer.parseInt(args[0]), vereador = 13;
        int cidade = 1392, vereador = 13;
        //String arquivoCandidatos = args[1];
        String arquivoCandidatos = "candidatos.csv";
        //String arquivoVotacao = args[2];
        String arquivoVotacao = "votacao.csv";

        InputStream is = new FileInputStream(arquivoCandidatos);
        InputStreamReader isr = new InputStreamReader(is, "ISO-8859-1");
        BufferedReader br = new BufferedReader(isr);
        br.readLine();
        String linha = br.readLine();

        while (linha != null) {
            Scanner s = new Scanner(linha).useDelimiter(";");
            int i = 0;
            int codCidade = 0, cargo = 0, numeroCandidato = 0, numeroPartido = 0, numeroFederacao = 0, codEleito = 0, codGenero = 0;
            String nomeCandidato = "", siglaPartido = "";
            LocalDate nascimento = LocalDate.parse("2005-03-04");
            
            for(i=0; i<50; i++){
                String input = s.next();
                if(i==11) codCidade = Integer.parseInt(input.replace("\"", ""));
                else if(i == 13) cargo = Integer.parseInt(input.replace("\"", ""));
                else if(i == 16) numeroCandidato = Integer.parseInt(input.replace("\"", ""));
                else if(i == 18) nomeCandidato = input.replace("\"", "");
                else if(i == 25) numeroPartido = Integer.parseInt(input.replace("\"", ""));
                else if(i == 26) siglaPartido = input.replace("\"", "");
                else if(i == 28) numeroFederacao = Integer.parseInt(input.replace("\"", ""));
                else if(i == 36) nascimento = LocalDate.parse(input.replace("\"", ""), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                else if(i == 38) codGenero = Integer.parseInt(input.replace("\"", ""));
                else if(i == 48) codEleito = Integer.parseInt(input.replace("\"", ""));
            }

            if(eleicao.partidoExiste(numeroPartido) == false){
                Partido p = new Partido(numeroPartido, siglaPartido, (numeroFederacao != -1));
                eleicao.adicionaPartido(p);
            }

            if(codCidade == cidade && cargo == vereador && codEleito != -1){
                Partido partido = eleicao.getPartido(numeroPartido);
                Candidato c = new Candidato(numeroCandidato, nomeCandidato, partido, nascimento, (codEleito == 2 || codEleito == 3), codGenero);
                eleicao.adicionaCandidato(c);
            }
            
            s.close();
            linha = br.readLine();
        }
        br.close();

        is = new FileInputStream(arquivoVotacao);
        isr = new InputStreamReader(is, "UTF-8");
        br = new BufferedReader(isr);
        br.readLine();
        linha = br.readLine();

        while (linha != null) {
            Scanner s = new Scanner(linha).useDelimiter(";");
            int i = 0;
            int codCidade = 0, cargo = 0, qtdVotos = 0, numeroCandidato = 0;
            
            for(i=0; i<26; i++){
                String input = s.next();
                if(i==13) codCidade = Integer.parseInt(input.replace("\"", ""));
                else if(i == 17) cargo = Integer.parseInt(input.replace("\"", ""));
                else if(i == 19) numeroCandidato = Integer.parseInt(input.replace("\"", ""));
                else if(i == 21) qtdVotos = Integer.parseInt(input.replace("\"", ""));
            }
            
            if(codCidade == cidade && cargo == vereador && (numeroCandidato<95 || numeroCandidato>98)){
                if(numeroCandidato < 100){
                    Partido p = eleicao.getPartido(numeroCandidato);
                    if(p != null) p.aumentaVotosLegenda(qtdVotos);
                }
                else{
                    Candidato c = eleicao.getCandidato(numeroCandidato);
                    if(c != null){
                        Partido p = c.getPartido();
                        p.aumentaVotosNominal(c, qtdVotos);
                    }
                }
            }

            s.close();
            linha = br.readLine();
        }
        br.close();

        eleicao.relatorio1();
        eleicao.relatorio2();
        eleicao.relatorio3();
        eleicao.relatorio4();
        eleicao.relatorio5();
        eleicao.relatorio6();
        eleicao.relatorio7();
        eleicao.relatorio8();
        eleicao.relatorio9();
        eleicao.relatorio10();
        
    }
}