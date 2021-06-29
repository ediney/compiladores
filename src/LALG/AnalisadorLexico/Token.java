package LALG.AnalisadorLexico;

import LALG.Logger.MainLogger;
import LALG.Automatos.TypeClass;

/** TOKEN - Classe dos tokens da Linguagem LALG
 *
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 *
 */
public class Token {

    private int line;
    private String token;
    private TypeClass type;
    
    /** Método de análise de um token
     * recebe como parametros o token, bem como seu tipo e a linha onde
     * se encontra no código
     * 
     * @param line
     * @param token
     * @param type
     */
    public Token(int line, String token, TypeClass type) {
        String cToken = new String();
        // Trata espaço fantasma [ :) ]
        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);
            if (c != ' ') {
                cToken += c;
            }
        }
        this.line = line;
        this.token = cToken;
        this.type = type;
    }

    /** Pega a linha do código onde se encontra o token
     * @return the line
     */
    public int getLine() {
        return line;
    }

    /** Seta a linha do token
     * @param line the line to set
     */
    public void setLine(int line) {
        this.line = line;
    }

    /** Pega o token no código para a análise
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /** Seta os valores do token para analise
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /** Pega o tipo do token para analise da classe TypeClass
     * @return the type
     */
    public TypeClass getType() {
        return type;
    }

    /** Seta o tipo do token na analise da classe TypeClass e gera log
     * @param type the type to set
     */
    public void setType(TypeClass type) {
        // MainLogger.logInfo("Token <" + this.getToken() + "> encontrado do tipo <" + type + ">.");
        this.type = type;
    }
}
