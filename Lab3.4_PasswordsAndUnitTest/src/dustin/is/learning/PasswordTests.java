package dustin.is.learning;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.function.Supplier;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


class PasswordTests {
@Mock
Calendar blah;
	// TESTS FOR LENGTH 7-12-----------------------------------------------------------
	@Test
	void passwordLength6() {
		String test = "iamsix";
		boolean actual = PasswordValidation.isLength7to12(test);
		assertFalse(actual);
	}

	@Test
	void passwordLength8() {
		String test = "iameight";
		boolean actual = PasswordValidation.isLength7to12(test);
		assertTrue(actual);
	}
	
	@Test
	void passwordLength13() {
		String test = "iambeyondsize";
		boolean actual = PasswordValidation.isLength7to12(test);
		assertFalse(actual);
	}

	// TESTS FOR NUMBER BUT FAILS IF NUMBER IS 6-------------------------------------
	@Test
	void hasNumber4() {
		String test = "ihavea4";
		boolean actual = PasswordValidation.hasNumberExcluding6(test);
		assertTrue(actual);
	}
	
	@Test
	void failsWithNumber6() {
		String test = "ihavea6";
		boolean actual = PasswordValidation.hasNumberExcluding6(test);
		assertFalse(actual);
	}

	@Test
	void hasNumber8() {
		String test = "ihavean8";
		boolean actual = PasswordValidation.hasNumberExcluding6(test);
		assertTrue(actual);
	}

	// TESTS FOR SPACE---------------------------------------------------------------
	@Test
	void noSpace() {
		String test = "nospaceshere";
		boolean actual = PasswordValidation.containsSpace(test);
		assertFalse(actual);
	}

	@Test
	void hasSpace() {
		String test = "I have spaces";
		boolean actual = PasswordValidation.containsSpace(test);
		assertTrue(actual);
	}
	
	// TESTS FOR CONTAINING AT LEAST 2 CAPITAL VOWELS---------------------------------
	@Test
	void has2LowercaseVowels() {
		String test = "vowels";
		boolean actual = PasswordValidation.contains2CapitalVowels(test);
		assertFalse(actual);
	}
	
	@Test
	void has2SameUppercaseVowels() {
		String test = "bOOk";
		boolean actual = PasswordValidation.contains2CapitalVowels(test);
		assertTrue(actual);
	}
	
	@Test
	void has1UpperCase1LowercaseVowel() {
		String test = "vowEls";
		boolean actual = PasswordValidation.contains2CapitalVowels(test);
		assertFalse(actual);
	}
	
	@Test
	void hasManyLowercaseVowels() {
		String test = "draagoonii";
		boolean actual = PasswordValidation.contains2CapitalVowels(test);
		assertFalse(actual);
	}
	
	@Test
	void hasManyUppercaseVowels() {
		String test = "AEIOUandsometimesY";
		boolean actual = PasswordValidation.contains2CapitalVowels(test);
		assertTrue(actual);
	}
	
	// TESTS FOR ALLOWING "admin" AND "mod" AS PASSWORDS----------------------------
	@Test
	void isYourEntireEnterpriseHackableByA6YearOld() {
		String test1 = "admin";
		String test2 = "mod";
		boolean actual = (PasswordValidation.isABadSecurityException(test1) && PasswordValidation.isABadSecurityException(test2));
		assertTrue(actual);	
	}
	
	@Test
	void admin123ShouldFailTheBadSecurityException() {
		String test = "admin123";
		boolean actual = PasswordValidation.isABadSecurityException(test);
		assertFalse(actual);
	}
	
	// TESTS THAT STRINGS ARE CORRECTLY HASHED ACCORDING TO KNOWN SHA256 HASHES-----
	@Test
	void returnsSha256() {
		String Sha256HashOfpassword = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
		String actual = PasswordValidation.hashesPassword("password");
		assertEquals(Sha256HashOfpassword, actual);
	}

	// 	TESTS STRINGS MEET PASSWORD REQUIREMENTS
	@Test
	void passwordIsAdmin() {
		String password1 = "admin";
		String password2 = "mod";
		boolean pwd1 = PasswordValidation.doesPasswordMeetsPolicyReqs(password1);
		boolean pwd2 = PasswordValidation.doesPasswordMeetsPolicyReqs(password2);
		assertEquals(true, (pwd1 && pwd2));
	}
	
	@Test
	void normalAcceptablePassword() {
		String password = "pAsswOrd4";
		boolean actual = PasswordValidation.doesPasswordMeetsPolicyReqs(password);
		assertEquals(true, actual);
	}

	@Test
	void passwordTooLong() {
		String password = "pAsswOrd4xxxxxxxxxxx";
		boolean actual = PasswordValidation.doesPasswordMeetsPolicyReqs(password);
		assertEquals(false, actual);
	}
	
	@Test
	void passwordTooShort() {
		String password = "pAOrd4";
		boolean actual = PasswordValidation.doesPasswordMeetsPolicyReqs(password);
		assertEquals(false, actual);
	}

	void isTuesdayHasTaco() {
		when(PasswordValidation.mustContainTaco()).thenReturn(true);
		String password = "tAc04aaa";
		boolean actual = PasswordValidation.doesPasswordMeetsPolicyReqs(password);
		assertEquals(true, actual);
	}
	
}

