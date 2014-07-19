import java.util.Random;

public class Posterior implements Comparable<Object> {
	public double posterior;
	public int id;

	public Posterior(int id) {
		this.posterior = 0;
		this.id = id;
	}

	@Override
	public int compareTo(Object o) {
		Random r = new Random();
		return (this.posterior < ((Posterior) o).posterior) ? 1
				: (this.posterior == ((Posterior) o).posterior) ? 0 : -1;

	}
}