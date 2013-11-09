class Car extends Thread {
    private Bridge bridge;
    private final int id;
    boolean way;
    
    public Car(Bridge b, int id, boolean way) {
    	bridge = b;
    	this.id = id;
    	this.way = way;
    }
    
    public void run() {
    	while (true) {
    		bridge.enter(way);
    		System.out.println("Car(" + id + ") driving " + (way ? "------>" : "<------"));
    		try {sleep((int)(Math.random() * 10));} catch(InterruptedException e) {}
    		bridge.leave();
    		try {sleep((int)(Math.random() * 10));} catch(InterruptedException e) {}
    	}
    	//System.out.println("Reader(" + id + ") end");
    }
}

class TestBridge {
	public static void main(String[] args) {
    	final int NC = 2;
      
    	/* init bridge */
    	Bridge bridge = new Bridge();
      
    	/* start NC Cars per lane */
    	for (int i = 0; i < NC; i++) {
    		new Car(bridge, i, true).start();
    		new Car(bridge, i, false).start();
        }
        
    	/* join them */
	}
}