package view;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * This class creates the interface the user works with regarding to performing polynomial 
 * operations. It extends the JFrame class. Its attributes are labels, text fields and 
 * buttons
 * @author anda
 *
 */
@SuppressWarnings("serial")
public class View extends JFrame {
	private JLabel l1 = new JLabel("Polynomial 1");
	private JLabel l2 = new JLabel("Polynomial 2");
	private JLabel l = new JLabel("Resulting Polynomial");
	private JTextField p1 = new JTextField(25);
	private JTextField p2 = new JTextField(25);
	private JTextField p = new JTextField(25);
	private JButton sum = new JButton("   Add Polynomials   ");
	private JButton sub = new JButton("Subtract Polynomials");
	private JButton m = new JButton("Multiply Polynomials");
	private JButton d = new JButton("Differentiate Polynomials");
	private JButton i = new JButton("  Integrate Polynomials  ");
	private JButton div = new JButton("   Divide Polynomials    ");
	private JButton clear = new JButton("Clear");
	
	public View() {
		JPanel main = new JPanel(new GridBagLayout());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 400);
		this.setTitle("Polynomial processing application");
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		main.add(l1, c);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		main.add(l2, c);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LAST_LINE_START;
		main.add(l,c);
		c.insets = new Insets(10, 0, 0, 0);
		c.weightx = 0.1;
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		main.add(p1, c);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		main.add(p2, c);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.PAGE_END;
		main.add(p, c);
		
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.PAGE_END;
		
		JPanel ops = new JPanel();
		JPanel ops2 = new JPanel();
		
		ops.setBorder(BorderFactory.createTitledBorder("Operations"));
		ops2.setBorder(BorderFactory.createTitledBorder("Operations"));
		
		BoxLayout lt = new BoxLayout(ops, BoxLayout.Y_AXIS);
		BoxLayout lt2 = new BoxLayout(ops2, BoxLayout.Y_AXIS);
		
		ops.setLayout(lt);
		ops2.setLayout(lt2);
		
		sum.setAlignmentX(Component.LEFT_ALIGNMENT);
		sub.setAlignmentX(Component.LEFT_ALIGNMENT);
		m.setAlignmentX(Component.LEFT_ALIGNMENT);
		ops.add(sum);
		ops.add(sub);
		ops.add(m);
		
		c.insets = new Insets(20, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.LAST_LINE_START;
		main.add(ops, c);
		
		d.setAlignmentX(Component.RIGHT_ALIGNMENT);
		i.setAlignmentX(Component.RIGHT_ALIGNMENT);
		div.setAlignmentX(Component.RIGHT_ALIGNMENT);
		ops2.add(d);
		ops2.add(i);
		ops2.add(div);
		c.insets = new Insets(20, 0, 0, 0);
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		main.add(ops2, c);
		
		c.insets = new Insets(20, 0, 0, 0);
		c.gridx = 2;
		c.gridy = 4;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		main.add(clear, c);
		this.add(main);
		
	}	
	/**
	 * method that turns the text fields empty
	 */
	public void reset() {
		p.setText(null);
		p1.setText(null);
		p2.setText(null);
	}
	/**
	 * these methods get the polynomials introduced by the user
	 * @return
	 */
	public String getUserInput1() {
		return p1.getText();
	}
	
	public String getUserInput2() {
		return p2.getText();
	}
	/**
	 * method that sets the result to the designated text field
	 * @param res
	 */
	public void setResult(String res) {
		p.setText(res);
	}
	/**
	 * method that helps to find errors
	 * @param errMessage
	 */
	public void showError(String errMessage) {
		JOptionPane.showMessageDialog(this, errMessage);
	}
	/**
	 * method that creates an event for the clear button
	 * @param cl
	 */
	public void addClearListener(ActionListener cl) {
		this.clear.addActionListener(cl);
	}
	/**
	 * method that creates an event for the addition button
	 * @param addition
	 */
	public void addAdditionListener(ActionListener addition) {
		this.sum.addActionListener(addition);
	}
	/**
	 * method that creates an event for the multiplication button
	 * @param mul
	 */
	public void addMultiplicationListener(ActionListener mul) {
		this.m.addActionListener(mul);
	}
	
	public void addSubtractionListener(ActionListener subtr) {
		this.sub.addActionListener(subtr);
	}
	
	public void addDerivativeListener(ActionListener der){
		this.d.addActionListener(der);
	}
	
	public void addIntegrationActionListener(ActionListener integr) {
		this.i.addActionListener(integr);
	}
	
	public void addDivisionActionListener(ActionListener divs) {
		this.div.addActionListener(divs);
	}
}
