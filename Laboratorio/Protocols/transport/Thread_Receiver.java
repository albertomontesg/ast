
package transport ;

import channel.TSMessage;

public class Thread_Receiver extends Thread {

	Transport transport;
	boolean alive;
	Thread_Receiver(Transport tr) {
		alive = true ;
		this.transport = tr;
	}
 
	public void run() {
		while (alive) {
			TSMessage pk = (TSMessage) transport.rx_channel.get();
			transport.receivedData(pk);
		}
		//mentres sigui viu, esperar a rebre un paquet nou de la capa IP,
		//comunicar el paquet rebut a la capa de transport.
		//...
	}
	
	public void terminate() {
		alive = false;
		//fer que el fil d'execucio de la classe mori
		//...
  }

}

