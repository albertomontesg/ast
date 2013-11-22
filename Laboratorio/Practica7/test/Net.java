package test;

import channel.*;
import static java.lang.System.out;

public class Net {
	
	/** Code to make the simulation of Channel creating one thread sending packets and another one receiving them
	 */
    public static void main(String[] args) {
    	// Create the channel where the packets will be sent
    	ChannelIP channel = new ChannelIP();
    	
    	// Create two threads, one tranmiter and one receiver which will be sending and receiving packets
    	Thread transmiter = new Thread(new Transmiter(channel), "transmiter");
    	Thread receiver = new Thread(new Receiver(channel), "receiver");
    	transmiter.start();
    	receiver.start();
    	
    }
    
}

/**
 * Transmiter thread which will be sending 10 packets to the channel, each packet with diferent
 * sequence number to keep the trace of them
 */
class Transmiter implements Runnable {
	
	public ChannelIP channel;
	
	public Transmiter (ChannelIP channel) {
		this.channel = channel;
	}
	
	public void run() {
		for (int i = 0; i < 10; i ++) {
			// Create the packet
			TSMessage packet = new TSMessage(0, i, 0, new byte[10], 10);
			// Print the trace
			out.println("+ Sent: " + packet);
			// Send the packet to the channel
			channel.send(packet);
			// Sleep during a short duration
			try {Thread.sleep(10);} catch(InterruptedException e) {}
		}
	}
	
}

/**
 * Receiver thread which will be blocked waiting until there is any new packet at the channel to receive.
 * It will receive it and print the trace of this packet
 */
class Receiver implements Runnable {
	
	public ChannelIP channel;
	
	public Receiver (ChannelIP channel) {
		this.channel = channel;
	}
	
	public void run() {
		while (true) {
			// Receive the packet
			TSMessage packet = channel.receive();
			// Print the trace
			out.println("- Received: " + packet);
		}
	}
	
}