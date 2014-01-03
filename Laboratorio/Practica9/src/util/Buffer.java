package util;

/**
 * @author Alberto Montes
 * @date 2-dec-2013
 * @subject AST
 * @exercise Practica8: Implementacio de Protocols // Part 2
 * 			Reliable transmission over a totally reliable channel
 */
public class Buffer<E> {

	private CircularQueue<E> queue;
    private boolean close;

    // Contructor of a Buffer of E objects
    public Buffer() {
    	this.queue = new CircularQueue<E>();
    }
    
	public Buffer(int capacity) {
        this.queue = new CircularQueue<E>(capacity);
    }
	
	public synchronized E get(){
        while(queue.isEmpty()){
            try {wait(); if(close) return null;}
            catch (InterruptedException e) {e.printStackTrace();}
		}
        E result = queue.get();
        notifyAll();
        return result;
    }
	
	public synchronized void put(E value){
        while(queue.isFull()){
            try {wait(); if(close) return;}
            catch (InterruptedException e) {e.printStackTrace();}
        }
        queue.put(value);
        notifyAll();
    }
	
    public synchronized void wakeUpToClose(){
        close = true;
        notifyAll();
    }
}
