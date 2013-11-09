package channel;

import util.Buffer;

public class ChannelIP {
    
	// Buffer with which the channel will be simulated
	private Buffer buffer;

	public ChannelIP(int cp){
		this.buffer = new Buffer(cp);
    }
	
	public Object receive(){
		return buffer.get();
    }
	
	public void send(Object packet){
        buffer.put(packet);
	}
	
    public void wakeUpToClose(){
    	buffer.wakeUpToClose();
    }
    
    private boolean PacketIsLost(double packet_loss_probability, Object packet){
        boolean b = Math.random() < packet_loss_probability;
    	// Print trace of the Error
    	return b;
    }
    
    private boolean PacketInError(double packet_error_probability, Object packet){
    	boolean b = Math.random() < packet_error_probability;
    	// Print trace of the Error
    	return b;
    }
    
}