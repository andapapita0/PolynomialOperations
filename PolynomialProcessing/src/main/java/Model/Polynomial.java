package Model;
import java.util.Iterator;
import java.util.List;
import java.lang.Cloneable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * This class represents the polynomial structure and contains the operations 
 * performed on such structures
 * @author anda
 *
 */

public class Polynomial implements Cloneable {
	/**
	 * monoms is an ArrayList structure; it contains all the monomials that form 
	 * the polynomial 
	 */
	private List<Monomial> monoms = new ArrayList<Monomial>();
	
	/**
	 * constructor
	 */
	public Polynomial() {
		setMonoms(new ArrayList<Monomial>());
	}
	
	/**
	 * constructor that adds a monomial in the polynomial's list of monomials
	 * @param m
	 */
	public Polynomial(Monomial m) {
		this.getMonoms().add(m);
	}
	
	/**
	 * simple method to generate a polynomials based on a given array of ints; used 
	 * for testing
	 * @param n
	 */
	public void setPol(int[] n) {
		Monomial m;
		int z = n.length;
		for(int i=0; i < n.length; i++) {
			m = new Monomial(n[i], z);
			this.getMonoms().add(m);
			z--;
		}
	}
	
	/**
	 * method that extracts a polynomial from a given string; it converts the format provided by 
	 * the user into a format the Polynomial class can work with
	 * @param text
	 */
	public void getPol(String text) {
		String terms[] = text.split("(\\+)");
		for(String term : terms) {
			String[] parts = term.split("x\\^");
			for(int k = 0; k < parts.length; k++) {
				//System.out.println(parts[k]);
				Monomial m = new Monomial(0, 0);
				int c = Integer.parseInt(parts[k]);
				int e = Integer.parseInt(parts[k + 1]);
				//System.out.println(e + " " + c);
				m.setExponent(e);
				m.setCoefficient(c);
				//System.out.println(m.getExponent()+"  "+ m.getCoefficient());
				k++;
				this.getMonoms().add(m);
			}	
		}
	}
	/**
	 * method that adds a polynomial to this class' instance; It iterates through the list of 
	 * the class' polynomial, calls another function to compare the exponents based on their indexes 
	 * and if they are not the same, it simply adds to the resulting polynomial the term of 
	 * this polynomial. If they are the same, the coefficients of the two polynomials are
	 *  added and the new monomial will be added in the new resulting polynomial. Also, the 
	 *  term will be removed from both polynomial after it is added, in order not to get 
	 *  added twice. At the end, the method iterates through the list of the second polynomial(p)
	 *  to add what is left in it.
	 * @param p
	 * @return a polynomial, the result of the addition
	 */
	public Polynomial addPolynomials(Polynomial p) {
		Polynomial sum = new Polynomial();
		for(Monomial m : this.getMonoms()) {
			if(p.compExp(m.getExponent()) == -1) {
				sum.getMonoms().add(new Monomial(m.getCoefficient(), m.getExponent()));
			}
			else {
				int c = p.getMonoms().get(p.compExp(m.getExponent())).getCoefficient();
				sum.getMonoms().add(new Monomial(m.getCoefficient() + c, m.getExponent()));
				p.getMonoms().remove(p.compExp(m.getExponent()));
			}
		}
		for(Monomial n : p.getMonoms()) {
			sum.getMonoms().add(new Monomial(n.getCoefficient(),n.getExponent()));
		}
		return sum;
	}
	/**
	 * method that subtracts from this class' polynomial the polynomial p; it works same as 
	 * the addition method
	 * @param p
	 * @return the polynomial resulted by subtracting
	 */
	public Polynomial subtractPolynomials(Polynomial p) {
		Polynomial res = new Polynomial();
		for(Monomial m : this.getMonoms()) {
			if(p.compExp(m.getExponent()) == -1) {
				res.getMonoms().add(new Monomial(m.getCoefficient(), m.getExponent()));
			}
			else {
				int c = p.getMonoms().get(p.compExp(m.getExponent())).getCoefficient();
				res.getMonoms().add(new Monomial(m.getCoefficient() - c, m.getExponent()));
				p.getMonoms().remove(p.compExp(m.getExponent()));
			}
		}
		for(Monomial n : p.getMonoms()) {
			res.getMonoms().add(new Monomial(n.getCoefficient()*(-1),n.getExponent()));
		}
		return res;
	}
	/**
	 * method that multiplies the instance of this class to another polynomial by iterating
	 * through both their lists and multiplying each monomial; it calls for a Monomial method
	 * that multiplies Monomials
	 * @see Monomial.multiplyMonomials
	 * @param p
	 * @return the new resulting polynomial after multiplication
	 */
	public Polynomial multiplyPolynomials(Polynomial p) {
		Polynomial res = new Polynomial();
		for(Monomial m : this.getMonoms()) {
			for(Monomial n : p.getMonoms()) {
				Monomial s = m.multiplyMonomials(n);
				res.getMonoms().add(s);
			}
		}
		res.simplify();
		return res;
	}
	/** does not work yet
	 * 
	 * @param p
	 * @return
	 */
	public ArrayList<Polynomial> dividePolynomials(Polynomial p) {
		Polynomial quotient = new Polynomial(new Monomial(0, 0));
		Polynomial rest = new Polynomial();
		ArrayList<Polynomial> qrPair = new ArrayList<Polynomial>();
		rest = this.deepCopy();
		int z = rest.monoms.get(0).getExponent();
		int y = rest.monoms.get(0).getCoefficient();
		int k = highestDegree(rest);
		
		if(highestDegree(rest) < highestDegree(p)) {
			System.out.println("A");
			quotient.monoms.add(new Monomial(0, 0));
		}
		else {
			if(k >= highestDegree(p)) {
				Monomial r = rest.maxMonomial();
				Monomial pm = p.maxMonomial();
				Monomial d = r.divideMonomials(pm);
				//System.out.println(highestDegree(p));
				
				Polynomial result = new Polynomial(d); 
				quotient.getMonoms().add(d);
				System.out.println(quotient.toString());
				//System.out.println(rest.highestDegree());
				
				Polynomial mul = new Polynomial();
				mul = p.multiplyPolynomials(result);
				System.out.println(mul.toString());
				
				//System.out.println(rest.highestDegree());
				Polynomial dis = new Polynomial();
				dis = rest.subtractPolynomials(mul);
				System.out.println(dis.toString());
				
				rest = dis.deepCopy();
				System.out.println(rest.toString());
				System.out.println(highestDegree(rest));
				k--;
				
			}
		}
		if(rest.getMonoms().isEmpty())
			rest.getMonoms().add(new Monomial(0, 0));
	
		qrPair.add(quotient);
		qrPair.add(rest);
		return qrPair;
	}
	/**
	 * method that returns a deep copy of the polynomial of the class, not just the reference
	 * @return - copy of a polynomial
	 */
	public Polynomial deepCopy() {
		Polynomial copy = new Polynomial();
		Iterator<Monomial> it = this.getMonoms().iterator();
		while(it.hasNext()) {
			copy.getMonoms().add((Monomial)it.next().clone());
		}
		return copy;
	}
	
