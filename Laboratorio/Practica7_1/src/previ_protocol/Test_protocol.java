package previ_protocol;

import channel.ChannelIP;

/**
 * @author Alberto Montes
 * @date 20-nov-2013
 * @subject AST
 * @exercise Practica7: Implementacio de Protocols // Sessio 1.1 Implementation
 *           of a reliable data transmission protocol
 */
public class Test_protocol {

	public static void main(String[] args) {

		ChannelIP tx_client = new ChannelIP(100, 1);
		ChannelIP tx_servidor = new ChannelIP(100, 2);

		Test_protocol_server server = new Test_protocol_server(tx_servidor,
				tx_client);
		Test_protocol_client client = new Test_protocol_client(tx_client,
				tx_servidor);

		server.start();
		client.start();

		try {
			server.join();
			client.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Stopping IP_Receivers");
		server.IPReceiver_stop();
		client.IPReceiver_stop();

		System.out.println("\nTest_protocol Program Terminated\n");
	}
}
