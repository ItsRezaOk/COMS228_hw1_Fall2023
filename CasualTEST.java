package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CasualTEST {

	    @Test
	    /**
	     * Creates a 2x2 town to test if casual will change to reseller in its next interation
	     */
	    void casualTest1() {
	        Town t = new Town(2,2);
	        t.townMap[0][0] = new Casual(t,0,0);
	        t.townMap[0][1] = new Streamer(t,0,1);
	        t.townMap[1][0] = new Casual(t,1,0);
	        t.townMap[1][1] = new Casual(t,1,1);
	        assertEquals(State.RESELLER, t.townMap[0][0].next(t).who());
	    }

	    @Test
	    void casualTest2() {
	        Town t = new Town(2,2);
	        t.townMap[0][0] = new Casual(t,0,0);
	        t.townMap[0][1] = new Casual(t,0,1);
	        t.townMap[1][0] = new Casual(t,1,0);
	        t.townMap[1][1] = new Casual(t,1,1);
	        assertEquals(State.RESELLER, t.townMap[0][0].next(t).who());
	        
	        Town t2 = new Town(2,2);
	        
	        for(int i = 0; i < t2.getLength();i++) {
	        	for(int j = 0; j < t2.getWidth(); j++) {
	        		t2.townMap[i][j]= new Casual(t2, i, j);
	        	}
	        }
	        t2.toString();
	        assertEquals(4, ISPBusiness.getProfit(t2));
	        
	    }

	}
