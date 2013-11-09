public class Bridge {
	boolean way;
	int n;
	
	synchronized void enter(boolean way) {
		while (n > 0 && this.way != way) {
			System.out.println("Car about to wait");
			try {wait();} catch(InterruptedException e) {}
		}
		n++;
		this.way = way;
	}
	
	synchronized void leave() {
		if(--n == 0) {
			System.out.println("Last car, signal opposite direction");
			notifyAll();
		}
	}
}