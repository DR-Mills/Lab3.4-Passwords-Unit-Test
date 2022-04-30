package dustin.is.learning;

import java.util.ArrayList;

public class PasswordStorage {

	static ArrayList<String> passwordHashList = new ArrayList<>();

	private static boolean passwordStoredSuccessfully(String str) {
		if (passwordHashList.contains(str)) {
			return false;
		} else {
			passwordHashList.add(str);
		}
		return true;
	}

	// to pass String to private method from outside class
	public static boolean passHashToStorage(String str) {
		String hashError = "Password hashing error. No data was transmitted.";
		String passwordExists = "Sorry, that password already exists (and now that you know that, please don't guess who's password it is. That would be hacking).";
		boolean hashFail = (str.length() != 64) ? true : false;
		if (hashFail) {
			System.out.println(hashError);
			return false;
		} else if (passwordStoredSuccessfully(str)) {
			System.out.println("Password is valid and has been stored successfully.");
			return true;
		} else {
			System.out.println(passwordExists);
			return false;
		}
	}

	public static void printPasswordDatabase() {
		System.out.println(passwordHashList);
	}
}
