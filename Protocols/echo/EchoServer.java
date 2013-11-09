package echo ;

import channel.ChannelIP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import transport.MySocket;
import transport.Transport;

public class EchoServer extends Thread {

  MySocket socket;

  EchoServer(Transport tr){
	socket = new MySocket(tr);
  }
  public void run() {
      
      socket.accept();
      BufferedReader sc_rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter sc_wr = new PrintWriter(socket.getOutputStream(),true);

      try {
        while(true) {
            String line = sc_rd.readLine();
            if(line == null) break;		//servidor rep eof
            sc_wr.println(line);
        }
        System.out.println("server finished.");
      }
      catch(IOException e) {
          System.out.println("Excepcio a EchoServer2: " + e.toString());
      }
  }
}

