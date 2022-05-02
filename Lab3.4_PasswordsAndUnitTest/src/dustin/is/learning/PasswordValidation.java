package dustin.is.learning;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordValidation {

	public static Calendar calendar = Calendar.getInstance();
	static int day = calendar.get(Calendar.DAY_OF_WEEK);
	static int hour = calendar.get(Calendar.HOUR_OF_DAY);

	public static boolean doesPasswordMeetPolicyReqs(String str) {
		if (isABadSecurityException(str) || isAnUnusualSecurityException(hour, str)) {
			return true;
		}
		if (isTuesdayButDoesntHaveTaco(day, str)) {
			return false;
		}
		if (isLength7to12(str) && hasNumberExcluding6(str) && !containsSpace(str) && contains2CapitalVowels(str)) {
			return true;
		}
		return false;
	}

	public static boolean isLength7to12(String str) {
		if (str.length() >= 7 && str.length() <= 12) {
			return true;
		} else {
			System.out.println("Password must be between 7-12 characters long.");
			return false;
		}
	}

	public static boolean hasNumberExcluding6(String str) {
		Pattern numsNot6 = Pattern.compile("[0-5[7-9]]");
		Matcher match = numsNot6.matcher(str);
		if (str.contains("6")) {
			System.out.println("The number 6 is not allowed.");
			return false;
		} else if (match.find()) {
			return true;
		} else {
			System.out.println("Password must contain a number, excluding 6.");
			return false;
		}
	}

	public static boolean containsSpace(String str) {
		if (str.contains(" ")) {
			System.out.println("Spaces are not allowed.");
			return true;
		} else {
			return false;
		}
	}

	public static boolean contains2CapitalVowels(String str) {
		Pattern vowels = Pattern.compile("[AEIOUY]");
		Matcher match = vowels.matcher(str);
		int count = 0;
		while (match.find()) {
			count++;
		}
		if (count >= 2) {
			return true;
		} else {
			System.out.println("Password must contain at least 2 capital vowels.");
			return false;
		}
	}

	public static boolean isABadSecurityException(String str) {
		return (str.equals("admin") || str.equals("mod"));
	}

	public static boolean isAnUnusualSecurityException(int hour, String str) {
		return ((hour >= 0 && hour < 1) && (str.equals("Red Rum") || str.equals("Spooky")));
	}

	public static boolean isTuesdayButDoesntHaveTaco(int day, String str) {
		if ((day == 3) && (!str.toLowerCase().contains("taco"))) {
			System.out.println("Today is Tuesday; please add a \"taco\" to your password.");
			return true;
		} else {
			return false;
		}
	}

	public static String hashesPassword(String str) {
		final String hashed = DigestUtils.sha256Hex(str);
		return hashed;
	}
}