	/**
	 * finds the maximum exponent in a list of monomials
	 * @return - the maximum exponent(degree)
	 */
	public int highestDegree(Polynomial p) {
		int maximum = -1;
		for(int i = 0; i < p.monoms.size(); i++) {
			if(maximum < p.monoms.get(i).getExponent())
				maximum = p.monoms.get(i).getExponent();
		}
		return maximum;
	}
	/**
	 * method that performs the differentiation of a polynomial by differentiating each monomial
	 * in the list, it calls a method from the Monomial class
	 */
	public void derivePolynomial() {
		for(Monomial m : this.getMonoms()) {
			m.derivative();
		}
	}
	/**
	 * method that performs the integration of a polynomial, works exactly like the 
	 * differentiation
	 * method
	 */
	public void integratePolynomial() {
		for(Monomial m : this.getMonoms()) {
			m.integrate();
		}
	}
	/**
	 * this method is used to reduce the terms of the polynomial when it is possible; 
	 * it searches for terms with the same exponent and adds/subtracts them; It also uses 
	 * a Collections method, sort, to sort the terms in reverse order by their exponent.
	 */
	public void simplify() {
		for(int i = 0; i < getMonoms().size(); i++) {
			for(int j = i + 1; j < getMonoms().size(); j++) {
				if(getMonoms().get(i).getExponent() == getMonoms().get(j).getExponent()) {
					getMonoms().get(i).setCoefficient(getMonoms().get(i).getCoefficient() + getMonoms().get(j).getCoefficient());
					getMonoms().remove(j);
				}
			}
		}
		Collections.sort(this.getMonoms(), Collections.reverseOrder(new Comparator<Monomial>(){
			public int compare(Monomial m1, Monomial m2) {
				// TODO Auto-generated method stub
				return Integer.compare(m1.getExponent(), m2.getExponent());
			}
		}));
	}
	/**
	 * method that compares the exponent of a polynomial with the given parameter e and if 
	 * they are equal it returns the index of the corresponding monomial
	 * @param e
	 * @return index of monomial
	 */
	public int compExp(int e) {
		for(Monomial m : this.getMonoms()) {
			if(m.getExponent() == e)
				return this.getMonoms().indexOf(m);
		}
		return -1;
	}
	/**
	 * method that finds the monomial with the maximum degree
	 * @return
	 */
	public Monomial maxMonomial() {
		int e = highestDegree(this);
		int mon = this.compExp(e);
		return this.getMonoms().get(mon);
	}
	
	public String toString() {
		String s = "";
		for(Monomial m : getMonoms()) {
			s = s + m.toStringM();
		}
		return "" + s;
	}
	
	public String toStringI() {
		String s = "";
		for(Monomial m : getMonoms()) {
			s = s + m.toStringMI();
		}
		return "" + s;
	}
	
	public List<Monomial> getMonoms() {
		return monoms;
	}

	public void setMonoms(List<Monomial> monoms) {
		this.monoms = monoms;
	}
	
	public static void main(String[] args) {
		int[] n1 = {3, 5, 4};
		int[] n2 = {1, 3};
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		p1.setPol(n1);
		p2.setPol(n2);
		System.out.println(p1.toString());
		System.out.println(p2.toString());
		
		ArrayList<Polynomial> lis = new ArrayList<Polynomial>();
		lis = p1.dividePolynomials(p2);
		Iterator<Polynomial> it = lis.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
}

