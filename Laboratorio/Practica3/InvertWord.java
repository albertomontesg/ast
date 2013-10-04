import java.util.*;
import java.lang.Exception;
import java.io.*;

public class InvertWord {
	public static void main(String[] args) throws IOException {
		Deque<Character> queue = new ArrayDeque<Character>();
		Deque<Character> stack = new ArrayDeque<Character>();
		String word = args[0];
		int size = word.length();
		
		// Save the word to the queue
		for (int i = 0; i < size; i++) {
			queue.push((Character) word.charAt(i));
		}
		
		// Push it to the stack
		for (int j = 0; j < size; j++) {
			stack.push(queue.remove());
		}
		
		// Pass back to the queue
		for (int j = 0; j < size; j++) {
			queue.push(stack.pop());
		}
		
		// Print the result
		System.out.print("Inverted String: ");
		for (int i = 0; i < size; i++) System.out.print(queue.remove());
		System.out.println();
	}
	
}


