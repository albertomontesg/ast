package util;

public class Buffer {

	private CircularQueue q;
    private boolean close;

	public Buffer(int cp) {
        q = new CircularQueue(cp); 
    }
	public synchronized Object get(){
        while(q.empty()){
            try {wait(); if(close) return null;}
            catch (InterruptedException e) {e.printStackTrace();}
		}
        Object result = q.get();
        notifyAll();
        return result;
    }
	
	public synchronized void put(Object value){
        while(q.full()){
            try {wait(); if(close) return;}
            catch (InterruptedException e) {e.printStackTrace();}
        }
        q.put(value);
        notifyAll();
    }
	
    public synchronized void wakeUpToClose(){
        close = true;
        notifyAll();
    }
}
