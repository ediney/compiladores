package LALG.AnalisadorSintatatico;

import LALG.Automatos.TypeClass;
import java.util.ArrayList;
import java.util.List;

/** Procedimento - Classe que analisa os procedimentos
 * da Linguagem LALG
 *
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 *
 */
public class Procedimento {

    public String ident;
    public int num_par;
    private List<Var> var;
    private List<Var> par;

    /** Método que recebe um identificador
     * e cria dois ArrayList var e par
     *
     * @param ident
     */
    public Procedimento(String ident) {
        this.ident = ident;
        var = new ArrayList<Var>();
        par = new ArrayList<Var>();

    }

    /** Método construtor dos vetores var e par
     *
     */
    public Procedimento() {
        this.ident = null;
        var = new ArrayList<Var>();
        par = new ArrayList<Var>();

    }

    /** Método para adicionar variaveis na Lista de variaveis
     *
     * @param var
     */
    public void add_var(List<Var> var) {
        for (Var var1 : var) {
            this.getVar().add(var1);
        }
    }

    /** Método para adicionar parametros na Lista de Parametros
     *
     * @param par
     */
    public void add_par(List<Var> par) {
        for (Var var_t : par) {
            this.getPar().add(var_t);
        }
        this.num_par += par.size();
    }

    /** Método que verifica se determinada variavel pertence a Lista
     *
     * @param var
     * @return
     */
    public boolean contains_var(Var var) {
        boolean rs = false;
        for (Var var1 : this.getVar()) {
            if (var1.equals(var)) {
                rs = true;
            }
        }
        return rs;
    }

    /** Método para verificar se determinado parametro esta na lista
     *
     * @param par
     * @return
     */
    public boolean contains_par(Var par) {
        boolean rs = false;
        for (Var var1 : this.getPar()) {
            if (var1.equals(par)) {
                rs = true;
            }
        }
        return rs;
    }

    /** Metodo de requisicao de variaveis
     *
     * @param var
     * @return
     */
    public Var request_var(Var var) {
        Var return_var = new Var();
        for (Var var1 : this.getVar()) {
            if (var1.ident.equals(var.ident)) {
                return_var = var1;
                return return_var;
            }
        }
        return return_var;
    }

    /** Metodo de requisicao de parametros
     *
     * @param par
     * @return
     */
    public Var request_par(Var par) {
        Var return_par = new Var();
        for (Var par1 : this.getPar()) {
            if (par1.ident.equals(par.ident)) {
                return_par = par1;
                return return_par;
            }
        }
        return return_par;
    }
    
    /** Método de contagem de parametros
     *
     * @return
     */
    public int par_count() {
        return getPar().size();
    }

    /** Método de contagem de variaveis
     *
     * @return
     */
    public int var_count() {
        return getVar().size();
    }

    /** Metodo que pega parametro para adicionar na Lista
     * @return the par
     */
    public List<Var> getPar() {
        return par;
    }

    /** Método que pega variavel para adicionar na Lista
     * @return the var
     */
    public List<Var> getVar() {
        return var;
    }
}
