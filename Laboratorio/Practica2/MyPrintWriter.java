import java.util.*;
import java.lang.Exception;
import java.io.*;

/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica2: Stacks // Apartat 2
 * Imprimir un sencer
 */
public class MyPrintWriter extends PrintWriter{
	
	// Create an item from our self-made class Stack
	private Stack<Character> stack;
	
	// The constructor initialize the super class with the OutputStream System.out and autoflush
	public MyPrintWriter() {
		super(System.out, true);
	}
	
	/**
	 * Prints an integer. The integer is translated into digits from less weigth to more weigth so it is
	 * necessery to invert them to print them with the print(char c) method.
	 */
	@Override
	public void print(int i) {
		stack = new Stack<Character>();
		// Get from right to left all the digits of the integer and push them into a Stack
		while( i != 0) {
			char c = (char) ((i % 10) + 48);
			stack.push(new Character(c));
			i = i / 10;
		}
		// Pop the digits and print them
		int size = stack.count();
		for (int j = 0; j < size; j++) super.print(stack.pop().charValue());
	}
	
	/**
	 * Prints an integer and then terminates the line. This method behaves as though it invokes print(int) and then println().
	 */
	@Override
	public void println(int i) {
		print(i);
		super.println();
	}
	
}