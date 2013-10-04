import java.util.*;

class FullStackException extends RuntimeException {
	
}

public class Queue<E> throws FullStackException EmptyStackException{
    private final int SIZE;
	private E[] slots;
	private int first, last, num;
	
	public Queue(int s) {
		SIZE = s;
		slots = (E[]) new Object[s];
	}
	
	public void put (E o) {
		if (size == num) throw new FullException();
		slots[last] = ch;
		last = (last + 1) % SIZE;
		num++;
	}
	
	public E get() {
		E el;
		if (0 == num) throw new EmptyException();
		el = slots[first];
		first = (first + 1) % SIZE;
		num--;
		return el;
	}
	
	public boolean isEmpty() {
		return num == 0;
	}
	
}