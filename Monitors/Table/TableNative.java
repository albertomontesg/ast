/**
 * @author Alberto Montes
 * @subject AST
 * Problemas Programacion Concurrente
 * 3 - Monitores
 * Problema 7
 * Modificacion: se le da preferencia a los escritores bloqueados si se esta leyendo,
 * y a los lectores que estan bloqueados si se esta escribiendo.
 */

public class TableNative implements Table {
	
	private boolean writing;
	private int nreaders, wait_r, wait_w, enter_r, enter_w;
	/** Se anaden contadores de los threads que se han bloqueado al leer o al escribir,
	 * junto con los threads que se han despertado para tambien escribir o leer.
	 */
	
	synchronized public void startRead() {
		if (writing || wait_w > 0) {
			wait_r++;
			do {
				try {wait();} catch(InterruptedException e) {}
			} while(enter_r == 0);
			enter_r--;
		} else {
			if (nreaders++ == 0) 
				System.out.println("----------- table: adquired for READING -----------");
		}
	}
	
	synchronized public void endRead() {
		if (--nreaders == 0 && wait_w > 0) {
			System.out.println("----------- table: released for READING ------------");
			writing = true;
			enter_w++;
			wait_w--;
			if (wait_r > 0)
				notifyAll(); // Notifica a lectors y escriptors
			else
				notify(); // Nomes hi haura escriptors bloquejats y notifiquem el primer
		}
	}
	
	synchronized public void startWrite() {
		if (nreaders > 0 || writing) {
			wait_w++;
			do {
				try{wait();} catch(InterruptedException e) {}
			} while(enter_w == 0);
			enter_w--;
		} else
			writing = true;
		System.out.println("----------- table: adquired for WRITING -----------");
	}
	
	synchronized public void endWrite() {
		System.out.println("----------- table: released for WRITING -----------");
		writing = false;
		if (wait_r > 0) {
			System.out.println("----------- table: adquired for READING -----------");
			enter_r = wait_r;
			nreaders = enter_r;
			wait_r = 0;
			notifyAll();
		} else if(wait_w > 0) {
			writing = true;
			wait_w--;
			enter_w++;
			notify();
		}
		
	}
}