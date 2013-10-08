import static java.lang.System.out;

// Clase dels threads
class Child extends Thread {
	String msg;

	// Habitualmente, por el constructor se pasan las variables del thread padre al thread hijo
	Child( String message ) {
		msg = message;
	}

	public void run() {
		/*try {
			sleep(10);
		} catch (InterruptudeException e) {}*/
		out.println("child: " + msg);
	}
}

class first extends Thread {
	
	public static void main(String[] args) throws InterruptedException {
		Thread child = new Child("I'm the child thread");
		child.start();
		sleep(10);
    	out.println("parent: I'm the parent thread");
    	try {
    		child.join();
    	} catch(InterruptedException e) {}
    	out.println("always after child println");
	}
}