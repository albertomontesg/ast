
package transport;

/**
 * @author Alberto Montes
 * @date 2-dec-2013
 * @subject AST
 * @exercise Practica8: Implementacio de Protocols // Part 2
 * 			Reliable transmission over a totally reliable channel
 */
public class Thread_Transmitter extends Thread {
    
	static final int DATA_LENGTH = 5;

	Transport transport;
	boolean alive;
	byte[] data;
 	
	Thread_Transmitter(Transport tr){
		alive = true ;
		this.transport = tr;
		data = new byte[DATA_LENGTH];
	}
	
	public void run() {
		
		/** While it is alive is reading from the outputStream and send it
		 * to the protocol layer to send it
		 */
		while(alive) {
			transport.socket.outputStream.read(data, 0, DATA_LENGTH);
			transport.sendData(data, data.length);
		}
	}
	
	public void terminate() {
		// To end the thread, only needs to set alive to false
		alive = false;
	}

}

