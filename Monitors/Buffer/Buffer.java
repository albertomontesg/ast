public class Buffer extends Monitor {

	private AbstractQueue q;
	private Condition notEmpty, notFull;

	public Buffer(AbstractQueue q) {
		this.q = q;
		notEmpty = newCondition();
		notFull = newCondition();
	}

	public void put(Object value) {
		lock();
		while (q.isFull())
			notFull.await();
		q.put(value);
		notEmpty.signal();
		unlock();
	}

	public Object get() {
		lock();
		while (q.isEmpty())
			notEmpty.await();
		Object result = q.get();
		notFull.signal();
		unlock();
		return result;
	}
}

