package util;

public class CircularQueue {

	private Object[] espai;
	private int capacitat, num_elem, primer, post_ultim;

	public CircularQueue(int cp){ 
      capacitat = cp;
	  espai = new Object[capacitat];
	}
	public int size(){
      return num_elem; 
    }
	public boolean full(){
	  return num_elem==capacitat; 
    }
	public boolean empty(){
	  return num_elem==0; 
    }
	public void put(Object value){
	  espai[post_ultim]=value;
	  post_ultim++;
	  post_ultim = post_ultim % capacitat;
	  num_elem++;
	}
	public Object get(){
	  Object result = espai[primer];
	  primer++;
	  primer = primer % capacitat;
	  num_elem--;
	  return result;
	}
}
