import java.util.*;
import java.lang.Exception;
import java.io.*;

import static java.lang.System.out;

/**
 * @author Alberto Montes ©
 * @subject AST
 * @exercise Practica4: Threads // Apartat 1
 * Imprimir As i Bs
 */
public class ExecutionThreads {
	public static void main(String[] args) {
		// Creation of two threads which each one will print a different character
		new Thread(new MyRunnable('A')).start();
		new Thread(new MyRunnable('B')).start();
	}
}

/**
 * Class implementing Runnable which will print the character passed through the constructor
 */
class MyRunnable implements Runnable {
	private char c;
	
	public MyRunnable (char c) {
		this.c = c;
	}
	
	// Code to run at the thread
	public void run() {
		for (int i = 0; i < 10000; i++) out.println(c);
	}
}