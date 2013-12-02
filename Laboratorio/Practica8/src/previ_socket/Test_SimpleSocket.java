package previ_socket;

import channel.ChannelIP;

/**
 * @author Alberto Montes
 * @date 20-nov-2013
 * @subject AST
 * @exercise Practica7: Implementacio de Protocols // Sessio 1.2 Implementation
 *           of a reliable data transmission protocol
 */
public class Test_SimpleSocket {

    public static void main(String[] args) {

        ChannelIP tx_client = new ChannelIP(100);
        ChannelIP tx_server = new ChannelIP(100);

        Test_SimpleSocket_EchoServer server = new Test_SimpleSocket_EchoServer(tx_server, tx_client);
        Test_SimpleSocket_EchoClient client = new Test_SimpleSocket_EchoClient(tx_client, tx_server);

        server.start();
        client.start();

        try {
            client.join();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Stopping IP_Receivers");
        tx_client.wakeUpToClose();
        tx_server.wakeUpToClose();

        System.out.println("\nTest_SimpleSocket Program Terminated\n");
    }
}
