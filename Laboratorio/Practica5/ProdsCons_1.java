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
public class ProdsCons_1 {
	
	public static final int NUM_ELEMENTS = 5;
	public static final int NUM_THREADS = 3;
	
	public static void main(String[] args) {
		
		CircularQueue<Integer> circularQueue = new CircularQueue<Integer>(NUM_ELEMENTS);
		ArrayList<Thread> productors = new ArrayList<Thread>(NUM_THREADS);
		ArrayList<Thread> consumidors = new ArrayList<Thread>(NUM_THREADS);
		
		// Creation of all the threads
		for (int i = 0; i < NUM_THREADS; i++) {
			productors.add(new Thread(new ProductorQueue(i, circularQueue), "P["+i+"]"));
			consumidors.add(new Thread(new ConsumidorQueue(i+NUM_THREADS, circularQueue), "C["+i+"]"));
		}
		
		// Starting the executation of all the threads
		for (Thread th : productors) th.start();
		for (Thread th : consumidors) th.start();
		
	}
}

/**
 * Class implementing Runnable which has got the code to run the Productors
 */
class ProductorQueue implements Runnable {
	private int id;
	private CircularQueue<Integer> circularQueue;
	
	public ProductorQueue (int id, CircularQueue<Integer> circularQueue) {
		this.id = id;
		this.circularQueue = circularQueue;
	}
	
	// Code to run at the thread
	public void run() {
		for (int i = 0; i < ProdsCons_1.NUM_ELEMENTS; i++) {
			Integer num = new Integer(id*ProdsCons_1.NUM_ELEMENTS + i);
			try {
				circularQueue.put(num);
			}
			catch(FullQueueException e) {}
		}
	}
}

/**
 * Class implementing Runnable which has got the code to run the Consumidor
 */
class ConsumidorQueue implements Runnable {
	private int id;
	private CircularQueue<Integer> circularQueue;
	
	public ConsumidorQueue (int id, CircularQueue<Integer> circularQueue) {
		this.id = id;
		this.circularQueue = circularQueue;
	}
	
	// Code to run at the thread
	public void run() {
		for (int i = 0; i < ProdsCons_1.NUM_ELEMENTS; i++) {
			try {
				Integer num = circularQueue.get();
			}
			catch(EmptyQueueException e) {}
		}
	}
}