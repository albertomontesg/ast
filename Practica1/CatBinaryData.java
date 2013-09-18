import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

public class CatBinaryData {
    public static void main(String[] args) throws IOException {

        DataInputStream in = null;
        DataOutputStream out = null;

        try {
			// Initialize with the standard Input/OutputStream from computer: keyboard and console.
            in = new DataInputStream(System.in);
            out = new DataOutputStream(System.out);
            int c;

            while ((c = in.readInt()) != -1) {
				out.writeChar((char) c);
				out.flush();
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