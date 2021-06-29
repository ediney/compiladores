package LALG.Compilador;

import LALG.AnalisadorLexico.Token;
import LALG.Automatos.TypeClass;

/** TSR - Tabela de Simbolos Reservados
 *  
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 *
 */
public abstract class TSR {
    
    /** Metodo que define o simbolo ';' (ponto-e-virgula)
     *  como delimitador de sentencas ou comandos
     */
    public static char sentenceDelimiter = ';';
    
    /** Metodo que define o simbolo ' ' (espaco em branco)
     *  como delimitador de tokens
     */
    public static char tokenDelimiter = ' ';
    
    /** Metodo que define um vetor de strings chamado reservedWords
     *  para receber as palavras reservadas da linguagem
     */
    private static String[] reservedWords = new String[] {
        "program",
        "var", "integer", "real", 
        "procedure",
        "begin",
        "read", "write",
        "do", "while", 
        "if", "then", "else",
        "end" ,"end."
    };    
    
    /** Metodo que define um vetor de strings chamado doubleSymbols
     *  para receber os simbolos duplos existentes na linguagem
     */
    private static String[] doubleSymbols = new String[] {
        // Simbolos para demarcar Comentarios
        "/*", "*/",
        
        // Simbolo de atribuicao
        ":=",
        
        // Simbolos comparativos
        "<=", ">=", "<>", "=="
    };
    
    /** Metodo que verifica se o token pertence
     *  a Classe de Tokens da LALG
     * @param token
     * @return true se pertence ou false, caso contrario
     */
    public static boolean isTerminal(Token token) {
        TypeClass tc = token.getType();
        if (tc == TypeClass.PalavrasReservadas
                || tc == TypeClass.Identificadores
                || tc == TypeClass.InteirosSemSinal
                || tc == TypeClass.NumerosReais
                || tc == TypeClass.SimbolosDuplos
                || tc == TypeClass.SimbolosEspeciais) {
            return true;
        } else {
            return false;
        }
    }
    
    /** Metodo para verificar se determinado simbolo
     *  pertence a classe dos Simbolos Duplos da LALG
     * @param simbolo
     * @return true se pertence ou false, caso contrario
     */
    public static boolean isDoubleSymbol(String simbolo) {
        for (int i = 0; i < doubleSymbols.length; i++) {
            if (doubleSymbols[i].equals(simbolo)) {
                return true;
            }
        }
        return false;
    }
    
    /** Metodo para verificar se determinado caracter
     *  trata-se de um digito (numero)
     * @param c
     * @return true se for digito, false caso contrario
     */
    public static boolean isDigit(char c) {
        String letra = String.valueOf(c);
        if (letra.matches("[0-9]")) {
            return true;
        }
        return false;
    }
    
    /** Metodo para verificar se determinado caracter
     *  trata-se de caracter especial ou simbolo
     * @param c
     * @return true se for simbolo, false caso contrario
     */
    public static boolean isSymbol(char c) {
        String letra = String.valueOf(c);
        if (letra.matches("[-:()*/+\\<>,=;${}.@¬¢£³²¹§ªº°]")) {
            return true;
        }
        return false;
    }
    
    /** Metodo para verificar se determinado caracter
     * trata-se de delimitador 
     * (espaco em branco, char 13, ponto-e-virgula, barra-invertida-n) 
     * @param ca
     * @return true se for delimitador, false caso contrario
     */
    public static boolean isDelimiter(char ca) {
        if (ca == ' ' || ca == (char) 13 || ca == ';' || ca == '\n') {
            return true;
        }
        return false;
    }
    
    /** Metodo para verificar se determinado caracter
     *  trata-se de letra do alfabeto
     * @param c
     * @return true se for letra, false caso contrario
     */
    public static boolean isLetter(char c) {
        String letra = String.valueOf(c);
        if (letra.matches("[a-zA-ZáàãâäéèêëíìîïóòõôöúùûüçÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÔÖÚÙÛÜÇ]")) {
            return true;
        }
        return false;
    }
    
    /** Metodo para verificar se uma determinada string
     *  pertence a classe das Palavras Reservadas da LALG 
     * @param w
     * @return true se pertence, false caso contrario
     */
    public static boolean isReservedWord(String w) {
        for (int i = 0; i < reservedWords.length; i++) {
            if (reservedWords[i].equals(w)) {
                return true;
            }
        }
        return false;
    }
}
