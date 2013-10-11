import java.util.*;
import java.lang.Exception;
import java.io.*;

import static java.lang.System.out;

/**
 * @author Alberto Montes ©
 * @subject AST
 * @exercise Practica4: Threads // Apartat 3
 * As i Bs intercalades amb una variable compartida
 */
public class ExecutionThreadsShared {
	public static void main(String[] args) {
		// Declaration of the shared variable Flag
		Flag flag = new Flag();
		new Thread(new MyRunnableShared('A', flag)).start();
		new Thread(new MyRunnableShared('B', flag)).start();
	}
	
}

/**
 * A class implementing runnable with a shared variable and the process consists on
 * printing a letter on the output
 */
class MyRunnableShared implements Runnable {
	private char ch;
	private Flag flag;
	
	public MyRunnableShared (char c, Flag f) {
		this.ch = c;
		this.flag = f;
	}
	
	public void run() {
		while (true) {
			flag.waitChar(ch);
			out.println(ch);
			flag.nextChar();
		}
	}
}

/**
 * Class Flag that encapsulate the char to print and manage to change and be accessible
 * from any of the process
 */
class Flag {
	
	// Volatile: we are saying to the compiler not to make optimizations
	private volatile char letter;

	// Define 'A' as the first letter to print
	public Flag () {
		this.letter = 'A';
	}
	
	// Wait until the othe process changes the letter to the desired one
	public void waitChar(char ch) {
		while (letter != ch) {}
	}
	
	// When the letter is printed, it is changed to let the other process print it
	public void nextChar() {
		this.letter = (letter == 'A') ? 'B' : 'A';
	}
}