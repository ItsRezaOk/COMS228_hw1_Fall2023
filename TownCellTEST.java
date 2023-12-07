package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class TownCellTEST {
    Town t;
	
	@Before
	public void initialize() throws FileNotFoundException
	{
		t = new Town("4x4.txt");
	}
	
	@Test
	public void testCensus() 
	{
		String str = t.townMap[1][1].next(t).who().toString();
		assertEquals("", State.EMPTY.toString(), str); 
	}
}