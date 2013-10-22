/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica5: Productors/Consumidors
 * ...
 */
class Buffer<E> {
	
	private CircularQueue<E> circularQueue;
	
	public Buffer() {
		this.circularQueue = new CircularQueue<E>();
	}
	
	public Buffer(int size) {
		this.circularQueue = new CircularQueue<E>(size);
	}
	
	public synchronized void put(E element) {
		while (circularQueue.isFull()) {
			try {wait();} 
			catch(InterruptedException e) {}
		}
		circularQueue.put(element);
		
		// Print the trace of the executation
		System.out.println(Thread.currentThread().getName() + ": put " + element.toString() + "--> " + this.toString());
		
		notifyAll();
	}
	
	public synchronized E get() {
		while (circularQueue.isEmpty()) {
			try {wait();} 
			catch(InterruptedException e) {}
		}
		E element = circularQueue.get();
		
		// Print the trace of the executation
		System.out.println(Thread.currentThread().getName() + ": get " + element.toString() + "<-- " + this.toString());
		
		notifyAll();
		return element;
	}
	
	@Override
	public synchronized String toString() {
		return circularQueue.toString();
	}
	
}