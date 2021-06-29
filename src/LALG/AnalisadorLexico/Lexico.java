package LALG.AnalisadorLexico;

import LALG.Logger.MainLogger;
import LALG.Compilador.TSR;
import LALG.Compilador.TokensList;
import LALG.Automatos.Comentario;
import LALG.Automatos.Identificador;
import LALG.Automatos.Real;
import LALG.Automatos.Simbolo;
import LALG.Automatos.UInteiro;
import LALG.Automatos.TypeClass;
import LALG.Compilador.Helper;
import java.util.List;

/** Analisador Léxico da Linguagem LALG
 *
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 *
 */
public abstract class Lexico {

    static TypeClass type;
    static int sentenceId = 0;
    static int tokenId = 0;
    static List<String> tokens;
    static List<String> sentences;

    /** Método que roda as sentenças e inicia a Analise Lexica
     *
     * @param sentences
     */
    public static void run(List<String> sentences) {
        MainLogger.logInfo("[" + Helper.getTimeStamp() + "] Iniciando Análise Lexica");
        int ActLine = 1;
        while (!sentences.isEmpty()) {
            Helper.stractTokens(ActLine, sentences.get(0));
            ActLine++;
            sentences.remove(0);
        }
        while (TokensList.hasNext()) {
            Token t = TokensList.next();

            boolean undefined = true;
            // Caso o token inicie com Digito, só poderá corresponder a inteiro ou real
            if (TSR.isDigit(t.getToken().charAt(0))) {
                undefined = !UInteiro.run(t);
                if (undefined) {
                    undefined = !Real.run(t);
                }
            } else if (TSR.isLetter(t.getToken().charAt(0))) {
                // Se iniciar com letra poderá ser um identificador ou palavra reservada
                undefined = !Identificador.run(t);
            } else if (TSR.isSymbol(t.getToken().charAt(0))) {
                // se começar com simbolo só poderá ser simbolos especiais ou comentário
                if (undefined) {
                    // um  comentário é iniciado por um simbolo duplo ou por { } portanto,
                    // devem ser checados de forma sobreposta
                    int cont = 0;
                    undefined = !Simbolo.run(t);
                    if (!undefined) {
                        cont++; // É simbolo
                    }
                    undefined = !Comentario.run(t);
                    if (undefined && cont == 1) {
                        // É simbolo, mas não é comentário
                        undefined = false;
                    } else {
                    }
                }
            }
            // Imprime falhas se ocorrerem
            if (undefined) {
                MainLogger.logError("Lexico - Linha: " + TokensList.getActualLine() + " com o token " + t.getToken());
            }
        }
        MainLogger.logInfo("[" + (Helper.getTimeStamp().toString()) + "] Análise Léxica concluída");
        //TokensList.debugList();
    }
}
