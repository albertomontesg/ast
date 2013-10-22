import java.io.*;
import java.net.*;

/**
 * @author Alberto Montes ©
 * @subject AST
 * @exercise Practica6: Echo // Apartat 3
 * Servidor d'eco multifil
 */
public class EchoServerMultithread {
	
	public static final int PORT = 50000;
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			
			while (true) {
				Socket socket = serverSocket.accept();
				
				Worker w = new Worker(socket);
				w.start();

			}
			
		} catch (Exception e) {e.printStackTrace();}
		
	}
}

class Worker extends Thread {
	private Socket socket;
	
	public Worker(Socket s) {
		this.socket = s;
	}
	
	public void run() {
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			String line;
			
			while ((line = reader.readLine()) != null) {
				writer.println(line);
			}
			socket.shutdownInput();
			
			reader.close();
			writer.close();
			socket.close();
		} catch (Exception e) {e.printStackTrace();}
		
	}
	
}