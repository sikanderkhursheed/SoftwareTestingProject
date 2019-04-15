/**
 * File: src/net.sf.eclipsecs.sample.checks/BlobCheckTest.java
 * -------------------------------------------------------------------------------------------
 * Date			Author          Changes
 * -------------------------------------------------------------------------------------------
 * 04/08/2019	xiaoqin Fu		created; Black box and white box test cases for Blob Check (Deliverable 1)
 * 04/14/2019	xiaoqin Fu		updated; Adding and updating test cases for Blob Check (Deliverable 2)
*/
package net.sf.eclipsecs.sample.checks;

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

import static org.junit.Assert.*;


class BlobCheckTest {

	BlobCheck check = new BlobCheck();
	 
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
	 
	 // test max default value
	 @Test
	 public void testDefaultMax(){
		 //  max default value should be 20
		 assertEquals(20, check.getMax());
	 }
	 
	 // test the function setMax (getMax())
	 @Test	   
	 public void testSetMax(){
		 check.setMax(Integer.MIN_VALUE);
		 assertEquals(Integer.MIN_VALUE, check.getMax());		 
		 check.setMax(-20);
		 assertEquals(-20, check.getMax());
		 check.setMax(-1);
		 assertEquals(-1, check.getMax());
		 check.setMax(0);
		 assertEquals(0, check.getMax());
		 check.setMax(1);
		 assertEquals(1, check.getMax());
		 check.setMax(10);
		 assertEquals(10, check.getMax());
		 check.setMax(19);
		 assertEquals(19, check.getMax());
		 check.setMax(21);
		 assertEquals(21, check.getMax());
		 check.setMax(200);
		 assertEquals(200, check.getMax());
		 check.setMax(Integer.MAX_VALUE);
		 assertEquals(Integer.MAX_VALUE, check.getMax());	
		 check.setMax(20);
		 assertEquals(20, check.getMax());
	 }	
	 
	 // test the function setCount (getCount())
	 @Test	   
	 public void testSetCount(){
		 check.setCount(Integer.MIN_VALUE);
		 assertEquals(Integer.MIN_VALUE, check.getCount());		 
		 check.setCount(-20);
		 assertEquals(-20, check.getCount());
		 check.setCount(-1);
		 assertEquals(-1, check.getCount());
		 check.setCount(1);
		 assertEquals(1, check.getCount());
		 check.setCount(10);
		 assertEquals(10, check.getCount());		 
		 check.setCount(Integer.MAX_VALUE);
		 assertEquals(Integer.MAX_VALUE, check.getCount());	
		 check.setCount(0);
		 assertEquals(0, check.getCount());
	 }	
	 
	 // test the function visitTokenWithoutLog 
	 @Test
	 public void testvisitTokenWithoutLog() throws IOException, CheckstyleException{		 
		FileText text;
		FileContents contents;
		DetailAST rootAST;		
		copyFileToTemp("BlobTestScenario.java");  
		
		text = new FileText(new File(File.separator+"Temp"+File.separator+"BlobTestScenario.java"), System.getProperty("file.encoding", "UTF-8"));
		contents = new FileContents(text);
		rootAST = JavaParser.parse(contents);
		check.visitTokenWithoutLog(rootAST);
        // Function visitTokenWithoutLog should let the count value is greater than zero and we use getCount() to get the count value.
		//System.out.println("check.getCount()="+check.getCount());
        assertTrue(check.getCount()>0);
     // Function visitTokenWithoutLog should let the count value is greater than or equals to max value set.
		//System.out.println("check.getMax()="+check.getMax());
        assertTrue(check.getCount()>=check.getMax()); 
        
        
	 } 
	 
		/**
		 * @param source, dest
		 * copy test scenario file to /Temp folder without package line
		 */
		 private void copyFileToTemp(String fileName)
		 {
			// get project path
				String projectPath=System.getProperty("user.dir").replace("/", File.separator).replace("\\", File.separator);  
				 // get test scenario file
				String testScenarioFile=projectPath+File.separator+"src"+File.separator+"net"+File.separator+"sf"+File.separator
						               +"eclipses"+File.separator+"sample"+File.separator+"tests"+File.separator+fileName;	
				// create /Temp folder
		    	File file1=new File(File.separator+"Temp");
				file1.mkdir(); 		
				// copy test scenario file to /Temp folder without package line
				copyFileWithoutPackage(testScenarioFile,File.separator+"Temp"+File.separator+fileName);       
		 }
		 
		/**
		 * @param source, dest
		 * copy source file to dest file without package line
		 */
		 private void copyFileWithoutPackage(String source, String dest)
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
