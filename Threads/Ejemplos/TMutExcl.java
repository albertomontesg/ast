class Puerta extends Thread {
    private int id;
    private Integer cuenta;
    
    public Puerta(Integer cuenta, int id) {
    	this.cuenta = cuenta;
    	this.id = id;
    }
    
    public void run() {
    	final int N = 10;
    	int temp;
      
    	for ( int i = 0; i < N; i++) {
        	/* Enter CZ */
        	System.out.println("Puerta(" + id + ") about to enter CZ");

        	/* CZ */
        	temp = cuenta.intValue();	/* Sentencia 1 */
			try {
              sleep(10);
            } catch(InterruptedException e) {
            }
			temp = temp + 1;			/* Sentencia 2 */
			try {
              sleep (10);
            } catch(InterruptedException e) {
            }
			cuenta = new Integer(temp);	/* Sentencia 3 */
			try {
              sleep (10);
            } catch(InterruptedException e) {
            }
			System.out.println("(" + id + "): --------------------------> cuenta = " + temp);

			/* Exit CZ */
			System.out.println("Puerta(" + id + ") about to exit CZ");
        }
    }
}
  
public class TMutExcl {
    public static void main(String[] args) {
    	Integer cuenta = 0;
    	final int M = 2;
      
    	System.out.println("main: INITIAL VALUE: cuenta = " + cuenta.intValue());
		
		/* start M Puerta threads */
		Puerta[] p = new Puerta[M];
		for (int i = 0; i < M; i++) {
			p[i] = new Puerta(cuenta, i);
			p[i].start();
		}
      
		/* join them */
		for (int i = 0; i < M; i++) {
			try {
              p[i].join();
            } catch(InterruptedException e) {
            }
        }
		System.out.println("main: FINAL VALUE: cuenta = " + cuenta.intValue());
	}
}

