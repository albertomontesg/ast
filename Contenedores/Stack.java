/**
 * @author Alberto Montes
 * @subject AST
 * The Stack class represents a last-in-first-out (LIFO) stack of objects.
 */
class Stack<E> {
	// Atribute with the size of the Stack
	private final int size;
	// Pointer to the position to pop or push elements
	private int top;
	// Array of generic elements
	private E[] elements;

	// Constructor. By default it creates a Stack of 32 elements
	public Stack() {
		this(32);
	}

	@SuppressWarnings("unchecked")
	public Stack(int s) {
		size = s > 0 ? s : 32;

		elements = (E[]) new Object[size]; // Create array of generic objects
	}

	// You push an element to the Stack
	public void push(E pushValue) {
		if (top == size - 1) // In case the Stack is full
			throw new FullStackException(String.format("Stack is full, cannot push %s", pushValue));

		elements[top++] = pushValue; // Place pushValue on Stack
	}

	// Return the element of the top of the Stack
	public E pop() {
		if (top == 0) // In case the stack is empty
			throw new EmptyStackException("Stack is empty, cannot pop");

		return elements[--top]; // Remove and return top element of Stack
	}
	
	// Count how many elements are at the Stack
	public int count() {
		return top;
	}
	
	public Iterator<E> iterator() {
		return new StackIterator<E>(this);
	}
}

/**
 * Exception thrown when the Stack is empty
 */
@SuppressWarnings("serial")
class EmptyStackException extends RuntimeException {
	public EmptyStackException() {
		this("Stack is empty");
	}

	public EmptyStackException(String exception) {
		super(exception);
	}
}

/**
 * Exception thrown when the Stack is full
 */
@SuppressWarnings("serial")
class FullStackException extends RuntimeException {
	public FullStackException() {
		this("Stack is full");
	}

	public FullStackException(String exception) {
		super(exception);
	}
}

class StackIterator<E> implements Iterator<E> {
	Stack<E> stack;
	int actual, num;
	
	public StackIterator(Stack<E> stack) {
		this.stack = stack;
		actual = 0;
		num = stack.top;
	}
	
	public E next() {
		if(!hasNext()) throw new NoSuchElementException();
		E r = stack.elements[actual++];
		num--;
		return r;
	}
	
	public boolean hasNext() {
		return num > 0;
	}
}