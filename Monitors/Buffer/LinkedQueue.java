/*
 ** A single-linked queue, first and last pointers
 */

public class LinkedQueue<E> /* extends AbstractQueue<E> */
{
	private Entry<E> first, last;

	private static class Entry<E> {
		E slot;
		Entry<E> next;

		Entry(E slot, Entry<E> next) {
			this.slot = slot;
			this.next = next;
		}
	}

	public LinkedQueue() {
		first = last = null;
	}

	public int size() {
		int r = 0;
		Entry<E> next = first;

		while (next != null) {
			r++;
			next = next.next;
		}

		return r;
	}

	public boolean isFull() {
		return false;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void put(E o) {
		if (first == null) {
			first = last = new Entry<E>(o, null);
		} else {
			last.next = new Entry<E>(o, null);
			last = last.next;
		}
	}

	public E get() {
		E result = first.slot;

		if (first == last)
			first = last = null;
		else
			first = first.next;
		return result;
	}

	public static void main(String[] args) {
		LinkedQueue<Integer> lq = new LinkedQueue<Integer>();
		lq.put(1);
		lq.put(2);
		System.out.println("size = " + lq.size());
		System.out.println("value = " + lq.get());
		System.out.println("value = " + lq.get());
		System.out.println("size = " + lq.size());
	}
}
