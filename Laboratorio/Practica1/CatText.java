import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica1: Apartat 2
 * cat fent servir readLine/println
 */
public class CatText {
    public static void main(String[] args) throws IOException {

        BufferedReader in = null;
        PrintWriter out = null;

        try {
			// Initialize with the standard Input/OutputStream from computer: keyboard and console.
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            String line;

            // Read lines while does not find an EOF and print it
            while ((line = in.readLine()) != null) {
				out.println(line);
				out.flush(); // Is necessary to flush the buffer of the PrintWriter
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