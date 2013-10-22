public class MutableInteger {
	private int value;
	
	public MutableInteger (int v) {
		this.value = v;
	}
	
	public int intValue() {
		return this.value;
	}
	
	public void setValue(int v) {
		this.value = v;
	}
}