import java.io.*;

public class Solver {
	
	public static void main(String[] args) {
		Solver solver = new Solver();
		solver.start();
	}
	
	public void start() {
		System.out.println("start");
		try {
			File fileIn = new File("B-small-practice.in");
			//File fileIn = new File("B-large-practice.in");
			FileReader inReader = new FileReader(fileIn);
			BufferedReader readerIn = new BufferedReader(inReader);
			readerIn.readLine();
			String line = null;
			Double result = 0.0;
			String out = "";
			Integer testNumber = 0;
			
			while((line = readerIn.readLine()) != null) {
				result = playGame(line);
				testNumber++;
				out	= out + "Case #"+testNumber+": " + result + "\n";
			}
			

			readerIn.close();

			writeFile(out);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public Double playGame(String in) {
		Double result = null;
		
		
		String[] inArray = in.split(" ");
		double C = Double.parseDouble(inArray[0]);
		double F = Double.parseDouble(inArray[1]);
		double X = Double.parseDouble(inArray[2]);
		
		double timeToBuild = 0;
		double oldTimeToBuild = 0;
		double timeToFinish = 0;
		double newTimeToFinish = 0;
		double cookiePerSecond = 2.0;
		boolean stillBuild = true;
		
		timeToFinish = X/cookiePerSecond;
		newTimeToFinish = timeToFinish;
		
		while(stillBuild) {
			oldTimeToBuild = timeToBuild;
			timeToBuild = timeToBuild + C/cookiePerSecond;
			if(X/(cookiePerSecond + F) + timeToBuild > newTimeToFinish) {
				stillBuild = false;
				result = X/cookiePerSecond + oldTimeToBuild;
			} else {
				newTimeToFinish = X/(cookiePerSecond + F) + timeToBuild;
				cookiePerSecond = cookiePerSecond + F;
			}
		}
		
		return result;
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