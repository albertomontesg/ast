package previ_socket;

import channel.TSMessage;
import channel.ChannelIP;
import transport.Protocol;

public class Protocol_stateless implements Protocol {

    protected ChannelIP tx_channel;
    RawInputStream inputStream;

    public Protocol_stateless(ChannelIP tx_channel, RawInputStream inputStream) {
        this.tx_channel = tx_channel;
        this.inputStream = inputStream;
    }

    public synchronized void connect() {
        
		// a completar
    }

    public synchronized void accept() {
        
		// a completar
		
    }

    public synchronized void sendData(byte[] data, int data_length) {
        
		// a completar
		
    }

    public synchronized void processReceivedMessage(TSMessage message) {

        // a completar
		
    }

    public synchronized void close() {
        
		// a completar
		
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
