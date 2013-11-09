import java.io.*;
import java.net.*;

import static java.lang.System.out;

/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica6: Echo // Apartat 1
 * Client/Servidor d'eco monofil
 */
public class EchoClient {
	
	// Declaration of some variables
	public static final int PORT = 50000;
	public static final String SERVER_ADDRESS = "ec2-54-246-226-16.eu-west-1.compute.amazonaws.com";
	public static final String SERVER_LOCAL = "localhost";
	
	public static void main(String[] args) {
		try {
			/* If we put any argument as input, the programm will connect with an external server hosted in amazon servers running 
			 * the EchoServerMultithread programm
			 */
			String adress = (args.length == 0) ? SERVER_LOCAL : SERVER_ADDRESS;
			
			// Initialize the socket
			Socket socket = new Socket(adress, PORT);
			
			// Initialize all the inputs and outputs streams
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			
			// While there isn't an EOF keep reading from the keyboard, sending to the server and reading the echo answer
			String line;
			while ((line = keyboard.readLine()) != null) {
				writer.println(line);
				out.println(reader.readLine());
			}
			socket.shutdownInput(); // Send an End of Stream to the server
			
			// Close all the streams and connections
			keyboard.close();
			reader.close();
			writer.close();
			socket.close();
			
		} catch (Exception e) {e.printStackTrace();}
		
	}
}