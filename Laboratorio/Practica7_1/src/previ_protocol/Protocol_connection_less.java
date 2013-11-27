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

public class Protocol_connection_less implements Protocol {

	protected ChannelIP tx_channel;

	public Protocol_connection_less(ChannelIP tx_channel) {
		this.tx_channel = tx_channel;
	}

	public synchronized void connect() {
	}

	public synchronized void accept() {
	}

	public synchronized void close() {
	}

	public synchronized void sendData(byte[] data, int data_length) {

		// ***************************************

		// Create the packet with the PSH flag and the data to transmit
		TSMessage message = new TSMessage(8, 0, 0, data, data_length);
		// Print trace
		System.out.println("Send from side: " + tx_channel.id + "\tPacket: "
				+ message.toString());
		// Put the message to the channel
		tx_channel.send(message);

		// ***************************************
	}

	public synchronized void processReceivedMessage(TSMessage message) {

		// ***************************************

		// All the received packets will have data so we print the trace of the
		// packetc received
		System.out.println("Received from side: " + tx_channel.id
				+ "\tPacket: " + message.toString());

		// ***************************************
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
