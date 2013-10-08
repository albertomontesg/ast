import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica1: Apartat 3
 * cat fent servir DataInputStream/DataOutputStream
 */
public class CatBinaryData {
    public static void main(String[] args) throws IOException {

        DataInputStream in = null;
        DataOutputStream out = null;

        try {
			// Initialize with the standard Input/OutputStream from computer: keyboard and console.
            in = new DataInputStream(System.in);
            out = new DataOutputStream(System.out);
            int c;

            // Reads the input data as a binary int and transforms it to a char while does not found the EOF
            while (true) {
				out.writeChar(in.readChar());
				out.flush(); // Completly necessary to flush the buffer, if not, the data will print when we close the stream (closing flush)
            }
        } catch(EOFException e) {
        	
			if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
		}
    }
}