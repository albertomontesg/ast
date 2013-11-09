package transport;

import java.io.InputStream;
import java.io.OutputStream;

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
        //...
    }
    public void accept() {
        //...
    }
    public void close() {
        //...
    }

    public InputStream getInputStream() {
        return inputStream;
    }
    public OutputStream getOutputStream() {
        return outputStream;
    }
}
