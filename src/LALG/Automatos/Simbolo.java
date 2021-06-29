/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LALG.Automatos;

import LALG.Compilador.TSR;
import LALG.AnalisadorLexico.Token;

/** Simbolo - Classe que analisa os simbolos especiais e os simbolos duplos
 *
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 *
 */
public abstract class Simbolo {

    public static boolean run(Token t) {
        if (e1(t)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean e1(Token t) {
        String word = t.getToken();
        if (TSR.isSymbol(word.charAt(0))) {
            if (TSR.isDoubleSymbol(word)) {
                t.setType(TypeClass.SimbolosDuplos);
                return true;
            } else {
                if (word.length() == 1) {
                    t.setType(TypeClass.SimbolosEspeciais);
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
