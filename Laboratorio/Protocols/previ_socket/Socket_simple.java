package previ_socket;

import java.io.IOException;
import channel.ChannelIP;
import transport.Protocol;

public class Socket_simple {

    RawInputStream inputStream;
    IPReceiver IP_receiver;
    Protocol protocol;
    boolean serverSocket;

    public Socket_simple(ChannelIP tx_channel, ChannelIP rx_channel) {
        
    	inputStream = new RawInputStream(128);
    	protocol = new Protocol_stateless(tx_channel, inputStream);
    	IP_receiver = new IPReceiver(rx_channel, protocol);
		
    }

    public void connect() {
        
		protocol.connect();
		
    }

    public void accept() {
        serverSocket = true;
        
		protocol.accept();		
    }

    public void close() {
        
    	protocol.close();
		
    }

    public void write(byte[] buffer, int offset, int count) {
        byte[] tmp_array = new byte[count];
        for (int i = 0; i < count; i++) {
            tmp_array[i] = buffer[offset + i];
        }
        
        protocol.sendData(tmp_array, count);
		
    }

    public int read(byte[] buffer, int offset, int count) throws IOException {
        
		return inputStream.read(buffer, offset, count);
		
    }
}
