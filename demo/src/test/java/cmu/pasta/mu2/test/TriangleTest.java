package cmu.pasta.mu2.test;


import cmu.pasta.mu2.Triangle;
import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {

	@Test
	public void testTriangle_classify_Equilateral(){
		int a = 12,b=12,c=12;
		Assert.assertEquals(Triangle.classify(a,b,c), 3);
	}

	@Test
	public void testTriangle_classify_Scalene(){
		int a=12,b=10,c=9;
		Assert.assertEquals(Triangle.classify(a,b,c), 1);
	}

	@Test
	public void testTriangle_classify_Isosceles(){
		int a = 12,b=17,c=12;
		Assert.assertEquals(Triangle.classify(a,b,c), 2);
	}

	@Test
	public void testTriangle_classify_Invalid(){
		int a = 2,b=7,c=2;
		Assert.assertEquals(Triangle.classify(a,b,c), 4);
	}
}
