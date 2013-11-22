/**
 * @author Alberto Montes
 * @date 18-nov-2013
 * @subject AST
 * Logger para mostrar una traza de la ejecucion de los protocolos
 */

package transport;

import java.util.logging.*;

public class Log {
	static final Logger logger = Logger.getAnonymousLogger();
	
	/**
	 * SEVERE (highest value)
	 * WARNING
	 * INFO
	 * CONFIG
	 * FINE
	 * FINER
	 * FINEST (lowest value)
	 */
	
	public static void info(String msg) {
		logger.info(msg);
	}
	
	public static void fine(String msg) {
		logger.fine(msg);
	}
	
	public static void warning(String msg) {
		logger.warning(msg);
	}
	
	public static void setLevel(level newLevel) {
		logger.setLevel(newLevel);
	}
	
}