package previ_protocol;

import channel.ChannelIP;
import transport.Protocol;

/**
 * @author Alberto Montes
 * @date 20-nov-2013
 * @subject AST
 * @exercise Practica7: Implementacio de Protocols // Sessio 1.1 Implementation
 *           of a reliable data transmission protocol
 */
public class Test_protocol_server extends Thread {

	Protocol protocol;
	IPReceiver IP_receiver;

	public Test_protocol_server(ChannelIP tx_channel, ChannelIP rx_channel) {
		// protocol = new Protocol_connection_less(tx_channel);
		protocol = new Protocol_connection_oriented_stateless(tx_channel);
		IP_receiver = new IPReceiver(rx_channel, protocol);
		IP_receiver.start();
	}

	public void run() {
		System.out.println("\t\t\t\t\t\tTest_protocol_server running...");
		protocol.accept();
		System.out.println("\t\t\t\t\t\tclient connected to server...");
	}

	public void IPReceiver_stop() {
		IP_receiver.terminate();
	}
}
