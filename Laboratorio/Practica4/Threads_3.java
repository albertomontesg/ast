/**
 * @author Alberto Montes ©
 * @subject AST
 * @exercise Practica4: Threads // Apartat 3
 * As i Bs intercalades amb una variable compartida
 */
public class Threads_3 {
	public static void main(String[] args) {
		// Declaration of the shared variable Flag
		Flag flag = new Flag();
		new Thread(new MyRunnableShared('A', flag)).start();
		new Thread(new MyRunnableShared('B', flag)).start();
	}
}