package transport ;

import java.io.IOException;
import java.io.InputStream;
import util.ReadCircularByteBuffer;

public class RawInputStream extends InputStream {

  MySocket socket ;
  ReadCircularByteBuffer readBuffer;
   
  public RawInputStream( MySocket socket ){
    this.socket = socket ;
    readBuffer = new ReadCircularByteBuffer(100);
  }
  public int read() throws IOException {
    byte[] buffer = new byte[1];
    if(readBuffer.get(buffer,0,1)!=-1)
        return (int) buffer[0];
    else
        return -1;
  }
  public int read( byte[] buffer ) throws IOException {
    return readBuffer.get(buffer,0,buffer.length); 
  }
  public int read( byte[] buffer , int offset , int count ) throws IOException {
    return readBuffer.get(buffer,offset,count);
  }
  public void close() throws IOException {
    socket.close();
  }
  public int available() {
  	return readBuffer.available();
  }
  void write(byte[] buffer , int offset , int count){
      readBuffer.put(buffer, offset, count);
  }
  void wakeUpToClose(){
      readBuffer.wakeUpToClose();
  }
}

