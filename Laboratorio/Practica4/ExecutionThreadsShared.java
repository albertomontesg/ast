import java.util.*;
import java.lang.Exception;
import java.io.*;

/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica4: Threads // Apartat 3
 * As i Bs intercalades amb una variable compartida
 */
public class ExecutionThreadsShared {
	public static void main(String[] args) {
		new Thread(new MyRunnable('A')).start();
		new Thread(new MyRunnable('B')).start();
	}
	
}

class MyRunnable implements Runnable {
	private char ch;
	
	public MyRunnable (char c) {
		this.ch = c;
	}
	
	public void run() {
		while (true) {
			flag.waitChar(ch);
			System.out.println(ch);
			flag.nextChar();
		}
	}
}

class Flag {
	
	public void waitChar(char ch) {
		
	}
	
	public void nextChar() {
		
	}
}