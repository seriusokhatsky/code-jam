import java.io.*;

public class Solver {
	private static BufferedReader br;
	private static BufferedWriter bw;
	public static void main(String[] args) {
		try {
			Solver s = new Solver();
			br = new BufferedReader(new FileReader(new File("A-large-practice.in")));
	        bw = new BufferedWriter(new FileWriter(new File("a.out")));
			Integer n = Integer.parseInt(br.readLine());
			for(int i=1; i <= n; i++) {
				bw.write("Case #" + i + ": ");
				s.solve();
			}
			br.close();
			bw.close();

		} catch (Exception ex) {}
	}

	private void solve() {
		try {			
			Integer c = Integer.parseInt(br.readLine());
			Integer l = Integer.parseInt(br.readLine());
			Integer i1;
			Integer i2;
			String[] items = br.readLine().split(" ");
			for(int i=0; i < l; i++) {
				i1 = Integer.parseInt(items[i]);
				for(int k=i+1; k < l; k++) {
					i2 = Integer.parseInt(items[k]);
					if((i1 + i2) == c) {
						bw.write((i + 1) + " " + (k + 1) + "\n"); return;
					}
				}
			}

		} catch (Exception ex) {}
	}
}