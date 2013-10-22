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

/**
 * Class implementing Runnable which will print the character passed through the constructor
 */
public class MyRunnable implements Runnable {
	private char c;
	
	public MyRunnable (char c) {
		this.c = c;
	}
	
	// Code to run at the thread
	public void run() {
		for (int i = 0; i < 10000; i++) out.println(c);
	}
}