import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CatFile {
    public static void main(String[] args) throws IOException {

        FileReader in = null;
        FileWriter out = null;

        try {
			// Initialize with the standard Input/OutputStream from computer: keyboard and console.
            in = new FileReader("/dev/tty");
            out = new FileWriter("/dev/tty");
            int c;
			
			while ((c = in.read()) != -1) {
				out.write((char) c);
				out.flush();
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