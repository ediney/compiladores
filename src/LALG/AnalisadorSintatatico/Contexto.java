/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LALG.AnalisadorSintatatico;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/** Contexto - Classe que contextualiza a sintaxe da Linguagem LALG
 *
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 *
 */
public class Contexto {

    public Procedimento escopo;
    public List<Procedimento> proc;
    public static Logger log = Logger.getLogger(Contexto.class);

    public Contexto() {
        proc = new ArrayList<Procedimento>();

    }

    /** Método que retorna o tipo da variavel
     *
     * @param type
     * @param var
     */
    public void to_type_var (String type, List<Var> var) {
        for (Iterator<Var> it = var.iterator(); it.hasNext();) {
            it.next().tipo = type;
        }
    }

    /** Metodo de requisicao de variavel no escopo local e global
     *
     * @param var_id
     * @param escopo_local
     * @return
     */
    public Var request_var (String var_id, Procedimento escopo_local) {
        Var return_var = new Var();
        Var var = new Var(var_id);
        return_var.ident = var.ident;

        if (escopo_local.equals (this.escopo)) {
            // Verifica a consistência da requisição
            if (escopo.request_var(var).ident != null) {
                //Procura no escopo local
                return_var = escopo.request_var(var);
                log.log(Priority.DEBUG, "Achou variável [" 
                        + var.ident + "] no escopo LOCAL ["+escopo.ident+"]");

                return return_var;
            } else if (proc.get(0).request_var(var).ident != null) {
                // Procura no escopo Global
                return_var = proc.get(0).request_var(var);
                log.log(Priority.DEBUG, "Achou variável [" + var.ident + "] no escopo GLOBAL");

                return return_var;
            }
        }
        return return_var;
    }

    /** Metodo de requisicao de variavel local
     *
     * @param var_id
     * @param escopo_local
     * @return
     */
    public Var request_var_local (String var_id, Procedimento escopo_local) {
        Var return_var = new Var();
        Var var = new Var(var_id);
        return_var.ident = var.ident;

        if (escopo_local.equals(this.escopo)) {
            // Verifica a consistência da requisição
            if (escopo.request_var(var).ident != null) {
                // Procura no escopo local
                return_var = escopo.request_var(var);
                log.log(Priority.DEBUG, "Achou variável [" + var.ident 
                        + "] no escopo LOCAL ["+ escopo.ident +
                        "] no teste secundário");

                return return_var;
            }
        }
        return return_var;
    }

    /** Método de requisicao de variavel global
     *
     * @param var_id
     * @param escopo_local
     * @return
     */
    public Var request_var_global (String var_id, Procedimento escopo_local) {
        Var return_var = new Var();
        Var var = new Var(var_id);
        return_var.ident = var.ident;

        if (escopo_local.equals(this.escopo)) {
            // Verifica a consistência da requisição
            if (proc.get(0).request_var(var).ident != null) {
                // Procura no escopo Global
                return_var = proc.get(0).request_var(var);
                log.log(Priority.DEBUG, "Achou variável [" + var.ident 
                        + "] no escopo GLOBAL");
                return return_var;
            }
        }
        return return_var;
    }

    /** Método de requisicao de parametro no escopo local
     *
     * @param par_id
     * @param escopo_local
     * @return
     */
    public Var request_par (String par_id, Procedimento escopo_local) {
        Var return_par = new Var();
        Var par = new Var(par_id);
        return_par.ident = par.ident;

        if (escopo_local.equals (this.escopo)) {
            // Verifica a consistência da requisição
            if (escopo.request_par(par).ident != null) {
                // Procura no escopo local
                return_par = escopo.request_par(par);
                log.log(Priority.DEBUG, "Achou parâmetro [" 
                        + par.ident + "] no escopo LOCAL");

                return return_par;
            }
        }
        return return_par;
    }

    /** Método de requisicao de procedimento
     *
     * @param proc_id
     * @return
     */
    public Procedimento request_proc(String proc_id) {
        Procedimento return_proc = new Procedimento();
        Procedimento proc_procurado = new Procedimento(proc_id);
        return_proc.ident = null;
        for (Procedimento proc1 : this.proc) {
            if (proc1.ident.equals(proc_procurado.ident)) {
                return_proc = proc1;
                return return_proc;
            }
        }
        return return_proc;
    }

    /** Método que verifica variavel
     *
     * @param var_id
     * @return
     */
    public boolean contains_var(String var_id) {
        boolean rs = false;
        Var var = new Var(var_id);
        for (int i = proc.size() - 1; i >= 0; i--) {
            if (proc.get(i).contains_var(var)) {
                rs = true;
            }
        }
        return rs;
    }
}
