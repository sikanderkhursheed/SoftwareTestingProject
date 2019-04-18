/**
 * File: src/net.sf.eclipsecs.sample.checks/RefusedBequestCheckWhiteBoxTest.java
 * -------------------------------------------------------------------------------------------
 * Date			Author          Changes
 * -------------------------------------------------------------------------------------------
 * 04/14/2019	xiaoqin Fu		created; White box test cases for Refused Bequest Check (Deliverable 2)
*/
package net.sf.eclipsecs.sample.checks;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

class RefusedBequestCheckWhiteBoxTest {
	RefusedBequestCheck check = new RefusedBequestCheck();
	
	 // test the function visitToken 
	 @Test
	 public void testvisitToken() throws IOException, CheckstyleException{	
		 check.setChildMethodNum(0);
		 check.setFatherMethodNum(0);	
		 check.setFatherClassName("");		 
        // test null
		check.visitToken(null);
		assertEquals(0, check.getChildMethodNum());
		assertEquals(0, check.getFatherMethodNum());
		assertEquals("", check.getFatherClassName());
		// test an empty file
		DetailAST rootAST_2=getRootAST("RefusedBequestTestScenario_2.java");  	
		check.visitToken(rootAST_2); 
		assertEquals(0, check.getChildMethodNum());
		assertEquals(0, check.getFatherMethodNum());
		assertEquals("", check.getFatherClassName());
		
		// test a java file which only has one child class.
		DetailAST rootAST_3=getRootAST("RefusedBequestTestScenario_3.java");
		check.visitToken(rootAST_3);
		assertEquals(0, check.getChildMethodNum());
		assertEquals(0, check.getFatherMethodNum());
		assertEquals("", check.getFatherClassName());	

		// test a java file which has one interface and one child class with one method.
		DetailAST rootAST_4=getRootAST("RefusedBequestTestScenario_4.java");
		check.visitToken(rootAST_4);
		assertEquals(1, check.getChildMethodNum());
		assertEquals(0, check.getFatherMethodNum());
		assertEquals("shape", check.getFatherClassName());
		
		// test a java file which has one father class with 28 methods and three sub-classes.
		// The last child class has 3 methods
		DetailAST rootAST=getRootAST("RefusedBequestTestScenario.java");  	
		check.visitTokenWithoutLog(rootAST); 
		assertEquals(3, check.getChildMethodNum());
		assertEquals(28, check.getFatherMethodNum());
		assertEquals("shape", check.getFatherClassName());
	 } 

	/**
	 * @param filename
	 * @return get root AST from filename
	 * @throws IOException 
	 * @throws CheckstyleException 
	 */
	 private DetailAST getRootAST(String fileName) throws IOException, CheckstyleException
	 {	 
			FileText text;
			FileContents contents;
			copyFileToTemp(fileName);  
			
			text = new FileText(new File(File.separator+"Temp"+File.separator+fileName), System.getProperty("file.encoding", "UTF-8"));
			contents = new FileContents(text);
			return JavaParser.parse(contents);				
	 }
	/**
	 * @param filename
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
