package dustin.is.learning;

import java.util.ArrayList;

public class PasswordStorage {

	private static boolean passwordStoredSuccessfully(String str) {
		ArrayList<String> passwordHashList = new ArrayList<>();
		if (passwordHashList.contains(str)) {
			return false;
		} else {
			passwordHashList.add(str);
		}
		return true;
	}

	// to pass String to private method from outside class
	public static boolean passHashToStorage(String str) {
		String hashError = "Password hashing error. No data was transmitted. Please try again or notify your administrator.";
		String passwordExists = "Sorry, that password already exists (and now that you know that, please don't guess who's password it is. That would be hacking).";
		boolean hashFail = (str.length() != 64) ? true : false;
		if (hashFail) {
			System.out.println(hashError);
			return false;
		} else if (passwordStoredSuccessfully(str)) {
			return true;
		} else {
			System.out.println(passwordExists);
			return false;
		}
	}

}
