/**
 * @author Alberto Montes ©
 * @subject AST
 * @exercise Practica4: Threads // Apartat 1
 * Imprimir As i Bs
 */
public class Threads_1 {
	public static void main(String[] args) {
		// Creation of two threads which each one will print a different character
		new Thread(new MyRunnable('A')).start();
		new Thread(new MyRunnable('B')).start();
	}
}