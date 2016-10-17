package interview.exercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;



public class RegexTextReplacementInFiles {


	// The String representing the extension to be used for all processed files.
	private static final String processedFileExtension = ".processed";


	/**
	 * 
	 * This method works as the controller driving the entire functional logic present in the class
	 * @param	 startingPath
	 * 			 The String containing the start point, could b  valid directory or file.
	 * 
	 * @param	 regexPattern
	 * 			 The String containing the String pattern to be replaced.
	 * 
	 * @param	 replacement
	 * 			 The String containing the String to be replaced with.
	 * 
	 * @param 	fileAcceptPattern
	 * 			The UNIX wild-card filename pattern
	 */
	public static void process(String startingPath, String regexPattern,
			String replacement, String fileAcceptPattern) {

		Path startPath = Paths.get(startingPath);

		if(inputParamsValidator(startingPath,regexPattern,replacement)){

			List<String> validFiles = null;
			try {
				if(null!=fileAcceptPattern){

					validFiles= fetchValidFiles(startPath, fileAcceptPattern);
				} 
				else{

					validFiles = fetchValidFiles(startPath);
				} 


				if(null!=validFiles){
					System.out.println("Processing "+ validFiles.size() + " files");
					System.out.println("Replaced to'"+replacement+"':");
					printProcessedWords(replaceTextInFiles(validFiles,regexPattern,replacement));

				}


			}catch (IOException e) {

				e.printStackTrace();
			}

		}

	}

	/**
	 * This method is used to check the validity of given input parameters.
	 * 
	 * @param	 startingPath
	 * 			 The String containing the start point, could b  valid directory or file.
	 * 
	 * @param	 regexPattern
	 * 			 The String containing the String pattern to be replaced.
	 * 
	 * @param	 replacement
	 * 			 The String containing the String to be replaced with.
	 * 
	 * @return	 result 
	 * 		   	 This boolean flag represents the validity of the input parameters.
	 */

	/* private -> modified for testing */  static boolean inputParamsValidator(String startingPath, String regexPattern,String replacement){
		boolean result = true;

		File file = new File(startingPath);

		if(!(file.exists() && (file.isDirectory()||file.isFile()))){
			System.out.println("Given startingPath is not valid");
			result = false;	
		}
		
		if(!Utility.isValidRegex(regexPattern)){
			System.out.println("Invalid regexPattern");
			result = false;
		}
		
		if(null==replacement||replacement.isEmpty()){
			System.out.println("NULL can't be a valid replacement string");
			result = false;
		}

		return result;

	}




	/**
	 * This method gives all the files present in a given directory including files in its sub directories
	 * @param 	startPath
	 * 			The path to search for files
	 * 
	 * @param 	fileAcceptPattern
	 * 			The UNIX wild-card filename pattern
	 * 
	 * @return List<String>
	 * 			A list of absolute paths to all the files 
	 * 
	 * @throws IOException
	 */	

	/* private -> modified for testing */  static List<String> fetchValidFiles(Path startPath, String fileAcceptPattern) throws IOException{
		String javaValidPattern = Utility.wildcardToRegex(fileAcceptPattern);
		List<String> validFiles = new ArrayList<String>();

		try (Stream<Path> fileStream = Files.walk(startPath).parallel())
		{

			fileStream.filter(p -> (p.toFile().isFile() && p.toString().matches(javaValidPattern))).forEach(p -> validFiles.add(p.toFile().getAbsolutePath()));
			fileStream.close();
		}

		return ( (validFiles.size()!=0) ? validFiles : null);


	}

	/**
	 * This method gives all the files present in a given directory including files in its sub directories
	 * @param 	startPath
	 * 			The path to search for files
	 * 
	 * @return List<String>
	 * 			A list of absolute paths to all the files 
	 * 
	 * @throws IOException
	 */

	/* private -> modified for testing */  static List<String> fetchValidFiles(Path startPath) throws IOException{

		List<String> validFiles = new ArrayList<String>();

		try (Stream<Path> fileStream = Files.walk(startPath).parallel())
		{

			fileStream.filter(p -> (p.toFile().isFile())).forEach(p -> validFiles.add(p.toFile().getAbsolutePath()));

			fileStream.close();
		}

		return ( (validFiles.size()!=0) ? validFiles : null);

	}

	/**
	 * This method replaces all the matching groups of the given regexPattern with a given replacement string
	 * 
	 * @param 	validFiles
	 * 			List of all the valid files that are to be transformed
	 * 
	 * @param 	regexPattern
	 * 			String containing the regexpattern representing the words to be replaced
	 * 
	 * @param 	replacement
	 * 			The string to be replaced with
	 * @return  HashMap 
	 * 			returns a hashmap containing the replaced words and their occurrences
	 */

	/* private -> modified for testing */  static HashMap<String, Integer> replaceTextInFiles(List<String> validFiles, String regexPattern, String replacement ){

		FileWriter fw = null;
		BufferedReader br = null;
		Pattern p = Pattern.compile(regexPattern);
		HashMap<String, Integer> replacedWords = new HashMap<String, Integer>();

		try  {
			for(String file: validFiles){
				boolean fileCreated = false;
				String s;
				StringBuilder totalStr = new StringBuilder();
				File accessFile = new File(file);

				//Tests whether the application can access a file or not 

				try{
					br = Files.newBufferedReader(Paths.get(file));

				}catch (AccessDeniedException e){
					System.out.println("This program doesn't have the required permissions to open file:"+file);
					continue;
				}

				while ((s = br.readLine()) != null) {

					String matchGroup = null;
					Matcher m = p.matcher (s);
					while (m.find()) {

						if(!fileCreated){
							fileCreated = true;
							fw = new FileWriter(new File(file+processedFileExtension));

						}
						if(replacedWords.containsKey(m.group())){
							int count =replacedWords.get(m.group())+1;
							replacedWords.put(m.group(), count);

						}else{
							matchGroup = m.group(1);
							replacedWords.put(m.group(), 1);

						}

					}
					totalStr.append((null!=matchGroup)? s.replaceAll(matchGroup, replacement): " ");
				}
				if(fileCreated){
					fw.write(totalStr.toString());
					fw.close();
				}
				br.close();
			}

		}catch (IOException e) {

			e.printStackTrace();
		}

		return replacedWords;
	}





	/**
	 * This method prints all the words that matched the given regex
	 * and their total number of occurrences
	 * 
	 * @param   matchedWordsmap 
	 * 		  	This is a Map that contains the words and their counts 
	 */

	 private static void printProcessedWords(Map<String, Integer> matchedWordsmap){

		if(!matchedWordsmap.isEmpty())

			for (Map.Entry<String, Integer> entry : matchedWordsmap.entrySet()) {
				System.out.println("* "+entry.getKey()+ " : "+entry.getValue()+" occurence" );
			}



	}

	public static void main(String[] args) {
		String startingDir = null, regexPattern = null, replacement = null, fileAcceptPattern = null;
		if (args.length >= 3) {
			startingDir = args[0];
			regexPattern = args[1];
			replacement = args[2];
		}
		if (args.length >= 4) {
			fileAcceptPattern = args[3];
		}
		if (startingDir != null) {
			process(startingDir, regexPattern, replacement, fileAcceptPattern);
		} else {
			System.out.println("Expected at least 3 parameters but got " + args.length);
		}
	}

}
