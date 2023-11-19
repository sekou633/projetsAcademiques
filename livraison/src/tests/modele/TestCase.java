package tests.modele;

import static org.junit.Assert.*;
import modele.Case;
public class TestCase {
    

    public void testGetCase() {
        Case testCase1 = new Case();
        Case testCase2 = new Case(10);
        Case testCase3 = new Case(Integer.MAX_VALUE);
        Case testCase4 = new Case(Integer.MAX_VALUE);

        assertEquals(testCase1.getValeur(), 0);
        assertEquals(testCase2.getValeur(), 10);
        assertEquals(testCase3.getValeur(), Integer.MAX_VALUE);
        assertNotSame(testCase4.getValeur(), Integer.MAX_VALUE);
    }
}
