public abstract class AbstractQueue {
	int SIZE;

	public abstract int size();

	public abstract boolean isFull();

	public abstract boolean isEmpty();

	public abstract void put(Object o);

	public abstract Object get();
}
