import java.util.*;

class FullStackException extends RuntimeException {
	
}

public class Stack<E> throws FullStackException EmptyStackException{
    private final int SIZE;
	private E[] slots;
	private int top;
	
	public Stack(int s) {
		SIZE = s;
		slots = (E[]) new Object[s];
	}
	
	public void push (E ch) throws FullStackException{
		if (isFull()) throw new FullStackException();
		chars[top++] = ch;
	}
	
	public E pop() throws EmptyStackExcpetion{
		if (isEmpty()) throw new EmptyStackException();
		return chars[--top];
	}
	
	public boolean isEmpty() {
		return top == 0;
	}
	
	public boolean isFull() {
		return top = (SIZE - 1);
	}
}