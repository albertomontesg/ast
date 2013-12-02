package channel;

import util.Buffer;
import static java.lang.System.out;

/**
 * @author Alberto Montes
 * @date 2-dec-2013
 * @subject AST
 * @exercise Practica8: Implementacio de Protocols // Part 2
 * 			Reliable transmission over a totally reliable channel
 */
public class ChannelIP {
    private static final double LOSS_PROBABILITY = 0;
    private static final double ERROR_PROBABILITY = 0;
	
	// Buffer with which the channel will be simulated
	private Buffer<TSMessage> buffer;
	public int id;

	public ChannelIP() {
		this.buffer = new Buffer<TSMessage>();
	}
	
	public ChannelIP(int capacity){
		this.buffer = new Buffer<TSMessage>(capacity);
    }
	
	/** Get the packet from the buffer and check if it has an error or is lost.
	 * Return the corresponding packet
	 */
	public TSMessage receive(){
		// Get the packet from the buffer
		TSMessage packet = buffer.get();
		// Check if the packet is lost or have an error
		if (packetIsLost(LOSS_PROBABILITY, packet)) 
			return null; // If it is lost nothing will be received
		if (packetInError(ERROR_PROBABILITY, packet)) 
			packet.setError(); // Set the packet to have an error changing his checksum
		// Return the received packet
		return packet;
    }
	
	/* Put the packet into the buffer of the IP Channel 
	 */
	public void send(TSMessage packet){
        buffer.put(packet);
	}
	
	/* Call method wakeUpToClose from the buffer to close it
	 */
    public void wakeUpToClose(){
    	buffer.wakeUpToClose();
    }
    
    /** Modelate randomly if the packet is lost and print the trace
     */
    private boolean packetIsLost(double packet_loss_probability, TSMessage packet){
        boolean b = Math.random() < packet_loss_probability;
        
    	// Print trace of the Error
        if (b) out.println("* The next packet has been LOST: " + packet);
    	
        return b;
    }
    
    /** Modelate randomly if the packet has got an error and print the trace of the error
     */
    private boolean packetInError(double packet_error_probability, TSMessage packet){
    	boolean b = Math.random() < packet_error_probability;
    	
    	// Print trace of the Error
    	if (b) out.println("* The next packet has an ERROR: " + packet);
    	
    	return b;
    }
    
}