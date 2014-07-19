public class Top10 implements Comparable<Object> {
	public String word;
	public double ratio;

	public Top10(String str, double res) {
		this.word = str;
		this.ratio = res;
	}

	@Override
	public int compareTo(Object o) {
		return (this.ratio < ((Top10) o).ratio) ? 1
				: (this.ratio == ((Top10) o).ratio) ? 0 : -1;
	}

}
