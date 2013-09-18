import java.io.InputStream;
import java.io.PrintStream;
import java.io.IOException;

public class CatBinary {
    public static void main(String[] args) throws IOException {

        InputStream in = null;
        PrintStream out = null;

        try {
			// Initialize with the standard Input/OutputStream from computer: keyboard and console.
            in = System.in;
            out = new PrintStream(System.out);
            int c;

            while ((c = in.read()) != -1) {
                out.print((char)c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}