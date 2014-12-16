import java.io.*;
import java.util.Arrays;

public class Solver {
	private static BufferedReader br;
	private static BufferedWriter bw;
	public static void main(String[] args) {
		try {
			Solver s = new Solver();
			br = new BufferedReader(new FileReader(new File("A-small-practice.in")));
	        bw = new BufferedWriter(new FileWriter(new File("a.out")));
			Integer t = Integer.parseInt(br.readLine());
			for(int i=1; i <= t; i++) {
				bw.write("Case #" + i + ": ");
				s.solve();
			}
			br.close();
			bw.close();

		} catch (Exception ex) {}
	}

	private void solve() {
		try {			
			Integer l = Integer.parseInt(br.readLine());
			Integer[] x = getIntegerArray(br.readLine());
			Integer[] y = getIntegerArray(br.readLine());
			Long scalar = 0l;

			Arrays.sort(x);
			Arrays.sort(y);

			for(int i = 0; i < l; i++) {
				scalar += (long)x[i]*(long)y[l-i-1];
			}

			bw.write(scalar + "\n");

		} catch (Exception ex) {}
	}

	private Integer[] getIntegerArray(String line) {
		String[] strings = line.split(" ");
		int length = strings.length;
		Integer[] integers = new Integer[length];
		for(int i = 0; i< length; i++) {
			integers[i] = new Integer(strings[i]);
		}
		return integers;
	}
}