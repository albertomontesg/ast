import java.util.*;
import java.lang.Exception;
import java.io.*;

import static java.lang.System.out;

/**
 * @author Alberto Montes ©
 * @subject AST
 * @exercise Practica5: Productors/Consumidors // Apartat 1
 * Productors/Consumidors amb cua circular
 */
public class SimulationQueue {
	public static void main(String[] args) {
		CircularQueue<> circularQueue = new CircularQueue<>(5);
	}
}

/**
 * Class implementing Runnable ...
 */
class Productor implements Runnable {
	private int id;
	private CircularQueue<E> circularQueue;
	
	public MyRunnable (int id, CircularQueue<E> circularQueue) {
		this.id = id;
		this.circularQueue = circularQueue;
	}
	
	// Code to run at the thread
	public void run() {
		
	}
}

/**
 * Class implementing Runnable ...
 */
class Consumidor implements Runnable {
	private int id;
	private CircularQueue<E> circularQueue;
	
	public MyRunnable (int id, CircularQueue<E> circularQueue) {
		this.id = id;
		this.circularQueue = circularQueue;
	}
	
	// Code to run at the thread
	public void run() {
		
	}
}