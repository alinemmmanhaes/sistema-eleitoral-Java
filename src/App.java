import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {
    /*
    Pré-condição de leitura: entradas nos args e nos arquivos de candidato e votação conforme especificado nao enunciado do Trabalho
    Por exemplo, a cidade deve ter um código que a representa e este deve ser do tipo "int"
    O mesmo vale para outras leituras, como o número de um candidato

    Para as funções com parâmetro de candidato ou partido, é pré-condição que esse parâmetro exista
    */
    public static void main(String[] args) {
        if(args.length <= 3){
            System.out.println("Erro! Faltam argumentos suficientes para o funcionamento do programa.");
            return;
        }

        //conforme a especificação, guarda em sua respectiva variavel todos os parametros passados por args
        LocalDate diaEleicao = LocalDate.parse(args[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        int cidade = Integer.parseInt(args[0]), vereador = 13;
        String arquivoCandidatos = args[1];
        String arquivoVotacao = args[2];

        //cria um leitor, para ler os arquivos cujos nomes foram passados por args
        Leitor leitor = new Leitor(diaEleicao, cidade, vereador);

        //le o arquivo de candidatos e trata IOException
        try {
            leitor.leCandidatos(arquivoCandidatos);
        } catch (IOException e) {
            System.out.println("Erro na leitura de arquivo de candidatos");
            e.printStackTrace();
        }

        //le o arquivo de votação e trata IOException
        try {
            leitor.leVotacao(arquivoVotacao);
        } catch (IOException e) {
            System.out.println("Erro na leitura de arquivo de votação");
            e.printStackTrace();
        }

        //eleicao criada e calculada pelo leitor, com base nos dados presentes nos arquivos
        Eleicao eleicao = leitor.getEleicao();

        //cria um objeto da classe Relatorio, que fará todas as análises solicitadas
        Relatorio relatorio = new Relatorio(eleicao);
        relatorio.geraRelatorios();
    }
}