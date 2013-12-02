package previ_socket;

import java.io.IOException;
import channel.ChannelIP;
import transport.Protocol;

/**
 * @author Alberto Montes
 * @date 20-nov-2013
 * @subject AST
 * @exercise Practica7: Implementacio de Protocols // Sessio 1.2 Implementation
 *           of a reliable data transmission protocol
 */
public class Socket_simple {

    RawInputStream inputStream;
    IPReceiver IP_receiver;
    Protocol protocol;
    boolean serverSocket;

    public Socket_simple(ChannelIP tx_channel, ChannelIP rx_channel) {
        
    	inputStream = new RawInputStream(256);
    	protocol = new Protocol_stateless(tx_channel, inputStream);
    	IP_receiver = new IPReceiver(rx_channel, protocol);
    	IP_receiver.start();
		
    }

    public void connect() {
        // Call the protocol to connect through the connect() method
		protocol.connect();
		
    }

    public void accept() {
        serverSocket = true;
        // Call the protocol to wait until a new connection is available
		protocol.accept();		
    }

    public void close() {
        // Close the connection calling the close() method of the protocol
    	protocol.close();
		
    }

    public void write(byte[] buffer, int offset, int count) {
        byte[] tmp_array = new byte[count];
        for (int i = 0; i < count; i++) {
            tmp_array[i] = buffer[offset + i];
        }
        
        // Order the protocol to send the data, equivalent to write to the socket
        protocol.sendData(tmp_array, count);
		
    }

    public int read(byte[] buffer, int offset, int count) throws IOException {
        return inputStream.read(buffer, offset, count);
    }
}
