
package transport ;

import channel.TSMessage;

/**
 * @author Alberto Montes
 * @date 2-dec-2013
 * @subject AST
 * @exercise Practica8: Implementacio de Protocols // Part 2
 * 			Reliable transmission over a totally reliable channel
 */
public class Thread_Receiver extends Thread {

	Transport transport;
	boolean alive;
	Thread_Receiver(Transport tr) {
		alive = true ;
		this.transport = tr;
	}
 
	public void run() {
		
		/** While the thread is alive, wait until a new packet arrives and then
		 * send it to the protocol level
		 */
		while (alive) {
			TSMessage pk = transport.rx_channel.receive();
			transport.receivedPacket(pk);
		}
	}
	
	public void terminate() {
		// To end the thread, only needs to set alive to false
		alive = false;
	}

}

