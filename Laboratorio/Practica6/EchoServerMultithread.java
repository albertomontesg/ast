import java.io.*;
import java.net.*;

/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica6: Echo // Apartat 3
 * Servidor d'eco multifil
 */
public class EchoServerMultithread {
	
	// Declaration of some variables
	public static final int PORT = 50000;
	
	public static void main(String[] args) {
		try {
			// Initialize the ServerSocket
			ServerSocket serverSocket = new ServerSocket(PORT);
			
			/* The programm will run infinitely accepting connections from client creting a new
			 * Thread for each input so for each client could be able to answer the echo */
			while (true) {
				// Get the socket of the input connection
				Socket socket = serverSocket.accept();
				
				// Create and start the Thread for this new connection/client
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
			// Initialize all the inputs and outputs streams
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			String line;
			
			// While there isn't an EOF keep reading from the client and sending the echo answer
			while ((line = reader.readLine()) != null) {
				writer.println(line);
			}
			// Send an End of Stream so the client would receive and EOF
			socket.shutdownInput();
			
			// Close all the streams and connections
			reader.close();
			writer.close();
			socket.close();
		} catch (Exception e) {e.printStackTrace();}
		
	}
}