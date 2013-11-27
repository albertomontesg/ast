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
    	
    	//***************************************
    	
    	TSMessage syn = new TSMessage(2, 0, 0, new byte[0], 0);
    	tx_channel.send(syn);
    	System.out.println("Sent: " + syn.toString());
        try{wait();} catch(InterruptedException e) {}
        System.out.println("Connected");
        
    	//***************************************
    }

    public synchronized void accept() {
        System.out.println("\t\t\t\t\t\tProtocol accepting connections");
        
        //***************************************
        
        try{wait();} catch(InterruptedException e) {}
        System.out.println("Connection accepted");
        
        //***************************************
		
    }

    public synchronized void sendData(byte[] data, int data_length) {

        //***************************************
        
    	TSMessage message = new TSMessage(8, 0, 0, data, data_length);
    	System.out.println("Send from side: " + tx_channel.id + "\tPacket: " + message.toString());
    	tx_channel.send(message);
        
        //***************************************
    }

    public synchronized void processReceivedMessage(TSMessage message) {

    	if (message.fin) {
        	System.out.println("Received: " + message.toString());
        	if (!message.ack) {
        		TSMessage fin_ack = new TSMessage(17, 0, 0, new byte[0], 0); // FIN+ACK
        		tx_channel.send(fin_ack);
            	System.out.println("Sent: " + fin_ack.toString());
            	IPReceiver ipreceiver = (IPReceiver) Thread.currentThread();
            	ipreceiver.terminate();
        	}
        	else notify();
        }
        else if (message.syn) {
        	System.out.println("Received: " + message.toString());
        	if (!message.ack) {
        		TSMessage syn_ack = new TSMessage(18, 0, 0, new byte[0], 0); // SYN+ACK
        		tx_channel.send(syn_ack);
        		notify();
            	System.out.println("Sent: " + syn_ack.toString());
        	}
        	else notify();
        }
        else if(message.psh) {
        	inputStream.write(message.data, 0, message.data.length);
        }
		
    }
    
    public synchronized void close() {
    	
        //***************************************
    	
        
        TSMessage fin = new TSMessage(1, 0, 0, new byte[0], 0);
        tx_channel.send(fin);
    	System.out.println("Sent: " + fin.toString());
        try{wait();} catch(InterruptedException e) {}
        System.out.println("Connection closed in side " + tx_channel.id);
        
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
