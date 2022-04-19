package onelogin.codingchallenge;

import java.util.*;

public class CodeChallenge {

	public static String fraction1;
	public static String op;
	public static String fraction2;
	public static int w1;
	public static int w2;
	public static int nominator1;
	public static int nominator2;
	public static int denominator1;
	public static int denominator2;

	public static void main(String[] args) {
		System.out.println("Welcome to the Fraction calculator!");
		Scanner console = new Scanner(System.in);
		System.out.print("Enter an expression (or \"quit\"): ");
		// get the first fraction, or quit
		fraction1 = console.next();
		// test fraction1 to see if the user types "quit"
		if (fraction1.equalsIgnoreCase("quit")) {
			System.out.println("Goodbye!");
		}
		while (!fraction1.equalsIgnoreCase("quit")) {
			op = console.next();
			fraction2 = console.next();
			processFractions(fraction1, op, fraction2);
			System.out.print("Enter an expression (or \"quit\"): ");
			fraction1 = console.next();
			if (fraction1.equalsIgnoreCase("quit")) {
				System.out.println("Goodbye!");
			}
		} // end while loop
			// while loop continues the calc until the user types "quit"
		console.close();
	}// end of main

	public static void processFractions(String f1, String op, String f2) {
		// get variables from fractions

		try {
			// testing fraction 1 to get int values
			if (f1.contains("_")) { // testing for mixed number
				w1 = Integer.parseInt(f1.substring(0, f1.indexOf("_")));
				nominator1 = Integer.parseInt(f1.substring(f1.indexOf("_") + 1, f1.indexOf("/")));
				denominator1 = Integer.parseInt(f1.substring(f1.indexOf("/") + 1));
				nominator1 = (w1 * denominator1) + nominator1; // making mixed number improper
			} else if (f1.contains("/")) { // testing for fraction
				nominator1 = Integer.parseInt(f1.substring(0, f1.indexOf("/")));
				denominator1 = Integer.parseInt(f1.substring(f1.indexOf("/") + 1));
			} else {// testing for whole number
				w1 = Integer.parseInt(f1.substring(0));
				nominator1 = w1;
				denominator1 = 1;
			} // end if, else if, else method

			// testing fraction 2 to get int values
			if (f2.contains("_")) { // mixed fraction
				w2 = Integer.parseInt(f2.substring(0, f2.indexOf("_")));
				nominator2 = Integer.parseInt(f2.substring(f2.indexOf("_") + 1, f2.indexOf("/")));
				denominator2 = Integer.parseInt(f2.substring(f2.indexOf("/") + 1));
				nominator2 = w2 * denominator2 + nominator2;
			} else if (f2.contains("/")) { // fraction
				nominator2 = Integer.parseInt(f2.substring(0, f2.indexOf("/")));
				denominator2 = Integer.parseInt(f2.substring(f2.indexOf("/") + 1));
			} else { // whole number
				w2 = Integer.parseInt(f2.substring(0));
				nominator2 = w2;
				denominator2 = 1;
			} // end if, else if, else method
		} catch (NumberFormatException e) {
			System.out.println("Expression is not in correct format");
			return;
		}

		dotheMath(nominator1, nominator2, denominator1, denominator2, op);

	}// end processFraction method

	// dotheMath detmerines the operator
	public static void dotheMath(int n1, int n2, int d1, int d2, String op) {
		if (op.equals("+")) {
			System.out.println(add(n1, n2, d1, d2));
		} else if (op.equals("-")) {
			n2 = -1 * n2;
			System.out.println(add(n1, n2, d1, d2));
		} else if (op.equals("*")) {
			System.out.println(multiply(n1, n2, d1, d2));
		} else {
			int x = n2;
			int y = d2;
			d2 = x;
			n2 = y;
			System.out.println(multiply(n1, n2, d1, d2));
		} // end the if, else if, else statement

	}// end dotheMath method

	public static String add(int n1, int n2, int d1, int d2) {
		int newnom = (n1 * d2) + (n2 * d1);
		int newdenom = d1 * d2;
		int divisor = checkDivisor(newnom, newdenom);
		newnom /= divisor;
		newdenom /= divisor;

		String right_answer = "";
		
		//If 0 is in mixed fraction answer, print only nominator and denominator
		right_answer = String.valueOf(java.lang.Math.abs(newnom / newdenom)) + "_" + newnom % newdenom + "/"
				+ newdenom;
		if (String.valueOf(java.lang.Math.abs(newnom / newdenom)).equals("0")) {
			right_answer = newnom + "/" + newdenom;
			
		} else {
			right_answer = String.valueOf(java.lang.Math.abs(newnom / newdenom)) + "_" + newnom % newdenom + "/"
					+ newdenom;
		}

		return right_answer;
	}// end add method

	public static String multiply(int nom1, int nom2, int denom1, int denom2) {
		int newnom = nom1 * nom2;
		int newdenom = denom1 * denom2;
		int divisor = checkDivisor(newnom, newdenom);
		newnom /= divisor;
		newdenom /= divisor;

		// short answer
		String answer = newnom + "/" + newdenom;
		
		String right_answer = "";

		//If 0 is in mixed fraction answer, print only nominator and denominator
		if (String.valueOf(java.lang.Math.abs(newnom / newdenom)).equals("0")) {
			right_answer = newnom + "/" + newdenom;
			
		} else {
			right_answer = String.valueOf(java.lang.Math.abs(newnom / newdenom)) + "_" + newnom % newdenom + "/"
					+ newdenom;
		}

		return right_answer;
	}// end multiply method

	public static int checkDivisor(int newn, int newd) { //
		int newn_abs = Math.abs(newn);
		int newd_abs = Math.abs(newd); //

		int min_num = Math.min(newn_abs, newd_abs);

		int divisor = 1;

		for (int i = 1; i <= min_num; i++) {
			if (newn % i == 0 && newd % i == 0) {

				divisor = i;
			} // end if
		} // end for

		// print divisor
		// System.out.println("In Reduce: " + divisor);

		return divisor;

	}// end reduce

}// end of class