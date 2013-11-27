package previ_protocol;

import channel.TSMessage;
import channel.ChannelIP;
import transport.Protocol;

/**
 * @author Alberto Montes
 * @date 20-nov-2013
 * @subject AST
 * @exercise Practica7: Implementacio de Protocols // Sessio 1.1 Implementation
 *           of a reliable data transmission protocol
 */
public class Protocol_connection_oriented_stateless implements Protocol {

	protected ChannelIP tx_channel;

	public Protocol_connection_oriented_stateless(ChannelIP tx_channel) {
		this.tx_channel = tx_channel;
	}

	public synchronized void connect() {

		// ***************************************

		// Create the packet SYN and put it to the transmitter channel
		TSMessage syn = new TSMessage(2, 0, 0, new byte[0], 0);
		tx_channel.send(syn);
		// Print a trace of the packet sent
		System.out.println("Sent: " + syn.toString());
		// Block the thread until the SYN+ACK is received
		try {
			wait();
		} catch (InterruptedException e) {
		}
		System.out.println("Connected");

		// ***************************************
	}

	public synchronized void accept() {
		System.out.println("\t\t\t\t\t\tProtocol accepting connections");

		// ***************************************

		// Block the thread until the client send a SYN packet to the server
		try {
			wait();
		} catch (InterruptedException e) {
		}
		// The packet would have been received so the connection is established
		System.out.println("Connection accepted");

		// ***************************************
	}

	public synchronized void sendData(byte[] data, int data_length) {

		// ***************************************

		// Create the data packet with the PSH flag up
		TSMessage message = new TSMessage(8, 0, 0, data, data_length);
		// Print a trace
		System.out.println("Send from side: " + tx_channel.id + "\tPacket: "
				+ message.toString());
		// Put the packet into the transmitter channel
		tx_channel.send(message);

		// ***************************************
	}

	public synchronized void processReceivedMessage(TSMessage message) {

		// ***************************************

		// Case the received packet have the FIN flag up
		if (message.fin) {
			// Print the trace
			System.out.println("Received: " + message.toString());
			// Case the packet is a FIN packet
			if (!message.ack) {
				// Create the packet to respond FIN+ACK
				TSMessage fin_ack = new TSMessage(17, 0, 0, new byte[0], 0); // FIN+ACK
				// Put it to the transmitter channel
				tx_channel.send(fin_ack);
				// Print trace
				System.out.println("Sent: " + fin_ack.toString());

			}
			// Case the packet is a FIN+ACK packet notify the thread block with
			// the close() method
			else
				notify();
		}
		// Case the received packet have the FIN flag up
		else if (message.syn) {
			// Print the trace
			System.out.println("Received: " + message.toString());
			// Case the packet is a SYN packet
			if (!message.ack) {
				// Create the packet to respond SYN+ACK
				TSMessage syn_ack = new TSMessage(18, 0, 0, new byte[0], 0); // SYN+ACK
				// Put it to the transmitter channel
				tx_channel.send(syn_ack);
				// Notify the thread blocked with the accept() method to wake up
				notify();
				// Print trace
				System.out.println("Sent: " + syn_ack.toString());
			}
			// Case the packet is a SYN+ACK packet notify the thread block with
			// the connect() method
			else
				notify();
		}
		// Case the received packet have the PSH flag up
		else if (message.psh) {
			// Print trace of the data packet received
			System.out.println("Received from side: " + tx_channel.id
					+ "\tPacket: " + message.toString());
		}

		// ***************************************
	}

	public synchronized void close() {

		// ***************************************

		// Create the packet SYN and put it to the transmitter channel
		TSMessage fin = new TSMessage(1, 0, 0, new byte[0], 0);
		tx_channel.send(fin);
		// Print trace
		System.out.println("Sent: " + fin.toString());
		// Block the thread until the FIN+ACK is received
		try {
			wait();
		} catch (InterruptedException e) {
		}
		System.out.println("Connection closed in side " + tx_channel.id);

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
