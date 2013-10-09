import java.util.*;
import java.lang.Exception;
import java.io.*;

/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica4: Threads // Apartat 2
 * As i Bs intercalades amb sleep
 */
public class ExecutionThreadsSleep {
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
		try {
			for (int i = 0; i < 1000; i++) {
				System.out.println(c);
				Thread.sleep(10);
			}
		} catch(InterruptedException e) {}
	}
}