import java.io.*;
import java.net.*;

import static java.lang.System.out;

/**
 * @author Alberto Montes ©
 * @subject AST
 * @exercise Practica6: Echo // Apartat 2
 * Client d'eco multifil
 */
public class EchoClientMultithread {
	
	public static final int PORT = 50000;
	public static final String SERVER_ADDRESS = "ec2-54-246-226-16.eu-west-1.compute.amazonaws.com";
	public static final String SERVER_LOCAL = "localhost";
	
	public static void main(String[] args) {
		try {
			String adress = (args.length == 0) ? SERVER_LOCAL : SERVER_ADDRESS;
			
			Socket socket = new Socket(adress, PORT);
			
			ClientInput ci = new ClientInput(socket);
			ClientOutput co = new ClientOutput(socket);
			
			ci.start();
			co.start();
			
			ci.join();
			co.join();
			
			socket.close();
			
		} catch (Exception e) {e.printStackTrace();}
		
	}
}

class ClientInput extends Thread {
	private BufferedReader keyboard;
	private PrintWriter writer;
	private Socket socket;
	
	public ClientInput (Socket s) {
		try {
			keyboard = new BufferedReader(new InputStreamReader(System.in));
			writer = new PrintWriter(s.getOutputStream(), true);
			socket = s;
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public void run() {
		String line;
		try {
			while ((line = keyboard.readLine()) != null) writer.println(line);
			socket.shutdownInput();
			
			keyboard.close();
			writer.close();
		} catch (Exception e) {e.printStackTrace();}
	}
	
}

class ClientOutput extends Thread {
	private BufferedReader reader;
	
	public ClientOutput (Socket s) {
		try {reader = new BufferedReader(new InputStreamReader(s.getInputStream()));}
		catch (Exception e) {e.printStackTrace();}
	}
	
	public void run() {
		String line;
		try {
			while((line = reader.readLine()) != null) out.println(line);

			reader.close();
		} catch(Exception e) {e.printStackTrace();}
		
	}
	
}