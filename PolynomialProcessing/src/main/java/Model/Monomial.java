package Model;
import java.lang.Cloneable;

/**
 * 
 * @author Anda
 * this class represents the type of terms that make up a polynomial, i.e. the monomial, 
 * which can be seen as a single-term polynomial
 *
 */
public class Monomial implements Cloneable {
	/**
	 * exponent is the exponent of the monomial 
	 * coef is its coefficient
	 * dcoef is the coefficient resulted after an operation that makes the coefficient 
	 * no longer integer
	 */
	private int exponent;
	private int coef;
	private double dcoef;
	
	/**
	 * constructor
	 * @param coeff
	 * @param exp
	 */
	public Monomial(int coeff, int exp) {
		this.coef = coeff;
		this.exponent = exp;
	}
	
	/**
	 * constructor when a double coefficient is needed
	 * @param d
	 * @param e
	 */
	public Monomial(double d, int e) {
		this.dcoef = d;
		this.exponent = e;
	}
	
	/**
	 * getter
	 * @return - the exponent of this monomial
	 */
	public int getExponent() {
		return this.exponent;
	}
	/**
	 * setter
	 * @param i - sets the integer value i as the exponent of the monomial
	 */
	public void setExponent(int i) {
		this.exponent = i; 
	}
	
	public int getCoefficient() {
		return this.coef;
	}
	
	public void setCoefficient(int i) {
		this.coef = i;
	}
	
	@Override
	public Monomial clone() {
		Monomial clone = null;
	    try {
	        clone = (Monomial) super.clone();
	    } catch (CloneNotSupportedException e) {
	        // this shouldn't happen, since we are Cloneable
	        throw new InternalError(e);
	    }
	    return clone;
	}
	
	/**
	 * method that multiplies two monomials
	 * @param n - the parameter which this monomial is multiblied with
	 * @return - the resulting monomial of the multiplication
	 */
	public Monomial multiplyMonomials(Monomial n) {
		int c = this.coef * n.coef;
		int e = this.exponent + n.exponent;
		Monomial res = new Monomial(c, e);
		return res;
	}
	
	/**
	 * method that divides two monomials
	 * @param m - the parameter by which this monomial is divided
	 * @return - the resulting monomial after division is performed
	 */
	public Monomial divideMonomials(Monomial m) {
		double d = ((double)this.coef / m.coef);
		int c = (int) d;
		int e= this.getExponent() - m.getExponent();
		Monomial res = new Monomial(c, e);
		return res;
	}
	
	/**
	 * method that calculates the derivative of the current class instance (monomial)
	 */
	public void derivative() {
		if(this.exponent != 0) {
			this.coef = this.coef * this.exponent;
			this.exponent = this.exponent - 1;
		}
		else
			System.out.println("exponent nul");
	}
	
	/**
	 * method that calculates the value of the current class instance (monomial) after integration
	 */
	public void integrate() {
		this.setDcoef((double)this.coef / this.exponent);
		this.exponent = this.exponent + 1;
	}

	public double getDcoef() {
		return dcoef;
	}

	public void setDcoef(double dcoef) {
		this.dcoef = dcoef;
	}
	/**
	 * method that transforms a Monomial turn into a string which holds its valid representation
	 * @return returns a String value which contains the string representation of the monomial
	 */
	public String toStringM() {
		String s = "";
		if (this.coef != 0)
			if (this.coef < 0) {
				if (this.exponent == 0) s = s + this.coef;
				else if (this.exponent == 1) s = s + this.coef + "x ";
				else s = s + this.coef + "x^" + this.exponent + " ";
			} else {
				if (this.exponent == 0) s = s + "+" + this.coef + " ";
				else if ((this.exponent == 1) && (this.coef == 1)) s = s + "+" + "x ";
				else if (this.exponent == 1) s = s + "+" + this.coef + "x ";
				else if (this.coef == 1) s = s + "+" + "x^" + this.exponent + " ";
				else s = s + "+" + this.coef + "x^" + this.exponent + " ";
			}
		return s;
	}
	/**
	 * method almost identical with the previous one; instad of placing the int coefficient in
	 * the string, it uses the double one, as needed in the cases of integration or division
	 * @return
	 */
	public String toStringMI() {
		String s = "";
		if (this.dcoef != 0)
			if (this.dcoef < 0) {
				if (this.exponent == 0) s = s + this.dcoef;
				else if (this.exponent == 1) s = s + this.dcoef + "x ";
				else s = s + this.dcoef + "x^" + this.exponent + " ";
			} else {
				if (this.exponent == 0) s = s + "+" +this.dcoef;
				else if (this.exponent == 1) s = s + "+" + this.dcoef + "x ";
				else s = s + "+" + this.dcoef + "x^" + this.exponent + " ";
			}
		return s;
	}
}
