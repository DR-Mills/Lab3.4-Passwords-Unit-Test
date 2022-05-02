package dustin.is.learning;

import java.util.ArrayList;

public class PasswordStorage {

	static ArrayList<String> passwordHashList = new ArrayList<>();

	public static boolean wasHashStoredSuccessfully(String str) {
		String hashError = "Password hashing error. No data was transmitted.";
		String passwordExists = "Sorry, that password already exists (and now that you know that, please don't guess who's password it is. That would be hacking).";
		boolean hashFail = (str.length() != 64) ? true : false;
		if (hashFail) {
			System.out.println(hashError);
			return false;
		} else if (!passwordHashList.contains(str)) {
			passwordHashList.add(str);
			System.out.println("Password is valid and has been stored successfully.");
			return true;
		} else {
			System.out.println(passwordExists);
			return false;
		}
	}
}
