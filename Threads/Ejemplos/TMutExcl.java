class Puerta extends Thread {
    private int id;
    
    public Puerta(int id) {
    	this.id = id;
    }
    
    public void run() {
    	final int N = 10;
    	int temp;
      
    	for ( int i = 0; i < N; i++) {
        	/* Enter CZ */
        	System.out.println("Puerta(" + id + ") about to enter CZ");

        	/* CZ */
        	temp = TMultExcl.cuenta;	/* Sentencia 1 */
			try {
              sleep(10);
            } catch(InterruptedException e) {
            }
			temp = temp + 1;			/* Sentencia 2 */
			try {
              sleep (10);
            } catch(InterruptedException e) {
            }
			TMultExcl.cuenta = temp;	/* Sentencia 3 */
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
	static int cuenta = 0;
	
    public static void main(String[] args) {
    	final int M = 2;
      
    	System.out.println("main: INITIAL VALUE: cuenta = " + cuenta);
		
		/* start M Puerta threads */
		Puerta[] p = new Puerta[M];
		for (int i = 0; i < M; i++) {
			p[i] = new Puerta(i);
			p[i].start();
		}
      
		/* join them */
		for (int i = 0; i < M; i++) {
			try {
              p[i].join();
            } catch(InterruptedException e) {
            }
        }
		System.out.println("main: FINAL VALUE: cuenta = " + cuenta);
	}
}

