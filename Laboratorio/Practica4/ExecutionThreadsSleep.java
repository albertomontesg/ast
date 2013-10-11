import java.util.*;
import java.lang.Exception;
import java.io.*;

import static java.lang.System.out;

/**
 * @author Alberto Montes ©
 * @subject AST
 * @exercise Practica4: Threads // Apartat 2
 * As i Bs intercalades amb sleep
 */
public class ExecutionThreadsSleep {
	public static void main(String[] args) {
		// Creation of two threads which each one will print a different character
		new Thread(new MyRunnableSleep('A')).start();
		new Thread(new MyRunnableSleep('B')).start();
	}
}

/**
 * Class implementing Runnable which will print the character passed through the constructor
 */
class MyRunnableSleep implements Runnable {
	private char c;
	
	public MyRunnableSleep (char c) {
		this.c = c;
	}
	
	// Code to run at the thread
	public void run() {
		try {
			for (int i = 0; i < 1000; i++) {
				out.println(c);
				/* Sleep method call at each thread trying to intercalate all the characters
				 * printed by different threads */
				Thread.sleep(10);
			}
		} catch(InterruptedException e) {}
	}
}