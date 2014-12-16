import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class Solver {
	private static BufferedReader br;
	private static BufferedWriter bw;
	public static void main(String[] args) {
		try {
			Solver s = new Solver();
			br = new BufferedReader(new FileReader(new File("A-small-practice.in")));
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
			Integer n = Integer.parseInt(br.readLine());
			String[] wires = new String[n];
			int a1;
			int b1;
			int a2;
			int b2;
			String[] temp = new String[2];
			Integer result = 0;

			for(int i = 0; i < n; i++) {
				wires[i] = br.readLine();
			}

			for(int k = 0; k < n; k++) {
				temp = wires[k].split(" ");
				a1 = Integer.parseInt(temp[0]);
				b1 = Integer.parseInt(temp[1]);
				for(int m = k+1; m < n; m++) {
					temp = wires[m].split(" ");
					a2 = Integer.parseInt(temp[0]);
					b2 = Integer.parseInt(temp[1]);
					if((a1 > a2 && b1 < b2) || (a1 < a2 && b1 > b2)) {
						result++;
					}
				}
			}

			bw.write(result + "\n");

		} catch (Exception ex) {}
	}

}