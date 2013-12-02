package previ_socket;

import java.io.IOException;
import channel.ChannelIP;

/**
 * @author Alberto Montes
 * @date 20-nov-2013
 * @subject AST
 * @exercise Practica7: Implementacio de Protocols // Sessio 1.2 Implementation
 *           of a reliable data transmission protocol
 */
public class Test_SimpleSocket_EchoServer extends Thread {

    Socket_simple socket;

    Test_SimpleSocket_EchoServer(ChannelIP tx_channel, ChannelIP rx_channel) {
        socket = new Socket_simple(tx_channel, rx_channel);
    }

    public void run() {

        socket.accept();
        System.out.println("SimpleEchoServer: Connection Established");

        try {
            byte[] buffer = new byte[256];
            String text;
            int len;
            while (true) {
                //Store up to 256 bytes on buffer
                len = socket.read(buffer, 0, 256);
                if (len == -1) {
                    System.out.println("SimpleEchoServer: read interrupted");
                    break; //servidor rep eof
                }

                text = new String(buffer, 0, len);
                System.out.println("SimpleEchoServer: received text = " + text + " Length =" + len);

                // Echo Reply
                socket.write(buffer, 0, len);
            }
            socket.close();
            System.out.println("SimpleEchoServer finished");

        } catch (IOException e) {
            System.out.println("Excepcio a SimpleEchoServer: " + e.toString());
        }
    }
}
