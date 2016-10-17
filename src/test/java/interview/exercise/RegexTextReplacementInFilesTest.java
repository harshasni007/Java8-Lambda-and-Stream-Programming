package interview.exercise;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

public class RegexTextReplacementInFilesTest {

    @Test
    public void inValidParams_inputParamsValidator() {
    	assertFalse(RegexTextReplacementInFiles.inputParamsValidator("",null,null));
    	
    }
    
    @Test
    public void validParams_inputParamsValidator() throws IOException {
    	assertTrue(RegexTextReplacementInFiles.inputParamsValidator(new java.io.File( "." ).getCanonicalPath(),"(a|b)" ,"abc"));
    	
    }
    
    @Test
    public void validParams_fetchValidFiles() throws IOException{
    	List<String> list = RegexTextReplacementInFiles.fetchValidFiles(Paths.get(new java.io.File( "." ).getCanonicalPath()), "*.java");
    	assertNotNull(list);
    }
    
    @Test
    public void inValidFilePattern_fetchValidFiles() throws IOException{
    	List<String> list = RegexTextReplacementInFiles.fetchValidFiles(Paths.get(new java.io.File( "." ).getCanonicalPath()), "*.abcd");
    	assertNull(list);
    }
    
    @Test
    public void validParam_fetchValidFiles() throws IOException{
    	List<String> list = RegexTextReplacementInFiles.fetchValidFiles(Paths.get(new java.io.File( "." ).getCanonicalPath()));
    	assertNotNull(list);
    }
    
    
}
