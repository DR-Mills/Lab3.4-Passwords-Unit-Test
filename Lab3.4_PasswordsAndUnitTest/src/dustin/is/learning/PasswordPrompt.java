package dustin.is.learning;

import java.util.Scanner;

public class PasswordPrompt {

	static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {


	}

	public static String promptUserForInput(Scanner scnr) {
		System.out.print("Please enter a password: ");
		return scnr.nextLine();
	}
}
