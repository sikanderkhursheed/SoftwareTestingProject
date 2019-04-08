# CPTS582

Team members:
1. Sikander Khursheed   (Type Checking & Duplicate Code)
2. Shiv Raj Pant	(Feature Envy & Swiss Army Knife)	
3. Xiaoqin Fu		(Blob & Refused Bequest)

How to run and test the project:
*To run the project you msut have Eclipse RCP IDE and CheckStyle installed in it*

1. Download or clone the project from repository
2. Open Eclipse IDE and import the project
3. Right click on the project named 'net.sf.eclipsecs.sample' and Run as Eclipse Application
4. A runtime IDE of Eclipse will pop up
5. Select Window from the top menu bar and go to preferences.
   a. A toolbox pops up
   b. Select CheckStyle
   c. Add new check and name it as test
   d. Set your newly made test as default
   e. Click configure and go to My Custom Checks
   f. Selects the check you want to apply on the project
6. Now import the project cloned from the repository named 'net.sf.eclipsecs.sample' 
*We have added test scenarios to test it under the custom checks*
7. Right click the project and click Checkstyle -> Check code with Checkstyle


Test Scenarios that we added are named after each checkstyle so that it can be easy to run that particular CheckStyle on that test scenario
Test Scenarios are in the package named 'net.sf.eclipses.sample.tests'
(Test Scenarios)    (Checks)
BlobTestScenario -> Blob Check
DuplicateCodeTestScenario -> Duplicate Code Check
FeatureEnvyScenario -> Feature Envy Check
RefusedBequestTestScenario -> Refused Bequest Check
SwissKnifeScenario -> Swiss Army Knife Check
TypeCheckTestScenario -> Type Checking Check

The test cases are under the package 'net.sf.eclipsecs.sample.checks' and each test case is named after the check with a suffix of 'test'. To run the test cases follow the following steps
1. open the test case file
2. Right click on the file and select Run as -> Junit Test