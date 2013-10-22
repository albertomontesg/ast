/**
 * @author Alberto Montes ©
 * @subject AST
 * @exercise Practica4: Threads // Apartat 2
 * As i Bs intercalades amb sleep
 */
public class Threads_2 {
	public static void main(String[] args) {
		// Creation of two threads which each one will print a different character
		new Thread(new MyRunnableSleep('A')).start();
		new Thread(new MyRunnableSleep('B')).start();
	}
}