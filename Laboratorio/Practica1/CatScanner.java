import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.IOException;

public class CatScanner {
    public static void main(String[] args) throws IOException {

        Scanner scanner = null;
		
		try {
			scanner = new Scanner(System.in);
			String line;
			
			while ((line = scanner.nextLine()) != null) {
				System.out.println(line);
			}
		} catch (NoSuchElementException e) { }
    }
}