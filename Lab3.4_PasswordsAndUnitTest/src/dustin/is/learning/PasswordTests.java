package dustin.is.learning;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class PasswordTests {
	
	//ArrayList Changed
	@Test
	void arrayListChanged() {
		boolean instance1 = PasswordStorage.wasHashStoredSuccessfully("dd4599fce4cc5120366aead85f79b7c9c3ba2de414881f08d9a805f39dcfa33e");
		boolean instance2 = PasswordStorage.wasHashStoredSuccessfully("ecb16108b9da4a65a72113cc1d0a520edc441a64354cfc3dfd2d5870b50eb9cb");
		int actualLength = PasswordStorage.passwordHashList.size();
		assertEquals(2, actualLength);
	}
	
	//True Boolean Returned
	@Test
	void passwordStoredSuccessfully() {
		boolean actual = PasswordStorage.wasHashStoredSuccessfully("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz123456789012");
		assertEquals(true, actual);
	}
	
	//Password Doesn't Already Exist
	@Test
	void passwordDoesntAlreadyExist() {
		boolean actual = PasswordStorage.wasHashStoredSuccessfully("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz123456789009");
		assertEquals(true, actual);
	}

	//Password Already Exist
	@Test
	void passwordExists() {
		boolean instance1 = PasswordStorage.wasHashStoredSuccessfully("dd4599fce4cc5120366aead85f79b7c9c3ba2de414881f08d9a805f39dcfa33e");
		boolean actual = PasswordStorage.wasHashStoredSuccessfully("dd4599fce4cc5120366aead85f79b7c9c3ba2de414881f08d9a805f39dcfa33e");
		assertEquals(false, actual);
	}
	
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
		boolean pwd1 = PasswordValidation.doesPasswordMeetPolicyReqs(password1);
		boolean pwd2 = PasswordValidation.doesPasswordMeetPolicyReqs(password2);
		assertEquals(true, (pwd1 && pwd2));
	}
	
	@Test
	void normalAcceptablePassword() {
		String password = "pAsswOrd4";
		boolean actual = PasswordValidation.doesPasswordMeetPolicyReqs(password);
		assertEquals(true, actual);
	}

	@Test
	void passwordTooLong() {
		String password = "pAsswOrd4xxxxxxxxxxx";
		boolean actual = PasswordValidation.doesPasswordMeetPolicyReqs(password);
		assertEquals(false, actual);
	}
	
	@Test
	void passwordTooShort() {
		String password = "pAOrd4";
		boolean actual = PasswordValidation.doesPasswordMeetPolicyReqs(password);
		assertEquals(false, actual);
	}
	
	@Test
	void isTuesdayHasTaco() {
		String password = "taco1234";
		boolean actual = PasswordValidation.isTuesdayButDoesntHaveTaco(2, password);
		assertEquals(false, actual);
	}
	
	@Test
	void isTuesdayButThereIsNoTaco() {
		String password = "password1234";
		boolean actual = PasswordValidation.isTuesdayButDoesntHaveTaco(3, password);
		assertEquals(true, actual);
	}
}

