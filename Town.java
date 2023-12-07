package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


/**
 *  @author <<Reza Choudhury>>
 *
 */
public class Town {
	
	private int length, width;  //Row and col (first and second indices)
	public TownCell[][] townMap;
	
	/**
	 * Constructor to be used when user wants to generate townMap randomly, with the given seed.
	 * This constructor does not populate each cell of the townMap (but should assign a 2D array to it).
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) {
		this.length = length;
		this.width = width;
		townMap = new TownCell[length][width];
		
	}
	
	/**
	 * Constructor to be used when user wants to populate townMap based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		File f = new File(inputFileName);
        Scanner in = new Scanner(f); 
        
        int length = in.nextInt();
        int width = in.nextInt();
        
        this.length = length;
        this.width = width;
  
        townMap = new TownCell[length][width];
        in.nextLine();
        
        for (int i = 0; i < length; i++) {
            String[] arr = in.nextLine().split(" ");
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == "R") {
                    townMap[i][j] = new Reseller(this,i,j);
                    break;
                }else if(arr[j] == "E") {
                    townMap[i][j] = new Empty(this,i,j);
                    break;
                }else if(arr[j] == "C") {
                    townMap[i][j] = new Casual(this,i,j);
                    break;
                }else if(arr[j] == "E") {
                    townMap[i][j] = new Outage(this,i,j);
                    break;
                }else if(arr[j] == "") {
                    townMap[i][j] = new Streamer(this,i,j);
                    break;
                }else if(arr[j] == "R\t") {
                    townMap[i][j] = new Reseller(this,i,j);
                    break;
                }else if(arr[j] == "E\t") {
                    townMap[i][j] = new Empty(this,i,j);
                    break;
                }else if(arr[j] == "C\t") {
                    townMap[i][j] = new Casual(this,i,j);
                    break;
                }else if(arr[j] == "O\t") {
                    townMap[i][j] = new Outage(this,i,j);
                    break;
                }else if(arr[j] == "S\t") {
                    townMap[i][j] = new Streamer(this,i,j);
                    break;
                
                }
            }
        }
        in.close();
	}
	
	/**
	 * Returns width of the townMap.
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns length of the townMap.
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Initialize the townMap by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) { 
		Random rand = new Random(seed);
		for (int i = 0; i <= length - 1; i++)
		{
			for (int j = 0; j <= width - 1; j++)
			{
				int temp = rand.nextInt(5);
				if (temp == TownCell.RESELLER) // 0
				{
					//System.out.println('R');
					townMap[i][j] = new Reseller(this, i, j);
				}
				else if (temp == TownCell.EMPTY) // 1
				{
					//System.out.println('E');

					townMap[i][j] = new Empty(this, i, j);
				}
				else if (temp == TownCell.CASUAL) // 2
				{
					//System.out.println('C');

					townMap[i][j] = new Casual(this, i, j);
				}
				else if (temp == TownCell.OUTAGE) // 3
				{
					//System.out.println('O');

					townMap[i][j] = new Outage(this, i, j);
				}
				else if (temp == TownCell.STREAMER) // 4
				{
					//System.out.println('S');

					townMap[i][j] = new Streamer(this, i, j);
				}
			}
		}
	}
	/**
	 * Output the town townMap. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between 
	 * the rows.
	 */
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i <= getLength() - 1; i++) 
		{
			s = s + "\n";
			for (int j = 0; j <= getWidth() - 1; j++) {
				if (townMap[i][j].who().equals(State.CASUAL)){
					s =s+"C"+" ";
				}else if (townMap[i][j].who().equals(State.RESELLER)){
					s = s +"R"+ " ";
				}else if (townMap[i][j].who().equals(State.STREAMER)){
					s = s +"S"+ " ";
				}else if (townMap[i][j].who().equals(State.OUTAGE)){
					s = s +"O"+ " ";
				}else if (townMap[i][j].who().equals(State.EMPTY)){
					s = s +"E"+ " ";
				}
			}
		}
		return s;
	}
}
