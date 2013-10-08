import java.util.*;
import java.lang.Exception;
import java.io.*;

/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica3: Queues // Apartat 1
 * Invertir paraula
 */
public class InvertWord {
	public static void main(String[] args) throws IOException {
		
		String word = args[0];
		int size = word.length();
		
		Queue<Character> queue = new Queue<Character>(size);
		Stack<Character> stack = new Stack<Character>(size);
		
		// Save the word to the queue
		for (int i = 0; i < size; i++) {
			queue.put((Character) word.charAt(i));
		}
		
		// Push it to the stack
		for (int j = 0; j < size; j++) {
			stack.push(queue.get());
		}
		
		// Pass back to the queue
		for (int j = 0; j < size; j++) {
			queue.put(stack.pop());
		}
		
		// Print the result
		System.out.print("Inverted String: ");
		for (int i = 0; i < size; i++) System.out.print(queue.get());
		System.out.println();
	}
	
}


