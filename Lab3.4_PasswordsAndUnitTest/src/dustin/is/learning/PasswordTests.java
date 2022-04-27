package dustin.is.learning;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PasswordTests {

	// TESTS FOR LENGTH 7-12-----------------------------------------------------------
	@Test
	void passwordLength6() {
		String test = "iamsix";
		boolean actual = PasswordPrompt.isLength7to12(test);
		assertFalse(actual);
	}

	@Test
	void passwordLength8() {
		String test = "iameight";
		boolean actual = PasswordPrompt.isLength7to12(test);
		assertTrue(actual);
	}
	
	@Test
	void passwordLength13() {
		String test = "iambeyondsize";
		boolean actual = PasswordPrompt.isLength7to12(test);
		assertFalse(actual);
	}

	// TESTS FOR NUMBER BUT FAILS IF NUMBER IS 6-------------------------------------
	@Test
	void hasNumber4() {
		String test = "ihavea4";
		boolean actual = PasswordPrompt.hasNumberExcluding6(test);
		assertTrue(actual);
	}
	
	@Test
	void failsWithNumber6() {
		String test = "ihavea6";
		boolean actual = PasswordPrompt.hasNumberExcluding6(test);
		assertFalse(actual);
	}

	@Test
	void hasNumber8() {
		String test = "ihavean8";
		boolean actual = PasswordPrompt.hasNumberExcluding6(test);
		assertTrue(actual);
	}

	// TESTS FOR SPACE---------------------------------------------------------------
	@Test
	void noSpace() {
		String test = "nospaceshere";
		boolean actual = PasswordPrompt.containsSpace(test);
		assertFalse(actual);
	}

	@Test
	void hasSpace() {
		String test = "I have spaces";
		boolean actual = PasswordPrompt.containsSpace(test);
		assertTrue(actual);
	}
	
	// TESTS FOR CONTAINING AT LEAST 2 CAPITAL VOWELS---------------------------------
	@Test
	void has2LowercaseVowels() {
		String test = "vowels";
		boolean actual = PasswordPrompt.contains2CapitalVowels(test);
		assertFalse(actual);
	}
	
	@Test
	void has2SameUppercaseVowels() {
		String test = "bOOk";
		boolean actual = PasswordPrompt.contains2CapitalVowels(test);
		assertTrue(actual);
	}
	
	@Test
	void has1UpperCase1LowercaseVowel() {
		String test = "vowEls";
		boolean actual = PasswordPrompt.contains2CapitalVowels(test);
		assertFalse(actual);
	}
	
	@Test
	void hasManyLowercaseVowels() {
		String test = "draagoonii";
		boolean actual = PasswordPrompt.contains2CapitalVowels(test);
		assertFalse(actual);
	}
	
	@Test
	void hasManyUppercaseVowels() {
		String test = "AEIOUandsometimesY";
		boolean actual = PasswordPrompt.contains2CapitalVowels(test);
		assertTrue(actual);
	}
	
	// TESTS FOR ALLOWING "admin" AND "mod" AS PASSWORDS----------------------------
	@Test
	void isYourEntireEnterpriseHackableByA6YearOld() {
		String test1 = "admin";
		String test2 = "mod";
		boolean actual = (PasswordPrompt.isABadSecurityException(test1) && PasswordPrompt.isABadSecurityException(test2));
		assertTrue(actual);	
	}
	
	@Test
	void admin123ShouldFailTheBadSecurityException() {
		String test = "admin123";
		boolean actual = PasswordPrompt.isABadSecurityException(test);
		assertFalse(actual);
	}
	
	@Test
	// TESTS THAT STRINGS ARE CORRECTLY HASHED ACCORDING TO KNOWN SHA256 HASHES-----
	void returnsSha256() {
		String Sha256HashOfpassword = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
		String actual = PasswordPrompt.hashedPassword("password");
		assertEquals(Sha256HashOfpassword, actual);
	}
}

