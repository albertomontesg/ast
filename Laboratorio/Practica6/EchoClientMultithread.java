import java.io.*;
import java.net.*;

import static java.lang.System.out;

/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica6: Echo // Apartat 2
 * Client d'eco multifil
 */
public class EchoClientMultithread {
	
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
			
			// Initialize the two threads
			ClientInput ci = new ClientInput(socket);
			ClientOutput co = new ClientOutput(socket);
			
			// Starting the threads
			ci.start();
			co.start();
			
			// Wait until both threads have end and close the streams before close the connection
			ci.join();
			co.join();
			
			// Close the connection
			socket.close();
			
		} catch (Exception e) {e.printStackTrace();}
		
	}
}

/**
 * ClientInput extends Thread and his work is to read the keyboard input and send it to the server
 */
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
			// While there isn't an EOF keep reading from the keyboard and sending to the server
			while ((line = keyboard.readLine()) != null) writer.println(line);
			/* When input is finished send an end of stream, so the ClientOutput will know that the
			 *  stream has finished and so the connection */
			socket.shutdownInput();
			
			// Close all the streams
			keyboard.close();
			writer.close();
		} catch (Exception e) {e.printStackTrace();}
	}
	
}

/**
 * ClientOutput extends Thread and his work is to read the echo response from the server and print it
 * to the screen
 */
class ClientOutput extends Thread {
	private BufferedReader reader;
	
	public ClientOutput (Socket s) {
		try {reader = new BufferedReader(new InputStreamReader(s.getInputStream()));}
		catch (Exception e) {e.printStackTrace();}
	}
	
	public void run() {
		String line;
		try {
			// While it doesn't read and EOF keep reading the answer of the server and printing it
			while((line = reader.readLine()) != null) out.println(line);

			// Close the stream
			reader.close();
		} catch(Exception e) {e.printStackTrace();}
		
	}
	
}