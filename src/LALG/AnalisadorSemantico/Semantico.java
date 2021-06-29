package LALG.AnalisadorSemantico;

import LALG.Logger.MainLogger;
import LALG.AnalisadorLexico.Token;
import LALG.AnalisadorSintatatico.Contexto;
import LALG.AnalisadorSintatatico.Syntax;
import LALG.Compilador.Helper;
import java.util.Stack;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import LALG.Compilador.TokensList;
import LALG.Automatos.TypeClass;
import LALG.AnalisadorSintatatico.Procedimento;
import LALG.AnalisadorSintatatico.Var;
import java.util.List;

/** Analisador Semantico da Linguagem LALG
 *
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 *
 */
public abstract class Semantico {

    public static Logger log = Logger.getLogger(Semantico.class);
    public static Stack pilha_expressao = new Stack();
    public static Stack pilha_par_reais_proc_usuario = new Stack();
    public static Stack pilha_par_reais_proc_sistema = new Stack();
    public static Stack pilha_identificadores = new Stack();
    public static Stack pilha_proc_usuario = new Stack();
    public static Stack pilha_erro = new Stack();

    public static boolean run() {
        MainLogger.logInfo("[" + Helper.getTimeStamp() 
                + "] Iniciando Analise Semantica");

        Semantico.pilha_expressao = new Stack();
        Semantico.pilha_identificadores = new Stack();
        Semantico.pilha_par_reais_proc_usuario = new Stack();
        Semantico.pilha_par_reais_proc_sistema = new Stack();
        Semantico.pilha_erro = new Stack();
        Semantico.pilha_proc_usuario = new Stack();
        return true;
    }

    /** Método que verifica a semântica em uma atribuição
     * 
     */
    public static void CheckSemanticAtrib() {
        log.log(Priority.DEBUG, "Iniciando Analise Semantica de Atribuição");
        while ((pilha_expressao.size() >= 1) 
                && (pilha_identificadores.size() >= 1)) {
            Token t1 = (Token) pilha_identificadores.pop();
            Token t2 = (Token) pilha_expressao.pop();
            if (t1.getType() == t2.getType()) {
                pilha_expressao.push(t2);
            } else {
                error_s (TokensList.getActualLine(), 
                        "Atribuição com incompatibilidade de tipo. Pois [" 
                        + t1.getToken() + "] é do tipo [" 
                        + t1.getType() + "] e [" + t2.getToken() 
                        + "] é do tipo [" + t2.getType() + "]!");
            }
        }
    }
    
    /** Método que verifica a semântica em uma expressão
     *
     */
    public static void CheckSemanticExp() {
        log.log(Priority.DEBUG, "Iniciando análise semântica de expressão");
        while (pilha_expressao.size() >= 2) {
            Token t1 = (Token) pilha_expressao.pop();
            Token t2 = (Token) pilha_expressao.pop();
            if (t1.getType() == t2.getType()) {

                pilha_expressao.push(t2);
            } else {
                error_s(TokensList.getActualLine(), 
                        "Expressão com incompatibilidade de tipo. Pois [" 
                        + t1.getToken() + "] é do tipo [" + t1.getType() 
                        + "] e [" + t2.getToken() + "] é do tipo [" 
                        + t2.getType() + "]!");
            }
        }
        CheckSemanticAtrib();
    }

