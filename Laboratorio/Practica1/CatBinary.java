import java.io.InputStream;
import java.io.PrintStream;
import java.io.IOException;

/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica1: Apartat 1
 * cat fent servir read/write
 */
public class CatBinary {
    public static void main(String[] args) throws IOException {

        InputStream in = null;
        PrintStream out = null;

        try {
			// Initialize with the standard Input/OutputStream from computer: keyboard and console.
            in = System.in;
            out = new PrintStream(System.out);
            int c;
            
            // Read characters while does not find and End Of File and print them
            while ((c = in.read()) != -1) {
                out.print((char)c);
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