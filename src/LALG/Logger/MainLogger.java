package LALG.Logger;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/** MainLogger - classe geradora de mensagens do tipo log
 *
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 */
public class MainLogger {
    
    public static Logger log = Logger.getLogger("Logger");
    private static boolean init = false;
    
    private MainLogger() {
    }

    /** init - Método de inicialização
     *
     * @param appender
     * @param l
     */
    public static void init(Appender appender, Level l) {
        if (init != true) {
            log.addAppender(appender);
            log.setLevel(l);
        }
        init = true;
    }

    /** logWarn - Método para mensagens de alerta
     *
     * @param s
     */
    public static void logWarn(final String s) {
        log.warn(s);
    }

    /** logError - Método para mensagens de erro (string)
     *
     * @param s
     */
    public static void logError(final String s) {
        log.error(s);
    }

    /** logError - Método para mensagens de erro (Throwable)
     *
     * @param ex
     */
    public static void logError(final Throwable ex) {
        log.fatal(new String(), ex);
    }

    /** logInfo - Método para mensagens informativas
     *
     * @param s
     */
    public static void logInfo(final String s) {
        log.info(s);
    }
}
