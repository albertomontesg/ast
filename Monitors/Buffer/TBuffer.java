class Generator extends Thread {

	private Buffer buffer;
    
	public Generator(Buffer b) {
		buffer = b;
	}
    
	public void run() {
		char c = 'a';

		System.out.println("Generator begin");
		while (true) {
			buffer.put(c);
			System.out.println("Generator char = " + c);
			if (c == 'z')
				break;
			c++;
		}
		System.out.println("Generator end");
	}
}
  
class Collector extends Thread {

	private Buffer buffer;
    
	public Collector(Buffer b) {
		buffer = b;
	}
    
	public void run() {
		char c;
      
		System.out.println("Collector begin");
		while (true) {
			c = (Character) buffer.get();
			System.out.println("Collector char = " + c);
			/*if (c == 'z')
				break;*/
		}
		//System.out.println("Collector end");
	}
}

public class TBuffer {

	public static void main(String[] args) {
	
		final int SIZE = 10;
		Buffer buffer;

		/* init buffer */
		buffer = new BufferJava(new Queue(SIZE));

		/* start Generator and Collector and wait */
		Generator g = new Generator(buffer);
		g.start();
		Collector c = new Collector(buffer);
		c.setDaemon(true);
		c.start();
		try {
			g.join();
		} catch(InterruptedException e) {
        }
		/*try {
			c.join();
		} catch(InterruptedException e) {
        }*/
	}
}

