/**
 * @author Alberto Montes
 * @date 18-nov-2013
 * @subject AST
 * Implementacion de clase de las clases de protocolo.
 * Pseudo-codigo
 */

package transport;

import java.io.*;
import net.*;

public class TransportAlternatingBitProtocol extends Protocol {
	
	boolean timeout;
	final long TOUT = 10;	// 10 msecs
	int eminum, recnum;
	Timer time_wait;
	
	public TransportAlternatingBitProtocol(AstSocketInputStream in, AstSocketOutputStream out) {
		inputStream = in;
		outputStream = out;
		time_wait = new Timer();
	}
	
	public synchronized void connect() {
		// create send SYN seg
		state = State.SYN_SENT;
		do {
			// send SYN seg
			try {
				timeout = true;
				wait(TOUT);
			} catch(InterruptedException e) {e.printStackTrace()}
		} while(timeout);
	}

	public synchronized void accept() {
		state = State.LISTEN;
		Log.info("TransportAlternatingBitProtocol:accept:wait");
		wait();
	}
	
	public synchronized void send(byte[] data, int length) {
		// create DATA seg ....data...length...eminum
		
		do {
			// send SYN seg
			try {
				timeout = true;
				wait(TOUT);
			} catch(InterruptedException e) {e.printStackTrace()}
			Log.info("TransportAlternatingBitProtocol:send:after wait(TOUT)");
		} while(timeout);
	}
	
	public synchronized void received(TSegment seg) {
		
	}
	
	public synchronized void close() {
		
	}
}