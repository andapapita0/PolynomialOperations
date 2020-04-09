package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Polynomial;
import view.View;
/**
 * This class acts on both Polynomial and View classes. It controls the data flow that goes into 
 * Polynomial and updates the View whenever data changes. More precisely, it coordinates each of 
 * the operation buttons to perform a specific task if the user chooses to press them. 
 * @author anda
 *
 */
public class Controller {
	/**
	 * contains model(polynomial) and view attributes
	 */
	private Polynomial model;
	private View view;
	
	/**
	 * constructor that implements a specific action (operations on polynomials)
	 * @param p
	 * @param v
	 */
	public Controller(Polynomial p, View v){
		model = p;
		view = v;
		v.addClearListener(new ClearListener());
		v.addAdditionListener(new AdditionListener());
		v.addMultiplicationListener(new MultiplicationListener());
		v.addSubtractionListener(new SubtractionListener());
		v.addDerivativeListener(new DerivativeListener());
		v.addIntegrationActionListener(new IntegrationListener());
		//v.addDivisionActionListener(new DivisionListener);
		v.setVisible(true);
	}
	
	/**
	 * class that describes what will happen when the addition button is pressed
	 * @author anda
	 *
	 */
	class AdditionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String userInput1 = "";
			String userInput2 = "";
			String res = "";
			Polynomial p1 = new Polynomial();
			Polynomial p2 = new Polynomial();
			try {
				userInput1 = view.getUserInput1();
				userInput2 = view.getUserInput2();
				p1.getPol(userInput1);
				p2.getPol(userInput2);
				model = p1.addPolynomials(p2);
				model.simplify();
				res = model.toString();
				view.setResult(res);
			} catch (NumberFormatException nfx) {
				view.showError("Bad input expression in: '" + userInput1 + " or " + userInput2 + "'");
			}
		}
	}
	/**
	 * class that describes what will happen when the clear button is pressed
	 * @author anda
	 *
	 */
	class ClearListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			view.reset();
		}
	}
	
	/**
	 * class that describes what will happen when the multiplication button is pressed
	 * @author anda
	 *
	 */
	class MultiplicationListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String userInput1 = "";
			String userInput2 = "";
			String res = "";
			Polynomial p1 = new Polynomial();
			Polynomial p2 = new Polynomial();
			try {
				userInput1 = view.getUserInput1();
				userInput2 = view.getUserInput2();
				p1.getPol(userInput1);
				p2.getPol(userInput2);
				model = p1.multiplyPolynomials(p2);
				model.simplify();
				res = model.toString();
				view.setResult(res);
			} catch (NumberFormatException nfx) {
				view.showError("Bad input expression in: '" + userInput1 + " or " + userInput2 + "'");
			}
		}
	}
	
	/**
	 * class that describes what will happen when the subtraction button is pressed
	 * @author anda
	 *
	 */
	class SubtractionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String userInput1 = "";
			String userInput2 = "";
			String res = "";
			Polynomial p1 = new Polynomial();
			Polynomial p2 = new Polynomial();
			try {
				userInput1 = view.getUserInput1();
				userInput2 = view.getUserInput2();
				p1.getPol(userInput1);
				p2.getPol(userInput2);
				model = p1.subtractPolynomials(p2);
				model.simplify();
				res = model.toString();
				view.setResult(res);
			} catch (NumberFormatException nfx) {
				view.showError("Bad input expression in: '" + userInput1 + " or " + userInput2 + "'");
			}
		}
	}
	
	/**
	 * class that describes what will happen when the derivative button is pressed
	 * @author anda
	 *
	 */
	class DerivativeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String userInput = "";
			String res = "";
			Polynomial p1 = new Polynomial();
			try {
				userInput = view.getUserInput1();
				p1.getPol(userInput);
				p1.derivePolynomial();
				p1.simplify();
				res = p1.toString();
				view.setResult(res);
			} catch (NumberFormatException nfx) {
				view.showError("Bad input expression in: '" + userInput);
			}
		}
	}
	
	/**
	 * class that describes what will happen when the integration button is pressed
	 * @author anda
	 *
	 */
	class IntegrationListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String userInput = "";
			String res = "";
			Polynomial p1 = new Polynomial();
			try {
				userInput = view.getUserInput1();
				p1.getPol(userInput);
				p1.integratePolynomial();
				p1.simplify();
				res = p1.toStringI();
				view.setResult(res);
			} catch (NumberFormatException nfx) {
				view.showError("Bad input expression in: '" + userInput);
			}
		}
	}
}
