import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class Solver {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static Integer l;
	private static Integer d;
	private static String[] words;
	public static void main(String[] args) {
		try {
			Solver s = new Solver();
			br = new BufferedReader(new FileReader(new File("A-small-practice.in")));
	        bw = new BufferedWriter(new FileWriter(new File("a.out")));
			String[] firstline = br.readLine().split(" ");
			l = Integer.parseInt(firstline[0]);
			d = Integer.parseInt(firstline[1]);
			Integer n = Integer.parseInt(firstline[2]);
			words = getWords();
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
			ArrayList<ArrayList<String>> pattern = getPattern();
			Integer result = 0;
			String[] chars = new String[l];
			Boolean found = true;
			for(int k = 0; k < d; k++) {
				chars = words[k].split("");
				for(int i = 0; i < l; i++) {
					if(pattern.get(i).indexOf(chars[i]) == -1) {
						found = false;
					}
				}
				if(found) {
					result++;
				}	
				found = true;
			}

			bw.write(result + "\n");

		} catch (Exception ex) {}
	}

	private ArrayList<ArrayList<String>> getPattern() {
		String pattern = "";
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		char[] charArray = new char[pattern.length()];
		Boolean inParan = false;
		try {
			pattern = br.readLine();
			charArray = pattern.toCharArray();
			String tempChar = "";
			int length = charArray.length;
			int patIncr = 0;
			for(int i = 0; i < length; i++) {
				tempChar = Character.toString(charArray[i]);
				if(tempChar.equals("(")) {
					inParan = true;
					result.add(new ArrayList<String>());
					continue;
				} else if(tempChar.equals(")")) {
					inParan = false;
					patIncr++;
					continue;
				} else if(inParan) {
					result.get(patIncr).add(tempChar);
				} else {
					result.add(new ArrayList<String>());
					result.get(patIncr).add(tempChar);
					patIncr++;
				}
			}
		} catch(Exception ex) {}
		return result;
	}

	private static String[] getWords() {
		String[] words = new String[d];
		for(int i=0; i < d; i++) {
			try {
				words[i] = br.readLine();
			} catch(Exception ex) {}
		}
		return words;
	}
}