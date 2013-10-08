import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.IOException;

/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica1: Apartat 5
 * cat fent servir Scanner associat a System.in
 */
public class CatScanner {
    public static void main(String[] args) throws IOException {
    	// Declare the Scanner
        Scanner scanner = null;
		
		try {
			// Initialize it with the InputStream System.in
			scanner = new Scanner(System.in);
			
			// Reads line by line until finds and EOF
			String line;
			while ((line = scanner.nextLine()) != null) {
				System.out.println(line);
			}
		} catch (NoSuchElementException e) { }
    }
}