package cmu.pasta.mu2;

public class CountNumber {
	public static int countNumber ( int [] array , int a ) {
		int counter = 0;
		for ( int i =0; i < array . length ; i ++) {
			if ( array [ i ] == a ) {
				counter ++;
			}
		}
		return counter ;
	}
}
