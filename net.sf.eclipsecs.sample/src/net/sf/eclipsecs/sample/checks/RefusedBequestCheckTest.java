package net.sf.eclipsecs.sample.checks;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

class RefusedBequestCheckTest {

	RefusedBequestCheck check = new RefusedBequestCheck();

	  @Before
	  public void setUp() throws Exception {

	  }
	  
	 @Test
	 public void testGetDefaultTokensNotNull() {
	    assertNotNull("Default tokens should not be null", check.getDefaultTokens());
	 }

	 @Test
	 public void testGetAcceptableTokensNotNull() {
	    assertNotNull("Acceptable tokens should not be null", check.getAcceptableTokens());
	 }

	 @Test
	 public void testGetRequiredTokensNotNull() {
	    assertNotNull("Required tokens should not be null", check.getRequiredTokens());
	 }
		    
    // test the function visitTokenWithoutLog 
	 @Test
	 public void testvisitTokenWithoutLog() throws IOException, CheckstyleException{
		FileText text;
		FileContents contents;
		DetailAST rootAST;		 
		// get project path
		String projectPath=System.getProperty("user.dir").replace("/", File.separator).replace("\\", File.separator);  
		 // get test scenario file
		String testScenarioFile=projectPath+File.separator+"src"+File.separator+"net"+File.separator+"sf"+File.separator
				               +"eclipses"+File.separator+"sample"+File.separator+"tests"+File.separator+"RefusedBequestTestScenario.java";	
		// create /Temp folder
     	File file1=new File(File.separator+"Temp");
 		file1.mkdir(); 		
 		// copy test scenario file to /Temp folder
 		copyFileWithoutPackage(testScenarioFile,File.separator+"temp"+File.separator+"RefusedBequestTestScenario.java");       
 		
		text = new FileText(new File(File.separator+"temp"+File.separator+"RefusedBequestTestScenario.java"), System.getProperty("file.encoding", "UTF-8"));
		
		text = new FileText(new File("C:/Temp/RefusedBequestTestScenario.java"), System.getProperty("file.encoding", "UTF-8"));
		contents = new FileContents(text);
		rootAST = JavaParser.parse(contents);
		
		check.visitTokenWithoutLog(rootAST);
		// visitTokenWithoutLog should find childMethodNum
		assertTrue(check.getChildMethodNum()>0);	
		// visitTokenWithoutLog should find fatherMethodNum
		assertTrue(check.getFatherMethodNum()>0);	
		// visitTokenWithoutLog should find fatherClassName
		assertTrue((check.getFatherClassName()).length()>0);		
		// The method number of child class should be less than that of father class.
		assertTrue(check.getChildMethodNum()<check.getFatherMethodNum());
	 } 

	/**
	 * @param source, dest
	 * @return copy source file to dest file without package line
	 */
	 public void copyFileWithoutPackage(String source, String dest)
	 {
		 try {
			FileReader reader = new FileReader(source);
			BufferedReader br = new BufferedReader(reader);
            FileWriter writer = new FileWriter(dest);
            BufferedWriter bw = new BufferedWriter(writer);			
			String str = null;
			// read source file line by line
            while((str = br.readLine()) != null) {
            	// only copy lines without package
            	if (str.length()>0 && !str.startsWith("package "))
    			{
            		bw.write(str+"\n");
    			}
            }
            br.close();
            reader.close();
            bw.close();
            writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
}

