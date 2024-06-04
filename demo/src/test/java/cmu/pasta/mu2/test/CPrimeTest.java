package cmu.pasta.mu2.test;

import cmu.pasta.mu2.CPrime;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CPrimeTest {

    @Test
    public void testIsPrime() {

        boolean isPrime = CPrime.isPrime(3);

        assertTrue(isPrime);
    }
}
