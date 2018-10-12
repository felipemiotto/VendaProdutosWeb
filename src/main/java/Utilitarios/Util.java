/*
Classe Responsavel pelas operações util da aplicação,
como gerar a primary key do tipo UUID gerar logs de erros da aplicação
 */
package Utilitarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import javax.faces.context.FacesContext;

/**
 *
 * @author luizf
 */
public class Util {

    public static String senha;
    public static String usuario;
    public static String banco;

    /**
     * Metodo responsavel por gerar o id dos novos registros. Exemplo de uso:
     * String id = Util.geraId();
     *
     * @return
     */
    public static UUID geraId() {
        UUID id = UUID.randomUUID();
        return id;
    }

    /**
     * Metodo de gravar log na pasta raiz do projeto exemplo de uso:
     * Util.log("minha mensagem");
     *
     * @param mensagem
     */
    public static void log(String mensagem) {
        try {
            //pega diretorio raiz do projeto
            String pasta = new File("").getAbsolutePath() + "\\log.txt";
            File arquivo = new File(pasta);
            //caso o arquivo não exista cria
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(arquivo.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            //começa a escrita no arquivo
            Calendar data = Calendar.getInstance();
            Date date = (Date) data.getTime();
            SimpleDateFormat sddia = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdhora = new SimpleDateFormat("HH:mm:ss");
            String dia = sddia.format(date);
            String hora = sdhora.format(date);
            bw.write(dia + " " + hora + ": " + mensagem + "\r\n");
            bw.close();
        } catch (IOException ex) {
            Util.log(ex.getMessage());
        }
    }

    /**
     * Metodo para extrair os dados de conexão com o banco de dados
     */
    public static void extraiConexao() {
        try {
            FileReader fileReader = new FileReader(new File("").getAbsolutePath() + "\\conecta.txt");
            BufferedReader br = new BufferedReader(fileReader);
            String valores = br.readLine();
            String[] array = valores.split(",");
            usuario = array[0];
            senha = array[1];
            banco = array[2];
        } catch (FileNotFoundException ex) {
            Util.log(ex.getMessage());
        } catch (IOException ex) {
            Util.log(ex.getMessage());
        }
    }
}
