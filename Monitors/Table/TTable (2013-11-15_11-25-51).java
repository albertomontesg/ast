class Reader extends Thread {
	private Table table;
	private final int id;

	public Reader(Table t, int id) {
		table = t;
		this.id = id;
	}

	public void run() {
		while (true) {
			table.startRead();
			System.out.println("Reader(" + id + ") reading table");
			try {
				sleep((int)(10 * Math.random()));
			} catch (InterruptedException e) {
			}
			table.endRead();
			try {
				sleep((int)(10 * Math.random()));
			} catch (InterruptedException e) {
			}
		}
		// System.out.println("Reader(" + id + ") end");
	}
}

class Writer extends Thread {
	private Table table;
	private final int id;

	public Writer(Table t, int id) {
		table = t;
		this.id = id;
	}

	public void run() {
		while (true) {
			table.startWrite();
			System.out.println("Writer(" + id + ") writing table");
			try {
				sleep((int)(10 * Math.random()));
			} catch (InterruptedException e) {
			}
			table.endWrite();
			try {
				sleep((int)(10 * Math.random()));
			} catch (InterruptedException e) {
			}
		}
		// System.out.println("Writer(" + id + ") end");
	}
}

class TTable {
	public static void main(String[] args) {
		final int NR = 2, NW = 2;

		/* init table */
		Table table = new TableNative();

		/* start NR readers & NW writers */
		Reader[] r = new Reader[NR];
		for (int i = 0; i < NR; i++) {
			r[i] = new Reader(table, i);
			r[i].start();
			//new Reader(table, i).start();
		}
		Writer[] w = new Writer[NW];
		for (int i = 0; i < NW; i++) {
			w[i] = new Writer(table, i);
			w[i].start();
		}

		/* join them */
	}
}

