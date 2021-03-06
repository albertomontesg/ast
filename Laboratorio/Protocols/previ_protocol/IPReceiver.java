package previ_protocol;

import channel.TSMessage;
import channel.ChannelIP;
import transport.Protocol;

class IPReceiver extends Thread {

    ChannelIP rx_channel;
    Protocol protocol;
    boolean alive;

    public IPReceiver(ChannelIP channel, Protocol protocol) {
        this.rx_channel = channel;
        this.protocol = protocol;
        alive = false;
    }

    public void run() {
        TSMessage rcv_msg;
        alive = true;
        while (alive) {
        	
            //***************************************
        	
        	rcv_msg = rx_channel.receive();
        	if (rcv_msg == null) break;
            protocol.processReceivedMessage(rcv_msg);
            
			//***************************************
			
        }
        System.out.println("IPReceiver Finished");
    }

    public void terminate() {
        alive = false;
        rx_channel.wakeUpToClose();
    }
}
