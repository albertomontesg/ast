import static java.lang.System.out;

class Child implements Runnable {
	String msg;

	Child( String message ) {
		msg = message;
	}

	public void run() {
		out.println("child: " + msg);
	}
}

class firstRunnable extends Thread {
	
	public static void main(String[] args) throws InterruptedException {
		
		//Como objeto a Thread se le pasa un objeto que implementa runnable
		//Asi la clase child podria extender de otra clase
		//Mecanismo mas flexible
		Thread child = new Thread(new Child("I'm the child thread"));
		
		child.start();
		sleep(10);
		out.println("parent: I'm the parent thread");
		/*try {
        	child.join();
      	}
   		catch (InterruptedException e) {}*/
  }
}