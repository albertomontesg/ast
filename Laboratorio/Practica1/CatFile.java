import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica1: Apartat 4
 * cat fent servir FileReader/FileWriter i el dispositiu /dev/tty
 */
public class CatFile {
    public static void main(String[] args) throws IOException {

        FileReader in = null;
        FileWriter out = null;
        // Declare the array of chars
        char[] ch = new char[1024];

        try {
			// Initialize with the standard Input/OutputStream from computer: keyboard and console.
        	// '/dev/tty' corresponds to the input/output file of the system
            in = new FileReader("/dev/tty");
            out = new FileWriter("/dev/tty");
			
            // Reads the input and store it in an array of chars
			while ((in.read(ch,0,1024)) != -1) {
				out.write(ch,0,1024);
				out.flush();
				// Return the array to initial values so previous longer reads will not be showing again
				ch = new char[1024];
			}
			
        } finally {
        	// Finally close the streams
			if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
		}
    }
}