package mytests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Monomial;

public class TestsMon {
	Monomial m1 = new Monomial(-4, 2);
	Monomial m2 = new Monomial(5, 7);

	@Test
	public void testMultiplication() {
		Monomial res = new Monomial(0, 0);
		res = m1.multiplyMonomials(m2);
		assertEquals("-20x^9 ", res.toStringM());
	}
	
	@Test
	public void testIntegration() {
		m1.integrate();
		assertEquals("-2.0x^3 ", m1.toStringMI());
	}
	
	@Test
	public void testDivision() {
		Monomial res = new Monomial(0, 0);
		res = m1.divideMonomials(m2);
		assertEquals("-0.8x^-5 ", res.toStringMI());
	}
	
	@Test
	public void testDerivation() {
		m1.derivative();
		assertEquals("-8x ", m1.toStringM());
	}

}
