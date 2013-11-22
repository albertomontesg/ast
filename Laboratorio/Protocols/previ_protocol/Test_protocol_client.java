package previ_protocol;

import channel.ChannelIP;
import transport.Protocol;
import java.io.*;

public class Test_protocol_client extends Thread {

    Protocol protocol;
    IPReceiver IP_receiver;

    public Test_protocol_client(ChannelIP tx_channel, ChannelIP rx_channel) {
        //protocol = new Protocol_connection_less(tx_channel);
        protocol = new Protocol_connection_oriented_stateless(tx_channel);
        IP_receiver = new IPReceiver(rx_channel, protocol);
        IP_receiver.start();
    }
    
    public void run() {

        BufferedReader teclat_rd = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Test_protocol_client running...");
        System.out.println("\nType any of the following commands: connect/send/close");

        try {
            
            while (true) {

                String line = teclat_rd.readLine();

                if (line.equals("connect")) {
                    System.out.println("");
                    protocol.connect();
                } 
                else if (line.equals("send")) {
                    System.out.print("DATA to Transmit: ");
                    String data_text = teclat_rd.readLine();
                    System.out.println("");
                    protocol.sendData(data_text.getBytes(), data_text.length());
                } 
                else if (line.equals("close")) {
                    System.out.println("");
                    protocol.close();
                    break;
                } 
                else {
                    System.out.println("\ncommand not defined\n");
                }
            }
        } catch (Exception exc) {
            System.out.println("Error teclat! " + exc.toString());
        }
    }
    
    public void IPReceiver_stop(){
        IP_receiver.terminate();
    }
}
