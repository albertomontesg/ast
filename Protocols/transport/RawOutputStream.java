
package transport ;

import java.io.IOException;
import java.io.OutputStream;
import util.WriteCircularByteBuffer;

public class RawOutputStream extends OutputStream {

  MySocket socket ;
  WriteCircularByteBuffer writeBuffer;

  public RawOutputStream( MySocket socket ){
    this.socket = socket ;
    writeBuffer = new WriteCircularByteBuffer(100);
  }
  public void write( int oneByte ) throws IOException {
    byte buffer[] = { (byte) oneByte };
    writeBuffer.put( buffer , 0 , 1 );
  }
  public void write( byte[] buffer ) throws IOException {
    writeBuffer.put( buffer , 0 , buffer.length );
  }
  public void write( byte[] buffer , int offset , int count ) throws IOException {
    writeBuffer.put( buffer , offset , count );
  }
  public void close() throws IOException {
    socket.close();
  }
  int read(byte[] buffer, int offset, int count){
      return writeBuffer.get(buffer, offset, count);
  }
  void wakeUpToClose(){
      writeBuffer.wakeUpToClose();
  }
  void forward(int count){
      writeBuffer.forward(count);
  }
}

