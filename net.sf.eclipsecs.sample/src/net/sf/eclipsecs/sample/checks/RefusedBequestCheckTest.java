/**
 * File: src/net.sf.eclipsecs.sample.checks/RefusedBequestCheckTest.java
 * -------------------------------------------------------------------------------------------
 * Date			Author          Changes
 * -------------------------------------------------------------------------------------------
 * 04/08/2019	xiaoqin Fu		created; Test cases for Refused Bequest Check (Deliverable 1)
 * 04/14/2019	xiaoqin Fu		updated; Adding and updating test cases for Refused Bequest Check (Deliverable 2)
*/
package net.sf.eclipsecs.sample.checks;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.jupiter.api.Test;

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
	 
	 // test childMethodNum default value
	 @Test
	 public void testDefaultChildMethodNum(){
		//  childMethodNum default value should be 0
		 assertEquals(0, check.getChildMethodNum());
	 }

	 // test fatherMethodNum default value
	 @Test
	 public void testDefaultFatherMethodNum(){
		//  fatherMethodNum default value should be 0
		 assertEquals(0, check.getFatherMethodNum());
	 }

	 // test fatherClassName default value
	 @Test
	 public void testDefaultFatherClassName(){
		 String fatherClassNameReceived=check.getFatherClassName();
		 // fatherClassName default value should be empty string with length 0
		 assertEquals("", fatherClassNameReceived);
		 assertEquals(0, fatherClassNameReceived.length());
	 }
	 
	 // test the function setChildMethodNum (getChildMethodNum())
	 @Test	   
	 public void testSetChildMethodNum(){
		 check.setChildMethodNum(-1);
		 assertEquals(-1, check.getChildMethodNum());
		 check.setChildMethodNum(0);
		 assertEquals(0, check.getChildMethodNum());
		 check.setChildMethodNum(1);
		 assertEquals(1, check.getChildMethodNum());
	 }	
	 
	 // test the function setFatherMethodNum (getFatherMethodNum())
	 @Test	   
	 public void testSetFatherMethodNum(){
		 check.setFatherMethodNum(-1);
		 assertEquals(-1, check.getFatherMethodNum());
		 check.setFatherMethodNum(0);
		 assertEquals(0, check.getFatherMethodNum());
		 check.setFatherMethodNum(1);
		 assertEquals(1, check.getFatherMethodNum());
	 }	
	 
	 // test the function setFatherClassName (getFatherClassName())
	 @Test	   
	 public void testSetFatherClassName(){
		 check.setFatherClassName("a");
		 assertEquals("a", check.getFatherClassName());
		 check.setFatherClassName("testclass");
		 assertEquals("testclass", check.getFatherClassName());
		 check.setFatherClassName("class1234567890123456789012345678901234567890");
		 assertEquals("class1234567890123456789012345678901234567890", check.getFatherClassName());
		 check.setFatherClassName("");
		 assertEquals("", check.getFatherClassName());

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

