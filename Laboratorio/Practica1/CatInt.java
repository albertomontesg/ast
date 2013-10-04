import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.IOException;

public class CatInt {
    public static void main(String[] args) throws IOException {
    	Input input = new Input();
        int data;
        try {
	        while(input.nextLine()){
	            while(input.scanner.hasNextInt()) System.out.println(input.nextInt());
	        }
        } catch (Exception e) {}
    	
    }
	
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
		
		// Reads the next integer
		int nextInt() {
			int data=0;
            if(scanner.hasNextInt()){
                data = scanner.nextInt();
            }
            return data;
		}
	}
}
