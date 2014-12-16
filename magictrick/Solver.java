import java.io.*;

public class Solver {

	Integer orderNum = 0;

	public static void main(String[] args) {
		System.out.println("=============== Start Work ===============");
		Solver solver = new Solver();
		solver.readFile();
	}

	public void readFile() {
		System.out.println("=============== Read File ===============");
		try {
			File inFile = new File("A-small-practice.in");
			FileReader inReader = new FileReader(inFile);
			FileInputStream inStream = new FileInputStream(inFile);

			BufferedReader reader = new BufferedReader(inReader);

			String line = null;
			String out = "";
			String firstLine = "";
			String secondLine = "";
			Integer caseNumber = 0;
			Integer lineNumber = 0;
			Integer firstAnswer = 0;
			Integer secondAnswer = 0;
			Integer firstCaseStart = 0;
			Integer secondCaseStart = 0;
			Boolean findFirstInt = false;
			Boolean findSecondInt = false;
			Integer checkResult = -1;

			System.out.println(inStream.read(1));

			while((line = reader.readLine()) != null) {
				lineNumber++;
				if(lineNumber != 1) {
					if(line.length() == 1) {
						if(!findFirstInt) { 
							caseNumber++;
							firstAnswer = Integer.parseInt(line);
							findFirstInt = true;
							firstCaseStart = lineNumber;	
						} else { // if first answer already defined look for second line
							secondAnswer = Integer.parseInt(line);
							findSecondInt = true;
							secondCaseStart = lineNumber;
						}
					}

					if(lineNumber == (firstCaseStart + firstAnswer) && findFirstInt) {
						firstLine = line;
						findFirstInt = true;
					}

					if(lineNumber == (secondCaseStart + secondAnswer) && findSecondInt) {
						secondLine = line;
						checkResult = compareLines(firstLine, secondLine);
						out = out + "Case #" + caseNumber + ": ";
						if(checkResult == -1) {
							out = out + "Volunteer cheated!" + "\n";
						} else if(checkResult == -2) {
							out = out + "Bad magician!" + "\n";
						} else {
							out = out + checkResult.toString() + "\n";
						}
						findFirstInt = false;
						findSecondInt = false;
					}

				}
			}

			reader.close();

			writeFile(out);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
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

	public String solveLine(String in) {
		orderNum++;
		String result = orderNum.toString() + "# - " + in;
		return result;
	}

	public Integer compareLines(String line1, String line2) {
		Integer value = -1;
		Boolean alreadyFound = false;
		String[] line1Array = line1.split(" ");
		String[] line2Array = line2.split(" ");
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(line1Array[i].equals(line2Array[j]) && !alreadyFound) {
					alreadyFound = true;
					value = Integer.parseInt(line1Array[i]);
				} else if(line1Array[i].equals(line2Array[j]) && alreadyFound) {
					value = -2;
				}
			}
		}
		return value;
	}
}