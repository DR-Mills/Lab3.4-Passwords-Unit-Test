package dustin.is.learning;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordPrompt {

	public static void main(String[] args) {

		hashedPassword("password");
	}

	public static boolean isLength7to12(String str) {
		return (str.length() >= 7 && str.length() <= 12);
	}

	public static boolean hasNumberExcluding6(String str) {
		Pattern numsNot6 = Pattern.compile("[0-5[7-9]]");
		Matcher match = numsNot6.matcher(str);
		return match.find();
	}

	public static boolean containsSpace(String str) {
		return str.contains(" ");
	}

	public static boolean contains2CapitalVowels(String str) {
		Pattern vowels = Pattern.compile("[AEIOUY]");
		Matcher match = vowels.matcher(str);
		int count = 0;
		while (match.find()) {
			count++;
		}
		return (count >= 2) ? true : false;
	}

	public static boolean isABadSecurityException(String str) {
		return (str == "admin" || str == "mod");
	}

	public static String hashedPassword(String str) {
		final String hashed = DigestUtils.sha256Hex(str);
		System.out.println(hashed);
		return hashed;
	}
}
