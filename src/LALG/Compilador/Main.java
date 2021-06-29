package LALG.Compilador;

import java.util.logging.Logger;
import LALG.Interface.TelaPrincipal;
import LALG.Logger.JTextPaneAppender;
import LALG.Logger.MainLogger;

/** Main - Classe de inicialização do Compilador LALG
 *
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 *
 */
public class Main {
    
    // Chamada para geração de relatórios de log
    static final Logger logger = Logger.getLogger("Logger");

    /** Metodo que executa a Tela Principal do Compilador LALG
     *
     * @param args
     */
    public static void main(String[] args) {
        TelaPrincipal principal = new TelaPrincipal();
        principal.setVisible(true);
        MainLogger.init(new JTextPaneAppender(principal.getjTextPane1()), org.apache.log4j.Level.TRACE);
    }
}
