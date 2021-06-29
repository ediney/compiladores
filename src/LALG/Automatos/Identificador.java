/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LALG.Automatos;

import LALG.Compilador.TSR;
import LALG.AnalisadorLexico.Token;
import org.apache.log4j.Logger;

/** Identificador - Classe que analisa os tokes que são
 * identificadores na Linguagem LALG
 *
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 *
 */
public abstract class Identificador {

    private static Logger logger = Logger.getLogger(UInteiro.class);

    /* Metodo padrão nos automatos
     * Inicia o automato e faz validações, se necessário
     * @param t da classe Token, representa o token a ser analisado
     * @return boolean retorna o resultado da analise efetuada pelo automato
     */
    public static boolean run(Token t) {
        return stateReservedWord01(t);
    }

    /* Estado 1 do automato
     * Verifica se a palavra informada é da classe das Palavras Reservadas
     * caso não seja chama o metodo stateIdentifier02
     */
    private static boolean stateReservedWord01(Token t) {
        String word = t.getToken();
        if (TSR.isReservedWord(word)) {
            t.setType(TypeClass.PalavrasReservadas);
            return true;
        } else {
            return stateIdentifier02(t);
        }

    }
    
    /*
     * Verifica se o token t é um identificado
     * return boolean retorna false se não for um identificador
     */
    private static boolean stateIdentifier02(Token t) {
        String word = t.getToken();
        for (int i = 0; i < word.length(); i++) {
            if (!TSR.isLetter(word.charAt(i)) && !TSR.isDigit(word.charAt(i))) {
                return false;
            }
        }
        t.setType(TypeClass.Identificadores);
        return true;
    }
}
