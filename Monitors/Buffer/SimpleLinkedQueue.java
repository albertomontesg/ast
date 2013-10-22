/*
 ** A very simple single-linked queue
 */

public class SimpleLinkedQueue<E> /* extends absqueue<E> */
{
	private E slot;
	private SimpleLinkedQueue<E> next;

	public SimpleLinkedQueue() {
		this(null, null);
	}

	private SimpleLinkedQueue(E slot) {
		this(slot, null);
	}

	private SimpleLinkedQueue(E slot, SimpleLinkedQueue<E> next) {
		this.slot = slot;
		this.next = next;
	}

	public int size() {
		if (next == null)
			return 0;
		else
			return 1 + next.size();
	}

	public boolean isFull() {
		return false;
	}

	public boolean isEmpty() {
		return next == null;
	}

	public void put(E o) {
		if (next == null)
			next = new SimpleLinkedQueue<E>(o);
		else
			next.put(o);
	}

	public E get() {
		E result = next.slot;
		next = next.next;
		return result;
	}

	public static void main(String[] args) {
		SimpleLinkedQueue<Integer> lq = new SimpleLinkedQueue<Integer>();
		lq.put(1);
		lq.put(2);
		System.out.println("size = " + lq.size());
		System.out.println("value = " + lq.get());
		System.out.println("value = " + lq.get());
		System.out.println("size = " + lq.size());
	}
}
