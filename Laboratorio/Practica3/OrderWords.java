import java.util.*;
import java.lang.Exception;
import java.io.*;

public class OrderWords {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		Deque<HashMap<Integer, String>> queue = new ArrayDeque<HashMap<Integer, String>>();
		int num;
		String word;
		// Scann the numbers and words
		while (scanner.hasNextInt()) {
			num = scanner.nextInt();
			word = scanner.next();
			queue.push(new HashMap<Integer, String>().put(num, word));
		}
		
	}
}