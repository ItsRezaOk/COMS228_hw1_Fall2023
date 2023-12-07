package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmptyTEST {

    @Test
    void testA() {
        Town t = new Town(2,2);
        t.townMap[0][0] = new Empty(t,0,0);
        t.townMap[0][1] = new Empty(t,0,1);
        t.townMap[1][0] = new Casual(t,1,0);
        t.townMap[1][1] = new Casual(t,1,1);
        assertEquals(State.RESELLER, t.townMap[0][0].next(t).who());
    }


}