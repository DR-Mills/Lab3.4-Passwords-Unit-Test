package dustin.is.learning;

import java.util.Scanner;

public class PasswordPrompt {

	static Scanner scnr = new Scanner(System.in);
	static boolean databaseActive = true;

	public static void main(String[] args) {
		while (databaseActive) {
			String password = promptUserForInput(scnr);
			boolean passwordMeetsReqs = PasswordValidation.doesPasswordMeetPolicyReqs(password);
			String hashedPassword = (passwordMeetsReqs) ? PasswordValidation.hashesPassword(password) : "error";
			if (passwordMeetsReqs) {
				boolean successful = PasswordStorage.wasHashStoredSuccessfully(hashedPassword);
				String userContinues = promptUserToContinue(scnr);
			}
		}
	}

	public static String promptUserForInput(Scanner scnr) {
		System.out.print("Please enter a password: ");
		return scnr.nextLine();
	}

	public static String promptUserToContinue(Scanner scnr) {
		System.out.print("Would you like to store more passwords? (y/n)");
		String userInput = scnr.nextLine().toLowerCase();
		if (userInput.contains("y")) {
			databaseActive = true;
			return userInput;
		} else if (userInput.contains("n")) {
			databaseActive = false;
			return userInput;
		} else {
			System.out.println("Please enter \"y\" or \"n\": ");
			promptUserToContinue(scnr);
		}
		return null;
	}
}
