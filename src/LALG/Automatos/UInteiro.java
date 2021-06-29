package LALG.Automatos;

import LALG.Compilador.TSR;
import LALG.AnalisadorLexico.Token;
import org.apache.log4j.Logger;

/** UInteiro - classe que analisa inteiros sem sinal
 *
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 *
 */
public abstract class UInteiro {

    private static String word;
    private static Logger logger = Logger.getLogger(UInteiro.class);
    private static int cp = 0;

    /* Metodo padrão nos automatos
     * Inicia o automato e faz validações, se necessário
     * @param t da classe Token, representa o token a ser analisado
     * @return boolean retorna o resultado da analise efetuada pelo automato
     */
    public static boolean run(Token t) {
        logger.debug("Init " + UInteiro.class);
        word = t.getToken();
        if (e1()) {
            t.setType(TypeClass.InteirosSemSinal);
            return true;
        }
        return false;
    }

    private static boolean e1() {
        if (TSR.isDigit(word.charAt(0))) {
            return e2();
        }
        return false;
    }

    private static boolean e2() {
        for (int i = 0; i < word.length(); i++) {
            if (!TSR.isDigit(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
