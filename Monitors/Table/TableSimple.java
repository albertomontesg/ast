/**
 * @author Alberto Montes
 * @subject AST
 * Problemas Programacion Concurrente
 * 3 - Monitores
 * Problema 7
 */

public class TableSimple implements Table {
	
	private boolean writing;
	private int nreaders;
	
	synchronized public void startRead() {
		while (writing)
			try {wait();} catch(InterruptedException e) {}
		if (nreaders++ == 0) 
			System.out.println("----------- table: adquired for READING -----------");
	}
	
	synchronized public void endRead() {
		if (--nreaders == 0) {
			System.out.println("----------- table: released for READING ------------");
			notify(); // Nomes estaran bloquejats threads per escriure per lo que nomes sera necesari notificar a un d'ells
		}
	}
	
	synchronized public void startWrite() {
		while (nreaders > 0 || writing)
			try{wait();} catch(InterruptedException e) {}
		writing = true;
		System.out.println("----------- table: adquired for WRITING -----------");
	}
	
	synchronized public void endWrite() {
		writing = false;
		System.out.println("----------- table: released for WRITING -----------");
		notifyAll();
	}
}