    /** Método que checa a semântica de parametros reais e formais
     *
     * @param contexto
     */
    public static void CheckSemanticParReaisUsr(Contexto contexto) {
        log.log(Priority.DEBUG, "Iniciando análise semântica "
                + "dos parâmetros reais e formais");
        if (pilha_par_reais_proc_usuario.size() >= 0 
                && pilha_proc_usuario.size() >= 1) {
            Object[] par_r = pilha_par_reais_proc_usuario.toArray();
            /*Descobrindo a linha*/

            Object object_temp = pilha_proc_usuario.pop();
            Procedimento proc = (Procedimento) object_temp;
            List<Var> par_f = proc.getPar();

            if (par_r.length == par_f.size()) {

                for (int i = 0; i < par_r.length; i++) {
                    Token par_r_1 = (Token) par_r[i];
                    Var par_f_1 = par_f.get(i);
                    /*Transformação de um tipo no outro*/
                    Var par_r_1_t = new Var(par_r_1.getToken());
                    if (par_r_1.getType().equals(TypeClass.InteirosSemSinal)) {
                        par_r_1_t.tipo = "integer";
                    } else if (par_r_1.getType().equals(TypeClass.NumerosReais)) {
                        par_r_1_t.tipo = "real";
                    }
                    /*Comparação*/
                    if (!par_r_1_t.tipo.equals(par_f_1.tipo)) {
                        error_s (par_r_1.getLine(), 
                                "Incompatibilidade de tipo entre "
                                + "parâmetros formais e reais. "
                                + "O procedimento [" + proc.ident + "] em seu " 
                                + (i + 1) + "º parâmetro esperava o tipo [" 
                                + par_f_1.tipo + "] e encontrou "
                                + "o parâmetro [" + par_r_1_t.ident 
                                + "] do tipo [" + par_r_1_t.tipo + "] ");
                    }
                }
            } else {
                error_s (TokensList.getActualLine() - 1, 
                        "Incompatibilidade de número de parâmetros."
                        + " O procedimento [" + proc.ident + "] possui " 
                        + par_f.size() + " parâmetro(s) formal(ais) "
                        + " e encontrou " + par_r.length 
                        + " parâmetro(s) real(ais)]");
            }
        }
        pilha_par_reais_proc_usuario = new Stack();
    }

    /** Método que exibe erro semântico e a linha em que ele se encontra
     *
     * @param line
     * @param erro
     */
    public static void error_s (int line, String erro) {
        MainLogger.logError ("Semantic - " + "Linha: " 
                + line + " [" + erro + "]");
    }

    /** Método de preenchimento da pilha em um contexto
     *
     * @param pilha
     * @param contexto
     */
    public static void preenchePilha(Stack pilha, Contexto contexto) {

        /*:::::::::::::::::::::: Parte semântica ::::::::::::::::::::::*/
        Token t = TokensList.getToken (TokensList.getActualToken());
        if (t.getType() == TypeClass.Identificadores) {
            /*Percorrendo o contexto do escopo mais interno para o mais externo
             * ver se o token é alguma variável declarada e qual é seu tipo*/
            Token temp_t = t;
            Var var = contexto.request_var (t.getToken(), contexto.escopo);
            /*Variável tem preferencia a parâmetro*/
            if (var.tipo == null) {
                var = contexto.request_par (t.getToken(), contexto.escopo);
                System.out.println ("RESGATE: PAR:" + var.ident 
                        + "TIPO:" + var.tipo);
                if (var.tipo != null) {
                    if (var.tipo.equals ("real")) {
                        temp_t.setToken (var.ident);
                        temp_t.setType (TypeClass.NumerosReais);
                    } else if (var.tipo.equals ("integer")) {
                        temp_t.setToken (var.ident);
                        temp_t.setType (TypeClass.InteirosSemSinal);
                    } else {
                        log.log (Priority.ERROR, "Tipo da parâmetro [" 
                                + t.getToken() + "] não existente!");
                    }
                } else {
                    error_s (TokensList.getActualLine(), "Variável [" 
                            + t.getToken() + "] não declarada!");
                    t.setType(TypeClass.ERRO);
                }
                if (t.getType() == TypeClass.ERRO) {
                    Semantico.pilha_erro.push(temp_t);
                } else {
                    pilha.push(temp_t);
                }
            } else {
                if ((contexto.request_par (t.getToken(), 
                        contexto.escopo).tipo != null) && 
                        (contexto.request_var_local(t.getToken(), 
                        contexto.escopo).tipo != null)) {
                    System.out.println ("FOI NA PILHA DE " + pilha.toString());
                    error_s (TokensList.getActualLine(), "Variável [" 
                            + t.getToken() + "] está em conflito com parâmetro"
                            + " [" + t.getToken() + "]!");
                }
                System.out.println("RESGATE: VAR:" + var.ident 
                        + "TIPO:" + var.tipo);

                if (var.tipo != null) {
                    if (var.tipo.equals ("real")) {
                        temp_t.setToken (var.ident);
                        temp_t.setType (TypeClass.NumerosReais);
                    } else if (var.tipo.equals("integer")) {
                        temp_t.setToken (var.ident);
                        temp_t.setType (TypeClass.InteirosSemSinal);
                    } else {
                        log.log (Priority.ERROR, "Tipo da variável [" 
                                + t.getToken() + "] não existente!");
                    }
                } else {
                    error_s (TokensList.getActualLine(), "Variável [" 
                            + t.getToken() + "] não declarada!");
                    t.setType(TypeClass.ERRO);
                }
                if (t.getType() == TypeClass.ERRO) {
                    Semantico.pilha_erro.push(temp_t);
                } else {
                    pilha.push(temp_t);
                }
            }
        } else {
            if (t.getType() == TypeClass.ERRO) {
                Semantico.pilha_erro.push(t);
            } else {
                pilha.push(t);
            }
        }
        /*:::::::::::::::::::: Fim da Parte semântica ::::::::::::::::::::::*/
    }

