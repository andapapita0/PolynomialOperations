package main;
//import javax.swing.*;

import Model.Polynomial;
import controller.Controller;
import view.View;

/**
 * the class that starts the application
 * @author anda
 *
 */
public class Main {
	/**
	 * creates a model, a view and a controller
	 * @param args
	 */
	public static void main(String[] args) {
		Polynomial model = new Polynomial();
		View v = new View();
		Controller ctr = new Controller(model, v);
	}
}
