import java.util.*;
import java.lang.Exception;
import java.io.*;

/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica3: Queues // Apartat 2
 * Ordenar paraules
 */
public class OrderWords {
	public static void main(String[] args) throws IOException {
		
		// Scanner which will read the pairs of number and word
		Scanner scanner = new Scanner(System.in);
		/* Queues which one save the number and the other one the word. Both will be moved
		 * (put and get) at the same time to mantain the pairs. */
		Queue<Integer> index = new Queue<Integer>();
		Queue<String> words = new Queue<String>();
		
		// The pairs of variables to be reading
		int num;
		String word;
		// Scann the numbers and words and put them to their respective queue
		while (scanner.hasNextInt()) {
			num = scanner.nextInt();
			word = scanner.next();
			
			index.put(num);
			words.put(word);
		}
		System.out.println("------------------");
		
		// Start to run the algorithm to sort the words
		int i = 1;
		int iter = -1; // Variable which helps not stay in the bucle if a input number is missing
		int count = index.count();
		
		/* While the hasn't printed all the words, the bucle will be looking at the queue if the
		 * first element is the next one to print at the screen. */
		while (i <= count && iter < count) {
			int j = index.get();
			// In case the first element of the queue is the next to be shown
			if (j == i) {
				System.out.println(i++ + " " + words.get());
				iter = 0;
			} // In case it isn't so the element extracted of the queue will be put it again at the end
			else {
				index.put(j);
				words.put(words.get());
				iter++;
			}
		}
		
		if (iter > 0) System.out.println("One number or more are misssing!");
		
		
	}
}