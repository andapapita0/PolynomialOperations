package mytests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import Model.Polynomial;

public class Tests {
	Polynomial p1 = new Polynomial();
   	Polynomial p2 = new Polynomial();
	
	@Before
	public void setUp() {
	   	int[] n1= {3,5,4};
		int[] n2= {1,3};
		p1.setPol(n1);
		p2.setPol(n2);
	}
	
	@Test
	public void testAdd() {
    	Polynomial res = new Polynomial();
    	res = p1.addPolynomials(p2);
    	assertEquals("+3x^3 +6x^2 +7x ", res.toString());
	}
	
	@Test
	public void testSubtraction() {
		Polynomial res = new Polynomial();
		res = p1.subtractPolynomials(p2);
		assertEquals("+3x^3 +4x^2 +x ", res.toString());
	}
	
	@Test
	public void testMultiplication() {
		Polynomial res = new Polynomial();
		res = p1.multiplyPolynomials(p2);
		assertEquals("+3x^5 +14x^4 +19x^3 +12x^2 ", res.toString());
	}
	
	@Ignore
	public void testDivision() {
		
	}
	
	@Test
	public void testDerivation() {
		p1.derivePolynomial();
		assertEquals("+9x^2 +10x +4 ", p1.toString());
	}
	
	@Test
	public void testIntegration() {
		p1.integratePolynomial();
		assertEquals("+1.0x^4 +2.5x^3 +4.0x^2 ", p1.toStringI());
	}

}
