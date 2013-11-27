package util;

/**
 * @author Alberto Montes
 * @date 10-nov-2013
 * @subject AST
 * @exercise Practica7: Implementacio de Protocols // Sessio 1
 * Implementation of a reliable data transmission protocol
 */
class CircularQueue<E> {
	
	private final static int DEFAULT_SIZE = 32;
    private final int size;
	private int first, last, num;
	private E[] elements;
	
	// Constructor. By default it creates a Queue of 32 elements
	public CircularQueue() {
		this(DEFAULT_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public CircularQueue(int s) {
		size = s > 0 ? s : DEFAULT_SIZE;

		elements = (E[]) new Object[size]; // Create array of generic objects
	}
	
	// Put an element into the Queue
	public void put (E putValue) {
		if (size == num) throw new FullQueueException();
		elements[last] = putValue;
		last = (last + 1) % size;
		num++;
	}
	
	// Return the first element put into the Queue
	public E get() {
		E element;
		if (0 == num) throw new EmptyQueueException();
		element = elements[first];
		first = (first + 1) % size;
		num--;
		
		return element;
	}
	
	// Returns true if the Queue has no element stored
	public boolean isEmpty() {
		return num == 0;
	}
	
	// Returns true if the Queue is full of elements
	public boolean isFull() {
		return num == size;
	}
	
	// Count how many elements are at the Queue
	public int count() {
		return num;
	}
	
	// Return a String representing the Queue
	@Override
	public String toString() {
		String st = "[";
		int l = first;
		
		for(int i = 0; i < num; i++) {
			st += elements[l] + " ";
			l = (l + 1) % size;
		} for(int i = num ; i < size; i++) st+="- ";
		
		st += "]";
		return st;
	}
	
}

/**
 * Exception thrown when the Stack is empty
 */
@SuppressWarnings("serial")
class EmptyQueueException extends RuntimeException {
	public EmptyQueueException() {
		this("Queue is empty");
	}

	public EmptyQueueException(String exception) {
		super(exception);
	}
}

/**
 * Exception thrown when the Stack is full
 */
@SuppressWarnings("serial")
class FullQueueException extends RuntimeException {
	public FullQueueException() {
		this("Queue is full");
	}

	public FullQueueException(String exception) {
		super(exception);
	}
}