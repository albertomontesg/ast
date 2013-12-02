package echo ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import transport.MySocket;
import transport.Transport;

/**
 * @author Alberto Montes
 * @date 2-dec-2013
 * @subject AST
 * @exercise Practica8: Implementacio de Protocols // Part 2
 * 			Reliable transmission over a totally reliable channel
 */
class EchoClient extends Thread {
    
	  MySocket socket;
	  BufferedReader sc_rd, kb_rd;
	  PrintWriter sc_wr;
	  
	  public EchoClient(Transport tr){
		  socket = new MySocket(tr);
	  }
	  
	  public void run(){
		  try {
			  socket.connect();
			  sc_rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			  sc_wr = new PrintWriter(socket.getOutputStream(),true);
			  kb_rd = new BufferedReader(new InputStreamReader(System.in));
			  
			  new Teclat().start();
			  
			  while(true) {
				  String eco = sc_rd.readLine();
				  if(eco == null) break;
				  System.out.println(eco);
			  }
		  } catch(Exception exc) {exc.printStackTrace();}
  }
	  
  class Teclat extends Thread{
	  public void run(){
    	  try {
    		  while(true) {
    			  String line = kb_rd.readLine();
    			  if(line == null || line.equals("fi")) break;	// usuari pica: Ctrl-Z o fi
    			  sc_wr.println(line);
    		  }
    		  System.out.println("client finished, closing socket...");
    		  socket.close();
    	  } catch(Exception exc) {exc.printStackTrace();}
     }
  }
}


