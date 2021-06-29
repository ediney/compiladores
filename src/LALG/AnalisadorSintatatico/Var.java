package LALG.AnalisadorSintatatico;

/** Var - Classe destinada a analise de variaveis da
 * Linguagem LALG
 *
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 *
 */
public class Var {

    /** Método que recebe um identificador de variável
     *
     * @param ident
     */
    public Var(String ident) {
        this.ident = ident;
    }

    /** Método inicializador para variavel
     *
     */
    public Var() {
        this.ident = null;
    }

    /** Método toString para o identificador de variavel
     *
     * @return
     */
    @Override
    public String toString(){
       if (this.ident!=null) {
           return this.ident;
       } else{
           return "null";
       }
    }
    
    /** Objeto ident para armazenar variavel
     * utilizado no toString
     */
    public String ident;
    
    /** Objeto tipo
     * utilizado na classe Contexto
     */
    public String tipo;
  
}
