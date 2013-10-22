/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica5: Productors/Consumidors
 * The CiruclarQueue class ...
 */
class CircularQueue<E> {
	// Atribute with the size of the Queue
    private final int size;
    //
	private int first, last, num;
	//
	private E[] elements;
	
	// Constructor. By default it creates a Queue of 32 elements
	public CircularQueue() {
		this(32);
	}
	
	@SuppressWarnings("unchecked")
	public CircularQueue(int s) {
		size = s > 0 ? s : 32;

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
		
		for (int i = 0; i < size; i++) {
			if (last < size)
				st += (i > last && i <= first) ? " -" : elements[i] + " ";
			else
				st += (i >= first && i < last) ? elements[i] + " " : "- ";
		}
		
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