    /** Método de preenchimento da pilha - ident
     *
     * @param contexto
     */
    public static void preenchePilhaIdent(Contexto contexto) {
        /*:::::::::::::::::::::: Parte semântica ::::::::::::::::::::::*/
        Token actual = TokensList.getToken(TokensList.getActualToken());
        Token ant = TokensList.getToken(TokensList.getActualToken() - 1);
        log.log(Priority.DEBUG, 
                ":::::::::::::::::::::::::::::::::::::::::::::::::::::ANTANT:" 
                + ant.getToken() + " ANT:" + actual.getToken());
        if (actual.getToken().equals(":=") && ant.getType() == TypeClass.Identificadores) {


            if (ant.getType() == TypeClass.Identificadores) {
                /* Percorrendo o contexto do escopo mais interno 
                 * para o mais externo ver se o token é alguma variável 
                 * declarada e qual é seu tipo*/
                Token temp_t = ant;
                Var var = contexto.request_var(ant.getToken(), contexto.escopo);
                /* Variável tem preferencia a parâmetro*/
                if (var.tipo == null) {
                    var = contexto.request_par(ant.getToken(), contexto.escopo);
                    System.out.println ("RESGATE: PAR:" + var.ident 
                            + "TIPO:" + var.tipo);
                    if (var.tipo != null) {
                        if (var.tipo.equals ("real")) {
                            temp_t.setToken (var.ident);
                            temp_t.setType (TypeClass.NumerosReais);
                        } else if (var.tipo.equals("integer")) {
                            temp_t.setToken (var.ident);
                            temp_t.setType (TypeClass.InteirosSemSinal);
                        } else {
                            log.log (Priority.ERROR, "Tipo da parâmetro [" 
                                    + ant.getToken() + "] não existente!");
                        }
                    } else {
                        error_s (TokensList.getActualLine(), "Variável [" 
                                + ant.getToken() + "] não declarada!");
                        ant.setType (TypeClass.ERRO);
                    }
                    if (ant.getType() == TypeClass.ERRO) {
                        Semantico.pilha_erro.push(temp_t);
                    } else {
                        pilha_identificadores.push(ant);
                    }
                } else {
                    if ((contexto.request_par(ant.getToken(), 
                            contexto.escopo).tipo != null) && 
                            (contexto.request_var_local(ant.getToken(), 
                            contexto.escopo).tipo != null)) {
                        System.out.println ("FOI NA PILHA DE IDENTIFICADOR");
                        error_s (TokensList.getActualLine(), 
                                "Variavél [" + ant.getToken() + "] "
                                + "está em conflito com parâmetro [" 
                                + ant.getToken() + "]!");
                    }
                    System.out.println ("RESGATE: VAR:" + var.ident 
                            + "TIPO:" + var.tipo);

                    if (var.tipo != null) {
                        if (var.tipo.equals ("real")) {
                            temp_t.setToken (var.ident);
                            temp_t.setType (TypeClass.NumerosReais);
                        } else if (var.tipo.equals ("integer")) {
                            temp_t.setToken (var.ident);
                            temp_t.setType (TypeClass.InteirosSemSinal);
                        } else {
                            log.log (Priority.ERROR, "Tipo da variavel [" 
                                    + ant.getToken() + "] não existente!");
                        }
                    } else {
                        error_s (TokensList.getActualLine(), 
                                "Variavél [" + ant.getToken()
                                + "] não declarada!");
                        ant.setType(TypeClass.ERRO);
                    }
                    if (ant.getType() == TypeClass.ERRO) {
                        Semantico.pilha_erro.push(temp_t);
                    } else {
                        pilha_identificadores.push(ant);
                    }
                }
            } else {
                if (ant.getType() == TypeClass.ERRO) {
                    Semantico.pilha_erro.push(ant);
                } else {
                    pilha_identificadores.push(ant);
                }
            }
            /*::::::::::::::::: Fim da Parte semântica ::::::::::::::::::::::*/
        }
    }

