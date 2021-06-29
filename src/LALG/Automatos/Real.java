package LALG.Automatos;

import LALG.Compilador.TSR;
import LALG.AnalisadorLexico.Token;

/** Real - Classe que analisa os tokes do tipo Real e seus delimitadores
 *
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 *
 */
public abstract class Real {

    /* Metodo padrão nos automatos
     * Inicia o automato e faz validações, se necessário
     * @param t da classe Token, representa o token a ser analisado
     * @return boolean retorna o resultado da analise efetuada pelo automato
     */
    private static String word;
    private static char realDelimiter = '.';
    private static boolean delimiterFound = false;

    public static boolean run(Token t) {
        word = t.getToken();
        if (e1() && word.length() >= 2) {
            t.setType(TypeClass.NumerosReais);
            return true;
        } else {
            return false;
        }
    }

    private static boolean e1() {
        if (TSR.isDigit(word.charAt(0)) || word.charAt(0) == realDelimiter) {
            return e2();
        } else {
            return false;
        }
    }

    private static boolean e2() {
        delimiterFound = false;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!(TSR.isDigit(c) || c == '.')) {
                return false;
            }
            if (c == '.') {
                if (delimiterFound) {
                    return false;
                } else {
                    // Encontrado o primeiro '.'
                    delimiterFound = true;
                    System.out.println("Encontrado: [" + c + "] "
                            + "mudando delimitador para : " + delimiterFound);
                }
            }
        }
        return delimiterFound;
    }
}
