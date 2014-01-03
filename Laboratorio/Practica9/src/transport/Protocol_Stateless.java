package transport;

import channel.TSMessage;

/**
 * @author Alberto Montes
 * @date 2-dec-2013
 * @subject AST
 * @exercise Practica8: Implementacio de Protocols // Part 2
 * 			Reliable transmission over a totally reliable channel
 */
public class Protocol_Stateless implements Protocol {
    
    Transport transport;
    
    public Protocol_Stateless(Transport transport){
        this.transport = transport;
    }
    
    public synchronized void connect() {
    	// Create and send the SYN Packet through the transport layer
    	transport.sendPacket(2,new byte[0], 0);
		
		// Block the thread until the SYN+ACK is received
		try {wait();} catch (InterruptedException e) {}
		System.out.println("Connected");
    }
    
    public synchronized void accept(){
    	System.out.println("\tProtocol accepting connections");

        // Block the thread until the client send a SYN packet to the server
        try{wait();} catch(InterruptedException e) {}
        // The packet would have been received so the connection is established
        System.out.println("Connection accepted");
    }
    
    public synchronized void sendData(byte[] data, int data_length) {
    	// Create the data packet with the PSH flag up and sent through the transport layer
    	transport.sendPacket(8, data, data_length);
    }
    
    public synchronized void processReceivedMessage(TSMessage message) {
    	// Case the received packet have the FIN flag up
		if (message.fin) {
			
			// Case the packet is a FIN packet
			if (!message.ack) {
				// Send the FIN+ACK packet through the transport layer
				transport.sendPacket(17, new byte[0], 0);
			}
			// Case the packet is a FIN+ACK packet notify the thread block with
			// the close() method
			else
				notifyAll();
		}
		// Case the received packet have the FIN flag up
		else if (message.syn) {
			
			// Case the packet is a SYN packet
			if (!message.ack) {
				// Send the SYN+ACK packet through the transport layer
				transport.sendPacket(18, new byte[0], 0);
				
				// Notify the thread blocked with the accept() method to wake up
				notifyAll();
			}
			// Case the packet is a SYN+ACK packet notify the thread block with
			// the connect() method
			else
				notifyAll();
		}
		// Case the received packet have the PSH flag up
		else if (message.psh) {
			// If the packet is received correctly send the data to the transport layer
			if (message.checksum == 0)
				transport.receivedData(message.data, message.data.length);

		}
    }
    
    public synchronized void close() {
    	// Create and send the SYN Packet through the transport layer
    	transport.sendPacket(1, new byte[0], 0);
        
    	// Block the thread until the FIN+ACK is received
        try{wait();} catch(InterruptedException e) {}
        // At this point the FIN+ACK would have been received so the channel is closed

    }
    
    public synchronized void wakeUp(){
        notify();
    }
    
    public int SeqNum(){
        return 0;
    }
    
    public int AckNum(){
        return 0;
    }
}
