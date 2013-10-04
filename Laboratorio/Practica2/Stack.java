
public class Stack {
    private final int SIZE;
	private char[] chars;
	private int top;
	
	public Stack(int s) {
		SIZE = s;
		chars = new char[s];
	}
	
	public void push (char ch) {
		chars[top++] = ch;
	}
	
	public char pop() {
		return chars[--top];
	}
}