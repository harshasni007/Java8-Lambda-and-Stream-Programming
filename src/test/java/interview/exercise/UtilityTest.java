package interview.exercise;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilityTest {
	
	/**
	 * Checks the interview.exercise.Utility.wildcardToRegex method with null parameter 
	 */
	@Test
	public void nullParam_wildcardToRegextest() {
		String result= Utility.wildcardToRegex(null);
		assertNull("Expected null value", result);
		
		
	}
	
	/**
	 * Checks the interview.exercise.Utility.wildcardToRegex method with 
	 * a valid UNIX wild-card filename syntax as parameter 
	 */
	@Test
	public void validParam_wildcardToRegextest() {
		String result= Utility.wildcardToRegex("(a|b)?[1-9]*.txt");
		assertTrue(Utility.isValidRegex(result));
	}
	
	/**
	 * Checks the interview.exercise.Utility.wildcardToRegex method with 
	 * a empty string as parameter 
	 */
	@Test
	public void emptyString_wildcardToRegextest() {
		String result= Utility.wildcardToRegex("     ");
		assertNull("Expected null value", result);
		
		
	}
	
	/**
	 * Checks the interview.exercise.Utility.isValidRegex method with null parameter as input
	 */
	
	@Test
	public void nullParam_isValidRegex(){
		assertFalse(Utility.isValidRegex(null));
	}
	

	/**
	 * Checks the interview.exercise.Utility.wildcardToRegex method with 
	 * a valid UNIX wild-card filename syntax as parameter 
	 */
	@Test
	public void validParam_isValidRegex() {
		String result= Utility.wildcardToRegex("(a|b)?[1-9]*.txt");
		assertTrue(Utility.isValidRegex(result));
	}
	
	/**
	 * Checks the interview.exercise.Utility.wildcardToRegex method with 
	 * a valid UNIX wild-card filename syntax as parameter 
	 */
	@Test
	public void invalidParam_isValidRegex() {
		String result= "*.txt";
		assertFalse(Utility.isValidRegex(result));
	}
	
	
	/**
	 * Checks the interview.exercise.Utility.wildcardToRegex method with 
	 * a empty string as parameter 
	 */
	@Test
	public void emptyString_isValidRegex() {
		assertTrue(Utility.isValidRegex(" "));
		
		
	}
	
	

}