    /** Método de preenchimento da pilha - proc
     *
     * @param contexto
     */
    public static void preenchePilhaProc(Contexto contexto) {

        /*:::::::::::::::::::::: Parte semântica ::::::::::::::::::::::*/
        Token t = TokensList.getToken(TokensList.getActualToken());
        if (t.getType() == TypeClass.Identificadores) {
            /*Percorrendo o contexto do escopo mais interno para o mais externo
             * ver se o token é alguma variável declarada e qual é seu tipo*/
            Token temp_t = t;
            Procedimento proc = contexto.request_proc(t.getToken());
            if (proc.ident != null) {
                pilha_proc_usuario.push(proc);
            } else {
                error_s (TokensList.getActualLine(), 
                        "Procedimento [" + t.getToken() + "] não declarado!");
            }

            /*::::::::::::::::: Fim da Parte semântica ::::::::::::::::::::::*/
        }

    }

    /** Método da Pilha de Expressão
     *
     * @return
     */
    public static String toStringSemantic() {
        String s = "\n";

        s += "::::::::Pilha de Expressão::::::::\n";
        for (Object object : pilha_expressao) {
            Token token = null;
            try {
                token = (Token) object;
            } catch (Exception e) {
                log.log (Priority.ERROR, e + "  : Erro na conversão de tipos");
            }
            s = s + "| " + token.getToken() + " : " + token.getType() + " |\n";
        }
        s += "::::::::::::::::::::::::::::::::::\n";
        s += ":::::Pilha de Identificadores:::::\n";
        for (Object object : pilha_identificadores) {
            Token token = null;
            try {
                token = (Token) object;
            } catch (Exception e) {
                log.log (Priority.ERROR, e + "  : Erro na conversão de tipos");
            }
            s = s + "| " + token.getToken() + " : " + token.getType() + " |\n";
        }
        s += "::::::::::::::::::::::::::::::::::\n";
        s += "::Pilha de Parâmetros Reais de Procedimento de Usuário::\n";
        for (Object object : pilha_par_reais_proc_usuario) {
            Token token = null;
            try {
                token = (Token) object;
            } catch (Exception e) {
                log.log (Priority.ERROR, e + "  : Erro na conversão de tipos");
            }
            s = s + "| " + token.getToken() + " : " + token.getType() + " |\n";
        }
        s += "::::::::::::::::::::::::::::::::::\n";
        s += "::Pilha de Parâmetros Reais de Procedimento de Sistema::\n";
        for (Object object : pilha_par_reais_proc_sistema) {
            Token token = null;
            try {
                token = (Token) object;
            } catch (Exception e) {
                log.log (Priority.ERROR, e + "  : Erro na conversão de tipos");
            }
            s = s + "| " + token.getToken() + " : " + token.getType() + " |\n";
        }
        s += "::::::::::::::::::::::::::::::::::\n";

        s += "::::::::::::Pilha de Procedimentos de Usuário:::::::::\n";
        for (Object object : pilha_proc_usuario) {
            Procedimento proc = null;
            try {
                proc = (Procedimento) object;
            } catch (Exception e) {
                log.log (Priority.ERROR, e + "  : Erro na conversão de tipos");
            }
            s = s + "| " + proc.ident + " : " + proc.num_par + " |\n";
        }

        s += "::::::::::::::::::::::::::::::::::\n";

        s += "::::::::::::Pilha de Erro:::::::::\n";
        for (Object object : pilha_erro) {
            Token token = null;
            try {
                token = (Token) object;
            } catch (Exception e) {
                log.log(Priority.ERROR, e + "  : Erro na conversão de tipos");
            }
            s = s + "| " + token.getToken() + " : " + token.getType() + " |\n";
        }

        s += "::::::::::::::::::::::::::::::::::\n";

        return s;
    }
}