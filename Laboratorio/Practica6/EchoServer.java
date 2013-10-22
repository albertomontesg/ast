import java.io.*;
import java.net.*;

/**
 * @author Alberto Montes ©
 * @subject AST
 * @exercise Practica6: Echo // Apartat 1
 * Client/Servidor d'eco monofil
 */
public class EchoServer {
	
	public static final int PORT = 50000;
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			
			while (true) {
				Socket socket = serverSocket.accept();
				
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
				
			}
			
		} catch (Exception e) {e.printStackTrace();}
		
	}
}