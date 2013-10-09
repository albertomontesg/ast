import java.util.*;
import java.lang.Exception;
import java.io.*;

/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica4: Threads // Apartat 1
 * Imprimir As i Bs
 */
public class ExecutionThreads {
	public static void main(String[] args) {
		new Thread(new MyRunnable('A')).start();
		new Thread(new MyRunnable('B')).start();
	}
	
}

class MyRunnable implements Runnable {
	private char c;
	
	public MyRunnable (char c) {
		this.c = c;
	}
	
	public void run() {
		for (int i = 0; i < 10000; i++) System.out.println(c);
	}
}