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
        
		// a completar
		
    }

    public void connect() {
        
		// a completar
		
    }

    public void accept() {
        serverSocket = true;
        
		// a completar
		
    }

    public void close() {
        
		// a completar
		
    }

    public void write(byte[] buffer, int offset, int count) {
        byte[] tmp_array = new byte[count];
        for (int i = 0; i < count; i++) {
            tmp_array[i] = buffer[offset + i];
        }
        
		// a completar
		
    }

    public int read(byte[] buffer, int offset, int count) throws IOException {
        
		// a completar
		
    }
}
