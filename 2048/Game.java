import java.io.*;

public class Game {
    
    Integer size = 0;
    
    String direction = "";
    
    Integer[][] currentGrid;
    
    Integer[][] newGgrid;
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
	public void start() {
		System.out.println("start");
		try {
			//File fileIn = new File("B-small-practice.in");
			File fileIn = new File("B-large-practice.in");
			FileReader inReader = new FileReader(fileIn);
			BufferedReader readerIn = new BufferedReader(inReader);
			readerIn.readLine();
			String line = null;
			String result = "";
			String out = "";
			Integer testNumber = 0;
            String firstLine = "";
            String[] commands; // Some comment
			
			while((line = readerIn.readLine()) != null && line.length() != 1) {
				testNumber++;
                firstLine = line;
                commands = firstLine.split(" ");
                size = Integer.parseInt(commands[0]);
                direction = commands[1];
                
                currentGrid = getGrid(readerIn);
                
                
                //System.out.println("Press: " + direction);
                
                playGrid();
                
                result = gridToString(currentGrid);
                
                out = out + "Case #" + testNumber + ":\n" + result + "\n";
			}
			
            
			readerIn.close();
            
			writeFile(out);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
    
    public void playGrid() { 
        Integer[][] grid = new Integer[size][size];
        Integer next = 0;
        Integer current = 0;
        
        switch (direction) {
            case "left":  
		        for(int j = 0; j < size; j++) {
		            for(int k = 0; k < size; k++) {
		                mergeTwoItems(j, k);
		            }
		        } 	
			break;
            case "right":  
		        for(int j = 0; j < size; j++) {
		            for(int k = size - 1; k > -1; k--) {
		                mergeTwoItems(j, k);
		            }
		        } 	
			break;
            case "up":  
	            for(int k = 0; k < size; k++) {
		            for(int j = 0; j < size; j++) {
		                mergeTwoItems(j, k);
		            }
		        } 	
			break;
            case "down":  
	            for(int k = 0; k < size; k++) {
		            for(int j = size - 1; j > -1; j--) {
		                mergeTwoItems(j, k);
		            }
		        } 	
			break;
        }
        
        shiftGrid();
        
    }
    
    public void shiftGrid() { 
        Integer[][] grid = new Integer[size][size];
        Integer[] next;
        Integer currentValue = 0;
        Integer nextValue = 0;
        
        for(int j = 0; j < size; j++) {
            for(int k = 0; k < size; k++) {
            	currentValue = currentGrid[j][k];
            	if(currentValue == 0) {
	            	next = takeNext(j, k);
	            	if(next[0] != -1) {
	            		currentGrid[j][k] = currentGrid[next[0]][next[1]];
	            		currentGrid[next[0]][next[1]] = 0;
	            	}
            	}
            }
        } 
        
        switch (direction) {
            case "left":  
		        for(int j = 0; j < size; j++) {
		            for(int k = 0; k < size; k++) {
		            	shiftElement(j,k);
		            }
		        } 	
			break;
            case "right":  
		        for(int j = 0; j < size; j++) {
		            for(int k = size - 1; k > -1; k--) {
		            	shiftElement(j,k);
		            }
		        } 	
	
			break;
            case "up":  
	            for(int k = 0; k < size; k++) {
		            for(int j = 0; j < size; j++) {
		                shiftElement(j, k);
		            }
		        } 	
			break;
            case "down":  
	            for(int k = 0; k < size; k++) {
		            for(int j = size - 1; j > -1; j--) {
		                shiftElement(j, k);
		            }
		        } 	
			break;
        }
        
    }
    
    public void shiftElement(int j, int k) {
    	Integer currentValue = currentGrid[j][k];
    	if(currentValue == 0) {
        	Integer[] next = takeNext(j, k);
        	if(next[0] != -1) {
        		currentGrid[j][k] = currentGrid[next[0]][next[1]];
        		currentGrid[next[0]][next[1]] = 0;
        	}
    	}
    }
    
    public void mergeTwoItems(int j, int k) {
        Integer[] next;
        Integer currentValue = 0;
        Integer nextValue = 0;
        
    	currentValue = currentGrid[j][k];
        next = takeNext(j, k); 
        
        if(next[0] != -1) {
	        nextValue = currentGrid[next[0]][next[1]];
	    	if(currentValue.equals(nextValue)  && nextValue != 0) {
		    	currentValue = currentValue*2;
		    	currentGrid[j][k] = currentValue;
		    	currentGrid[next[0]][next[1]] = 0;
	    	}
        }
        

    }
    
    public Integer[] takeNext(int j, int k) { 
    	Integer[] next = new Integer[2];
    	Integer nextRow = j;
    	Integer nextCol = k;
    	
        switch (direction) {
            case "left":  
		    	nextRow = j;
		    	nextCol = k+1;
			break;
            case "right":  
		    	nextRow = j;
		    	nextCol = k-1;	
			break;
            case "up":  
		    	nextRow = j+1;
		    	nextCol = k;	
			break;
            case "down":  
		    	nextRow = j-1;
		    	nextCol = k;
			break;
        }
        
    	if(nextCol > size - 1 || nextRow > size - 1 || nextCol < 0 || nextRow < 0) {
	    	next[0] = -1;
	    	next[1] = -1;
	    	return next;
    	}
    	
    	if(currentGrid[nextRow][nextCol] != 0) {
	    	next[0] = nextRow;
	    	next[1] = nextCol;
    	} else {
	    	next = takeNext(nextRow, nextCol);
    	}
    	
    	return next;
    }
    
    
    public Integer[][] getGrid(BufferedReader readerIn) {
        Integer[][] grid = new Integer[size][size];
        String rowStr = "";
        String[] row = null;
        

		try {
            for(int j = 0; j < size; j++) {
                rowStr = readerIn.readLine();
                row = rowStr.split(" ");
                for(int k = 0; k < size; k++) {
                    grid[j][k] = Integer.parseInt(row[k]);
                }
            }
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
        return grid;
    }
	
    
    public void printGrid() {
        for(int j = 0; j < size; j++) {
            for(int k = 0; k < size; k++) {
                System.out.print(currentGrid[j][k]);
                System.out.print(" ");
            }
	        System.out.println("");
	    }
        System.out.println("");
    }
    
    public String gridToString(Integer[][] values) {
		StringBuffer result = new StringBuffer();
		String separator = " ";
		
        for(int j = 0; j < size; j++) {
            for(int k = 0; k < size; k++) {
            	result.append(currentGrid[j][k]);
            	if(k != size - 1) {
	            	result.append(" ");
            	}
            }
        	if(j != size - 1) {
			    result.append("\n");
        	}
	    }
		
		return result.toString();
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