/*
Classe Responsavel pelas operações util da aplicação,
como gerar a primary key do tipo UUID gerar logs de erros da aplicação
 */
package Utilitarios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author luizf
 */
public class Util {

    /**
     * Metodo responsavel por gerar o id dos novos registros. Exemplo de uso:
     * String id = Util.geraId();
     *
     * @return
     */
    public static String geraId() {
        UUID id = UUID.randomUUID();
        String valor = id.toString().toUpperCase();
        return valor;
    }

    /**
     * Metodo de gravar log na pasta raiz do projeto
     * exemplo de uso: Util.log("minha mensagem");
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
}
