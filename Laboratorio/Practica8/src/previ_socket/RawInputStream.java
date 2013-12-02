package previ_socket;

import java.io.IOException;
import java.io.InputStream;
import util.ReadCircularByteBuffer;

/**
 * @author Alberto Montes
 * @date 20-nov-2013
 * @subject AST
 * @exercise Practica7: Implementacio de Protocols // Sessio 1.2 Implementation
 *           of a reliable data transmission protocol
 */
public class RawInputStream extends InputStream {

    ReadCircularByteBuffer readBuffer;

    public RawInputStream(int buffersize) {
        readBuffer = new ReadCircularByteBuffer(buffersize);
    }

    public int read() throws IOException {
        byte[] buffer = new byte[1];
        if (readBuffer.get(buffer, 0, 1) != -1) {
            return (int) buffer[0];
        } else {
            return -1;
        }
    }

    public int read(byte[] buffer) throws IOException {
        return readBuffer.get(buffer, 0, buffer.length);
    }

    public int read(byte[] buffer, int offset, int count) throws IOException {
        return readBuffer.get(buffer, offset, count);
    }

    public void close() throws IOException {
    }

    public int available() {
        return readBuffer.available();
    }

    void write(byte[] buffer, int offset, int count) {
        readBuffer.put(buffer, offset, count);
    }

    void wakeUpToClose() {
        readBuffer.wakeUpToClose();
    }
}
