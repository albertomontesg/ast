import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.IOException;

/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica1: Apartat 6
 * Llegir sencers fent servir una classe auxiliar Input
 */
public class CatInt {
    public static void main(String[] args) throws IOException {
    	Input input = new Input();
        int data;
        
        // While there are lines whith integers, their will be printed
        try {
	        while(input.nextLine()){
	            while(input.scanner.hasNextInt()) System.out.println(input.nextInt());
	        }
        } catch (Exception e) {} // The EOF Exception is catch and the program ends
    	
    }
	
    // Intern class Input
	static class Input {
		// The line
		String line = new String();
		// The Scanner
		Scanner scanner;
		// The BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/* Get the next line and pass it through the scanner
		Returns true if there is a line, in the contrary false */
		boolean nextLine() {
			try {
                line = br.readLine();
                scanner = new Scanner(line);
                return true;
            } catch (IOException ex) {
                return false;
            }
		}
		
		// Reads the next integer if there is one
		int nextInt() {
			int data=0;
            if(scanner.hasNextInt()){
                data = scanner.nextInt();
            }
            return data;
		}
	}
}
