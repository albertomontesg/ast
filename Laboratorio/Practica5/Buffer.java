/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica5: Productors/Consumidors
 * The Buffer class is the requested by the statement as a native monitor of Java
 */
class Buffer<E> {
	
	// Encapsulate a CircularQueue
	private CircularQueue<E> circularQueue;
	
	// Constructors...
	public Buffer() {
		this.circularQueue = new CircularQueue<E>();
	}
	
	public Buffer(int size) {
		this.circularQueue = new CircularQueue<E>(size);
	}
	
	/* I make the put method synchronized to protect the current state of the monitor (Buffer)
	 * from possible threads to also call this method
	 */
	public synchronized void put(E element) {
		while (circularQueue.isFull()) {
			try {wait();} 
			catch(InterruptedException e) {}
		}
		circularQueue.put(element);
		
		// Notify the threads that have been blocked by the initial condition
		notifyAll();
	}
	
	/* I make the get method synchronized to protect the current state of the monitor (Buffer)
	 * from possible threads to also call this method
	 */
	public synchronized E get() {
		while (circularQueue.isEmpty()) {
			try {wait();} 
			catch(InterruptedException e) {}
		}
		E element = circularQueue.get();
		
		// Notify the threads that have been blocked by the initial condition
		notifyAll();
		return element;
	}
	
	// Returns a String of the current state of the Buffer
	@Override
	public synchronized String toString() {
		return circularQueue.toString();
	}
	
}