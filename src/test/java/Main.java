
import Utilitarios.Util;
import java.io.File;

/**
 * Classe para realizar chamada de funções em modo desenvolvimento
 */
/**
 *
 * @author luizf
 */
public class Main {

    public static void main(String[] args) {
        Util.extraiConexao();
        System.out.println(Util.banco);
        System.out.println(Util.senha);
        System.out.println(Util.usuario);
        
    }
}
