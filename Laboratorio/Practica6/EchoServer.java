import java.io.*;
import java.net.*;

/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica6: Echo // Apartat 1
 * Client/Servidor d'eco monofil
 */
public class EchoServer {
	
	// Declaration of some variables
	public static final int PORT = 50000;
	
	public static void main(String[] args) {
		try {
			// Initialize the ServerSocket
			ServerSocket serverSocket = new ServerSocket(PORT);
			
			/* The programm will run infinitely accepting connections from client (not at the same time)
			 * and answering the echo */
			while (true) {
				// Get the socket of the input connection
				Socket socket = serverSocket.accept();
				
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
			}
			
		} catch (Exception e) {e.printStackTrace();}
	}
}