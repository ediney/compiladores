package LALG.Automatos;

import LALG.Logger.MainLogger;
import LALG.AnalisadorLexico.Token;
import LALG.Compilador.TokensList;

/** Comentario - Classe que analisa os comentarios existentes na
 * na Linguagem LALG
 *
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 *
 */
public abstract class Comentario {

    /* Metodo padrão nos automatos
     * Inicia o automato e faz as validações, se necessário
     * @param t da classe Token, representa o token a ser analisado
     * @return boolean retorna o resultado da analise efetuada pelo automato
     */
    public static boolean run(Token t) {
        return stateComment01(t);
    }

    /* Estado 1 do automato
     * Verifica se o token é o inicio de um comentario
     */
    private static boolean stateComment01(Token t) {
        String word = t.getToken();
        int st = 1;
        if ("/*".equals(word)) {
            t.setType(TypeClass.Comentarios);
            return stateComment02(t);
        } else if (word.charAt(0) == '{') {
            t.setType(TypeClass.Comentarios);
            return stateComment03(t);
        } else {
            return false;
        }
    }

    /* Estado 2 do automato
     * Consome todos os tokens delimitados pelo caractere de comentário
     * correspondente ao de abertura
     */
    private static boolean stateComment02(Token t) {
        while (TokensList.hasNext()) {
           
            Token temp = TokensList.next();
            if ("*/".equals(temp.getToken())) {
               
                temp.setType(TypeClass.Comentarios);
                return true;
            }
            temp.setType(TypeClass.Comentarios);
        }
        MainLogger.logError("Erro Lexico - Fim de arquivo sem fim de comentário! 2");
        t.setType(TypeClass.Comentarios);
        return false;
    }

    /* Estado 3 do automato
     * Consome todos os tokens delimitados pelo caractere de comentário
     * correspondente ao de abertura
     */
    private static boolean stateComment03(Token t) {
        while (TokensList.hasNext()) {
            Token temp = TokensList.next();
            if (temp.getToken().charAt(0) == '}') {
                temp.setType(TypeClass.Comentarios);
                return true;
            }
            temp.setType(TypeClass.Comentarios);
        }
        MainLogger.logError("Erro Lexico - Fim de arquivo sem fim de comentário! 2");
        t.setType(TypeClass.Comentarios);
        return false;
    }
}
