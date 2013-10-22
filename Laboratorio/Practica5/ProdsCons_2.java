import java.util.*;
import java.lang.Exception;
import java.io.*;

import static java.lang.System.out;

/**
 * @author Alberto Montes ©
 * @subject AST
 * @exercise Practica5: Productors/Consumidors // Apartat 2
 * Productors/Consumidors amb Buffer
 */
public class ProdsCons_2 {
	
	public static final int NUM_ELEMENTS = 5;
	public static final int NUM_THREADS = 3;
	
	public static void main(String[] args) {
		
		// Declaring the buffer and the array of threads
		Buffer<Integer> buffer = new Buffer<Integer>(NUM_ELEMENTS);
		ArrayList<Thread> productors = new ArrayList<Thread>(NUM_THREADS);
		ArrayList<Thread> consumidors = new ArrayList<Thread>(NUM_THREADS);
		
		// Creation of all the threads
		for (int i = 0; i < NUM_THREADS; i++) {
			productors.add(new Thread(new ProductorBuffer(i, buffer), "P["+i+"]"));
			consumidors.add(new Thread(new ConsumidorBuffer(i+NUM_THREADS, buffer), "C["+i+"]"));
		}
		
		// Starting the executation of all the threads
		for (Thread th : productors) th.start();
		for (Thread th : consumidors) th.start();
		
	}
}

/**
 * Class implementing Runnable ...
 */
class ProductorBuffer implements Runnable {
	private int id;
	private Buffer<Integer> buffer;
	
	public ProductorBuffer (int id, Buffer<Integer> buffer) {
		this.id = id;
		this.buffer = buffer;
	}
	
	// Code to run at the thread
	public void run() {
		for (int i = 0; i < ProdsCons_2.NUM_ELEMENTS; i++) {
			Integer num = new Integer(id*ProdsCons_2.NUM_ELEMENTS + i);
			buffer.put(num);
		}
	}
}

/**
 * Class implementing Runnable ...
 */
class ConsumidorBuffer implements Runnable {
	private int id;
	private Buffer<Integer> buffer;
	
	public ConsumidorBuffer (int id, Buffer<Integer> buffer) {
		this.id = id;
		this.buffer = buffer;
	}
	
	// Code to run at the thread
	public void run() {
		for (int i = 0; i < ProdsCons_2.NUM_ELEMENTS; i++) {
			Integer num = buffer.get();
		}
	}
}