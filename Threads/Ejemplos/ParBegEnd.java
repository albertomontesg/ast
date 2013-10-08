import static java.lang.System.out;
import java.util.*;

class ParBegEnd extends Thread {

	  static void proc(int n) {
		  out.println("I'm the child thread(" + n + ")");
	  }

	  static void joinAll(ArrayList<Thread> children)
	  {
		  for (Thread child : children) {
			  try { child.join(); } catch(InterruptedException e) {}
	      }
		  children.clear();
	  }

	  public static void main(String[] args) throws InterruptedException {
		  ArrayList<Thread> children = new ArrayList<Thread>();
		  Thread child;
		   
		  //Codigo que se quiere emular la teoria de parbegin parend
		  /*parbegin
		        altpar
		          proc(1);
		        altpar
		          proc(2);
		        altpar
		          proc(3);
		  parend*/
		  child = new Thread() { /* ... children ... child */ public void run() {
			  proc(1);
		  }}; child.start(); children.add(child);
		  child = new Thread() { public void run() {
			  proc(2);
		  }}; child.start(); children.add(child);
		  child = new Thread() { public void run() {
			  proc(3);
		  }}; child.start(); children.add(child);
		  
		  // Equivalente al 'parend'
		  joinAll(children);
	  }
}
