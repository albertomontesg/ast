package previ_socket;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import channel.ChannelIP;

class Test_SimpleSocket_EchoClient extends Thread {

    Socket_simple socket;
    BufferedReader kb_rd;

    public Test_SimpleSocket_EchoClient(ChannelIP tx_channel, ChannelIP rx_channel) {
        socket = new Socket_simple(tx_channel, rx_channel);
        kb_rd = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() {
        try {
            socket.connect();
            new Teclat().start();

            byte[] buffer = new byte[256];
            String text;

            while (true) {               
                int len = socket.read(buffer, 0, 256);
                if (len == -1) {
                    break;
                }
                text = new String(buffer, 0, len);
                System.out.println("SimpleEchoClient: received text Reply = " + text);
            }
            System.out.println("SimpleEchoClient: Reader Terminated");

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    class Teclat extends Thread {

        public void run() {
            try {
                while (true) {
                    String line = kb_rd.readLine();
                    if ( (line == null) || line.equals("close") ) {
                        break;
                    }
                    socket.write(line.getBytes(), 0, line.length());
                    System.out.println("Sent: " + line);
                }
                socket.close();
                System.out.println("SimpleEchoClient closed, keyboard Input closed");
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }
}
