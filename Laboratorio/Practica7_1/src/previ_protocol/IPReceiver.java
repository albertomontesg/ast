package previ_protocol;

/**
 * @author Alberto Montes
 * @date 20-nov-2013
 * @subject AST
 * @exercise Practica7: Implementacio de Protocols // Sessio 1.1
 * Implementation of a reliable data transmission protocol
 */
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

		/*
		 * The thread will be running getting packets from the receiver channel
		 * while it is alive
		 */
		while (alive) {

			// ***************************************

			/*
			 * Waits until a new packet is available at the channel,* then get
			 * it and send it to process it
			 */
			rcv_msg = rx_channel.receive();
			// If the packet received is null, it means the channel has been
			// closed
			if (rcv_msg == null)
				break;
			protocol.processReceivedMessage(rcv_msg);

			// ***************************************

		}
		System.out.println("IPReceiver Finished");
	}

	public void terminate() {
		alive = false;
		rx_channel.wakeUpToClose();
	}
}
