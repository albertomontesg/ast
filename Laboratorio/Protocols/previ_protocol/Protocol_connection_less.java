package previ_protocol;

import channel.TSMessage;
import channel.ChannelIP;
import transport.Protocol;

public class Protocol_connection_less implements Protocol {

    protected ChannelIP tx_channel;

    public Protocol_connection_less(ChannelIP tx_channel) {
        this.tx_channel = tx_channel;
    }

    public synchronized void connect() {}
    public synchronized void accept() {}
    public synchronized void close() {}

    public synchronized void sendData(byte[] data, int data_length) {
    	
    	//***************************************
    	
    	TSMessage message = new TSMessage(0, 0, 0, data, data_length);
    	System.out.println("Send from side: " + tx_channel.id + "\tPacket: " + message.toString());
    	tx_channel.send(message);

    	//***************************************
    }

    public synchronized void processReceivedMessage(TSMessage message) {

    	//***************************************
    	
    	//if (message != null)		// TX_Channel returns null when it close
    		System.out.println("Received from side: " + tx_channel.id + "\tPacket: " + message.toString());
    	
    	//***************************************
    }

    public synchronized void wakeUp() {
        notify();
    }

    public int SeqNum() {
        return 0;
    }

    public int AckNum() {
        return 0;
    }
}
