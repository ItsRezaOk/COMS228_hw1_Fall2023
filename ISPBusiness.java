package edu.iastate.cs228.hw1;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Reza Choudhury
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {
	
	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		int length = tOld.getLength();
		int width = tOld.getWidth();
		Town tNew = new Town(length,width);
			for (int row = 0; row < length; row++) {
				for (int col = 0; col < width; col++) {
					tNew.townMap[row][col] = tOld.townMap[row][col].next(tNew); 
				}
			}
		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		//casual 1$
		//streamer 0
		//reseller 0
		int profit = 0;
		for (int i = 0; i <= town.getLength() - 1; i++){
			for (int j = 0; j <= town.getWidth() - 1; j++){
				if(town.townMap[i][j].who().equals(State.CASUAL)) {
					profit++;
				}
			}
		}
		return profit;
	}
	

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) {
		Scanner s = new Scanner(System.in); 
        System.out.println("1 for file");
        System.out.println("2 for random");
        int input = s.nextInt(); 
        int length = 0;
        int width = 0;
        int seed = 0;
        Town town = null;
       
        
        
        if (input == 1) {
            Scanner s2 = new Scanner(System.in);
            System.out.println("Enter filename");
            String file = s2.nextLine();
            try {
                town = new Town(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                s2.close();
                return;
            }
            s2.close();

        }
        else if(input == 2) {
            Scanner townScnr = new Scanner(System.in);
            System.out.println("rows + cols + seed");
            length = townScnr.nextInt();
            width = townScnr.nextInt();
            seed = townScnr.nextInt();
            town = new Town(length, width);
            town.randomInit(seed);
            townScnr.close();
        }
        else {
            System.out.println("Error");
      
        }
        
       

        int profit = 0;
        for(int i = 1; i <= 12; i++) {
            //System.out.println("Iteration: " + i);
            System.out.print(town.toString());
            System.out.print("\n");

           // UPDATE PROFIT AND PRINT PROFIT
            profit = getProfit(town);
        System.out.println("Profit " + profit); 
       town =  updatePlain(town);
        }
   		int total = town.getLength() * town.getWidth();
  		double percent = (profit * 100) / total;
        System.out.println("Profit percent " + percent); 
	}
}
