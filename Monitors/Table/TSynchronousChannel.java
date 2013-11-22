/**
 * @author Alberto Montes
 * @date 11-nov-2013
 * @subject AST
 * Problemas Programacion Concurrente
 * Monitores
 * Pas de missatges sincrone amb monitors
 * N emisors i N receptors que s'enviaran missatges punt a punt.
 * Si hi ha un receptor esperant a rebre el emisor 
 */

/** Point-To-Point sync monitor
 */
class Sync {
	
	Object data;
	boolean sender;
	
	synchronized void send(Object data) {
		sender = true;
		this.data = data;
		notify();
		try {wait();} catch(InterruptedException e) {}
	}
	
	synchronized Object receive() {
		if (!sender) 
			try {wait();} catch(InterruptedException e) {}
		Object data = this.data;
		sender = false;
		notify();
		return data;
	}
}

/** Multipoint sync monitor
 */
class SynchronizedChannel extends Sync {
	void send(Object data) {
		
	}
	
	Object receive() {
		
	}
}

class Generator extends Thread {

	private SynchronizedChannel channel;
    
	public Generator(SynchronizedChannel c) {
		channel = c;
	}
    
	public void run() {
		Character c = new Character('a');

		System.out.println("Generator begin");
		while (true) {
			channel.send(c);
			System.out.println("Generator char = " + c);
			if (c == 'z')
				break;
			c++;
		}
		System.out.println("Generator end");
	}
	
}

class Collector extends Thread {

	private SynchronizedChannel channel;
    
	public Collector(Synchronized c) {
		channel = c;
	}
    
	public void run() {
		char c;
      
		System.out.println("Collector begin");
		while (true) {
			c = (Character) buffer.receive();
			System.out.println("Collector char = " + c);
			/*if (c == 'z')
				break;*/
		}
		//System.out.println("Collector end");
	}
	
}

class TSynchronizedChannel {
	public static void main(String[] args) {
		SynchronousChannel ch;
		
		ch = new SynchronousChannel();
		
		Generator g = new Generator(ch);
		g.start();
		Collector c = new Collector(ch);
		c.start();
		try {g.join();} catch(InterruptedException e) {}
		try {c.join();} catch(InterruptedException e) {}
	}
}