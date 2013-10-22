public class Queue extends AbstractQueue {

	private Object[] slots;
	private int first, last, num;

	public Queue(int s) {
		SIZE = s;
		slots = new Object[SIZE];
		first = last = num = 0;
	}

	public int size() {
		return num;
	}

	public boolean isFull() {
		return num == SIZE;
	}

	public boolean isEmpty() {
		return num == 0;
	}

	public void put(Object o) {
		slots[last] = o;
		last = (last + 1) % SIZE;
		num++;
	}

	public Object get() {
		Object result = slots[first];
		first = (first + 1) % SIZE;
		num--;
		return result;
	}
}

