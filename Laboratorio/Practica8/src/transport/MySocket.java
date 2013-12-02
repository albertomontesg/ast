package transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Alberto Montes
 * @date 2-dec-2013
 * @subject AST
 * @exercise Practica8: Implementacio de Protocols // Part 2
 * 			Reliable transmission over a totally reliable channel
 */
public class MySocket {

	RawOutputStream outputStream;
    RawInputStream inputStream;
    Transport transport;

    public MySocket(Transport tr) {
        outputStream = new RawOutputStream(this);
        inputStream = new RawInputStream(this);
        transport = tr;
        transport.socket = this;
        transport.start_TX_RX_threads();
    }

    public void connect() {
    	// Call the protocol to connect through the connect() method
    	transport.protocol.connect();
    }
    
    public void accept() {
        // Call the protocol to wait until a new connection is available
		transport.protocol.accept();	
    }
    
    public void close() {
    	// Close the connection calling the close() method of the protocol
    	transport.protocol.close();
    }

    public InputStream getInputStream() {
        return inputStream;
    }
    
    public OutputStream getOutputStream() {
        return outputStream;
    }
}
