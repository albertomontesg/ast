import java.io.*;
import java.net.*;

import static java.lang.System.out;

/**
 * @author Alberto Montes ©
 * @subject AST
 * @exercise Practica6: Echo // Apartat 1
 * Client/Servidor d'eco monofil
 */
public class EchoClient {
	
	public static final int PORT = 50000;
	public static final String SERVER_ADDRESS = "ec2-54-246-226-16.eu-west-1.compute.amazonaws.com";
	public static final String SERVER_LOCAL = "localhost";
	
	public static void main(String[] args) {
		try {
			String adress = (args.length == 0) ? SERVER_LOCAL : SERVER_ADDRESS;
			
			Socket socket = new Socket(adress, PORT);
			
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			
			String line;
			while ((line = keyboard.readLine()) != null) {
				writer.println(line);
				out.println(reader.readLine());
			}
			socket.shutdownInput();
			
			keyboard.close();
			reader.close();
			writer.close();
			socket.close();
			
		} catch (Exception e) {e.printStackTrace();}
		
	}
}