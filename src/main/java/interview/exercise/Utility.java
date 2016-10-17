package interview.exercise;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public final class Utility {

	/**
	 * This method converts the given valid UNIX wild-card filename syntax
	 * to Java specific regular expression
	 * 
	 * @param 	wildcard 
	 * 			This String contains UNIX wild-card filename
	 * @return 	String	
	 * 			A valid Java regular expression
	 */
	public static String wildcardToRegex(String wildcard){
		String result = null;
		
		if(null!=wildcard && !wildcard.trim().isEmpty()){
			wildcard = wildcard.trim();
			StringBuilder s = new StringBuilder(wildcard.length());
			s.append('^');
			for (int i = 0, is = wildcard.length(); i < is; i++) {
				char c = wildcard.charAt(i);
				switch(c) {
				case '*':
					s.append(".*");
					break;
				case '?':
					s.append(".");
					break;
				case '^': // escape character in cmd.exe
					s.append("\\");
					break;
					// escape special regexp-characters
				case '(': case ')': case '[': case ']': case '$':
				case '.': case '{': case '}': case '|':
				case '\\':
					s.append("\\");
					s.append(c);
					break;
				default:
					s.append(c);
					break;
				}
			}
			s.append('$');
			result = (s.toString());
		}
		return result;

	}
	
	/**
	 * This method checks for the validity of a given regular expression
	 * @param 	regex
	 * 			Regular expression String
	 * @return 	boolean
	 * 			A flag notifying the validity of the regular expression
	 */
	
	@SuppressWarnings("finally")
	public static boolean isValidRegex(String regex){
		boolean result = true;
		try {
			Pattern.compile(regex);
			result = true;
		} catch (PatternSyntaxException e) {

			result = false;
		}catch(NullPointerException e){
			result = false;
		}
		finally{
			return result;
		}
	}

}
