/**
 * @author Alberto Montes
 * @subject AST
 * Problemas Programacion Concurrente
 * 3 - Monitores
 * Problema 7
 */

public interface Table {
	void startRead();
	void endRead();
	void startWrite();
	void endWrite();
}