package LALG.Compilador;

import LALG.AnalisadorLexico.Token;
import LALG.AnalisadorSintatatico.Syntax;
import LALG.Logger.MainLogger;
import LALG.Automatos.TypeClass;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/** TokensList - Classe que define uma lista de tokens da linguagem LALG
 *
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 *
 */
public abstract class TokensList {

    private static int ActualLine = 0;
    private static int ActualToken = -1;
    public static List<Token> tokens = new ArrayList<Token>();
    public static Logger log = Logger.getLogger(Syntax.class);

    /** Método que pega o token no código para analise
     *
     * @param i
     * @return
     */
    public static Token getToken(int i) {
        return tokens.get(i);
    }

    /** Método que analisa o token para depois adicionar na Lista de Tokens
     *
     * @param content
     * @param type
     */
    public static void add(String content, TypeClass type) {
        // Valida o conteudo do token antes de adicionar a Lista de Tokens
        if (!(" ".equals(content)) && !("".equals(content))) {
            Token t = new Token(getActualLine(), content, type);
            tokens.add(t);
        }
    }

    /** Método que analisa o tamanho do token
     *
     * @return
     */
    public static int size() {
        return tokens.size();
    }

    /** Método que insere o token na Lista de Tokens
     *
     * @param t
     */
    public static void insertion(Token t) {
        tokens.add(t);
    }

    /** Método que realiza o debug da Lista de Tokens
     *
     */
    public static void debugList() {
        MainLogger.logWarn("Inicio debug TokenList");
        setActualToken(-1);
        while (TokensList.hasNext()) {
            Token token = TokensList.next();
            MainLogger.logWarn("<" + token.getToken() + "," + token.getType() + ">");
        }
        MainLogger.logWarn("Fim debug TokenList");
    }

    /** Método que apaga token da Lista de Tokens
     *
     */
    public static void clear() {
        tokens = new ArrayList<Token>();
        ActualLine = 0;
        ActualToken = -1;

    }

    /** Método para resetar token
     *
     */
    public static void reset() {
        ActualLine = 0;
        ActualToken = -1;

    }

    /** Método que adiciona nova linha na Lista de Tokens
     *
     */
    public static void newLine() {
        setActualLine(getActualLine() + 1);
    }

    /** Método que retorna para a linha anterior da Lista de Tokens
     *
     */
    public static void prevLine() {
        setActualLine(getActualLine() - 1);
    }

    /** Método Iterator de tokens
     *
     * @return tokens.iterator()
     */
    public static Iterator<Token> iterator() {
        return tokens.iterator();
    }

    /** Método hasNext analisa tamanho do token anterior com o atual
     *
     * @return verdadeiro se maior, falso se menor
     */
    public static boolean hasNext() {
        if ((tokens.size() - 1) > getActualToken()) {
            return true;
        }
        return false;
    }

    /** Método que passa para o proximo token
     *
     * @return tokens se conseguir ou null
     */
    public static Token next() {
        if (TokensList.hasNext()) {
            setActualToken(getActualToken() + 1);
            setActualLine(getActual().getLine());
            log.log(Priority.DEBUG, "TOKEN[" + tokens.get(getActualToken()).getToken() + "] (next)");
            return tokens.get(getActualToken());
        } else {
            return null;
        }

    }

    /** Método que seta token anterior
     *
     * @return token
     */
    public static Token prev() {
        setActualToken(getActualToken() - 1);
        setActualLine(getActual().getLine());
        log.log(Priority.DEBUG, "TOKEN[" + tokens.get(getActualToken()).getToken() + "] (prev)");
        return tokens.get(getActualToken());
    }

    /** Método para pegar o token atual
     *
     * @return
     */
    public static Token getActual() {
        return tokens.get(getActualToken());
    }

    /** Método de remoção de token
     *
     */
    public static void removeActual() {
        MainLogger.logInfo("Removing: " + getActual().getToken());
        tokens.remove(getActualToken());
        setActualToken(getActualToken() - 1);
    }

    /** Método que pega a linha atual
     * @return the ActualLine
     */
    public static int getActualLine() {
        return ActualLine;
    }

    /** Metodo que seta a linha atual
     * @param aActualLine the ActualLine to set
     */
    public static void setActualLine(int aActualLine) {
        ActualLine = aActualLine;
    }

    /** Metodo que pega o token do tipo inteiro
     * @return the ActualToken
     */
    public static int getActualToken() {
        return ActualToken;
    }

    /** Metodo que seta o token do tipo inteiro
     * @param aActualToken the ActualToken to set
     */
    public static void setActualToken(int aActualToken) {
        ActualToken = aActualToken;
    }
}
