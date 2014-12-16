import java.io.*;
import java.util.BitSet;
import java.util.Arrays;

public class Solver {

	Integer orderNum = 0;

	Integer count = 0;
	private Integer n; // devices
	private Integer l; // flows
	private String[] flows;
	private String[] devices;
	private String[] flowsOrigin;
	private int number = -1;
	static BitSet bs;   

	public void readFile() {
		try {
			File inFile = new File("A-small-practice.in");
			//File inFile = new File("A-large-practice.in");
			FileReader inReader = new FileReader(inFile);
			FileInputStream inStream = new FileInputStream(inFile);
			BufferedReader reader = new BufferedReader(inReader);

			String line = null;
			String out = "";
			Integer lineNumber = 0;
			Integer caseNumber = 0;
			String[] nAndl;
			Integer result;

			while((line = reader.readLine()) != null) {
				lineNumber++;
				if(lineNumber != 1) {

					caseNumber++;

					nAndl = line.split(" ");

					n = Integer.parseInt(nAndl[0]);
					l = Integer.parseInt(nAndl[1]);

					bs = new BitSet(l);

					flows = reader.readLine().split(" ");
					devices = reader.readLine().split(" ");

					flowsOrigin = flows.clone();

					result = solveCase();

					out = out + "Case #" + caseNumber + ": ";

					if(result == -1) {
						out = out + "NOT POSSIBLE" + "\n";
					} else {
						out = out + result + "\n";
					}


				}
			}

			reader.close();

			writeFile(out);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Integer solveCase() {

		number = -1;

		fill(0, l);
	    //permuation = Integer.toBinaryString(i);
	    //System.out.println(permuation);


		return number;
	} 

	private void flipSwitches(BitSet flipWord) {
		BitSet flow;


		for(int k=0; k < n; k++) { // Loop all flows and flip switch
			flow = fromString(flows[k], l); // Electric flow 
			for(int b=0; b < l; b++) {
				if(flipWord.get(b)) {
					flow.flip(b);
				}
			}
			flows[k] = toString(flow);
		}

		if(checkCompatibility()) {
			if(number == -1 || number > flipWord.cardinality()) {
				number = flipWord.cardinality();
			}
			
			System.out.println(toString(flipWord));
			System.out.println("True with cardinality - " + number);
		}

		flows = flowsOrigin.clone();
	}

	private Boolean checkCompatibility() {
		String[] temp1 = flows;
		String[] temp2 = devices;
		Arrays.sort(temp1);
		Arrays.sort(temp2);

		if(Arrays.equals(temp1, temp2)) {
			System.out.println("");
			for(String word1 : temp1) {
				System.out.print(word1 + " ");
			}
			System.out.println("");
			for(String word : temp2) {
				System.out.print(word + " ");
			}
			System.out.println("");
			System.out.println("true");
			return true;
		}
		//System.out.println("false");
		return false;
	}


	public void fill(int k, int n) {

		flipSwitches(bs);

		if (k == n) {
			//System.out.println(bs);
			return;
		}

		bs.set(k, false);
		fill(k+1, n);

		bs.set(k, true);
		fill(k+1, n);

	}

	private static BitSet fromString(String s, int length) {
	    BitSet t = new BitSet(length);
	    int lastBitIndex = length - 1;
	    int wordLastIndex = s.length()-1;

	    for (int i = lastBitIndex; i >= 0; i--) {
	    	if (i <= wordLastIndex && s.charAt(i) == '1'){
	            t.set(lastBitIndex - i);                            
	        }               
	    }

	    return t;
	}

    private String toString(BitSet bset) {
    	String string = "";
	    for (int i = l-1; i >= 0; i--) {
    		if(bset.get(i)) {
    			string += "1";
    		} else {
    			string += "0";
    		}
    	}
        return string;
    }


	public static void main(String[] args) {
		System.out.println("=============== Start Work ===============");
		Solver solver = new Solver();
		solver.readFile();
		System.out.println("=============== Finish Work ===============");
	}

	public void writeFile(String out) {

		try {
			File file = new File("a.out");
	        FileWriter fileWriter = new FileWriter(file);
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(out);
			bufferedWriter.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}