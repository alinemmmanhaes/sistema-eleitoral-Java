import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Leitor {
    private Eleicao eleicao;
    private int cidade;
    private int cargo;

    public Leitor(LocalDate diaEleicao, int cidade, int cargo) {
        this.eleicao = new Eleicao(diaEleicao);
        this.cidade = cidade;
        this.cargo = cargo;
    }

    /*le as informações do arquivo de candidatos:
    -criando todos os partidos existentes no estado (que foram lidos do arquivo)
    -criando todos os candidatos do cargo especificado da cidade com o codigo armazenado
    -guardando tudo no objeto eleicao
    */
    public void leCandidatos(String arquivo) throws IOException{
        InputStream is = new FileInputStream(arquivo);
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

            /*verifica se a cidade do candidato é a mesma a ser analisada
            se o cargo é o de vereador
            se a situação eleitoral do candidato não é invalida
            */
            if(codCidade == this.cidade && cargo == this.cargo && codEleito != -1){
                Partido partido = eleicao.getPartido(numeroPartido);
                Candidato c = new Candidato(numeroCandidato, nomeCandidato, partido, nascimento, (codEleito == 2 || codEleito == 3), codGenero);
                eleicao.adicionaCandidato(c);
            }
            
            s.close();
            linha = br.readLine();
        }
        br.close();
    }

    /*le as informações do arquivo de votacao:
    -aumentando todos os votos em partidos existentes no estado (que foram lidos do arquivo)
    -aumentando todos os votos em candidatos do cargo especificado da cidade com o codigo armazenado
    -guardando tudo no objeto eleicao
    */
    public void leVotacao(String arquivo) throws IOException{
        InputStream is = new FileInputStream(arquivo);
        InputStreamReader isr = new InputStreamReader(is, "ISO-8859-1");
        BufferedReader br = new BufferedReader(isr);
        br.readLine();
        String linha = br.readLine();

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
            
            if(codCidade == this.cidade && cargo == this.cargo && (numeroCandidato<95 || numeroCandidato>98)){
                if(numeroCandidato < 100){
                    eleicao.partidoInsereVotos(numeroCandidato, qtdVotos);
                }
                else{
                    eleicao.candidatoInsereVotos(numeroCandidato, qtdVotos);
                }
            }

            s.close();
            linha = br.readLine();
        }
        br.close();
    }

    //retorna a eleicao armazenada com os dados de leitura
    public Eleicao getEleicao(){
        return eleicao;
    }
}