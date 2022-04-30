package dustin.is.learning;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordValidation {
	
	public static Calendar calendar = Calendar.getInstance();

	public static boolean doesPasswordMeetsPolicyReqs(String str) {
		if (isABadSecurityException(str) || isAnUnusualSecurityException(str)) {
			return true;
		}
		if (mustContainTaco() && !containsTaco(str)) {
			return false;
		}
		if (isLength7to12(str) && hasNumberExcluding6(str) && !containsSpace(str) && contains2CapitalVowels(str)) {
			return true;
		}
		return false;
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
		return (str.equals("admin") || str.equals("mod"));
	}
	
	public static boolean isAnUnusualSecurityException(String str) {
		return (isBetween12and1am() && (str.equals("Red Rumm") || str.equals("Spooky")));
	}
	
	public static boolean mustContainTaco() {
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		if (day == 2) {
			return true;
		} else {
			System.out.println("Today is Tuesday; please add a \"taco\" to your password.");
			return false;
		}
	}
	
	public static boolean isBetween12and1am() {
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		return (hour >= 0 && hour < 1);
	}
	
	public static boolean containsTaco(String str) {
		String lowercase = str.toLowerCase();
		return lowercase.contains("taco");
	}
		
	public static String hashesPassword(String str) {
		final String hashed = DigestUtils.sha256Hex(str);
		System.out.println(hashed);
		return hashed;
	}
}