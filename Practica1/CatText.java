import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class CatText {
    public static void main(String[] args) throws IOException {

        BufferedReader in = null;
        PrintWriter out = null;

        try {
			// Initialize with the standard Input/OutputStream from computer: keyboard and console.
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            String line;

            while ((line = in.readLine()) != null) {
				out.println(line);
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