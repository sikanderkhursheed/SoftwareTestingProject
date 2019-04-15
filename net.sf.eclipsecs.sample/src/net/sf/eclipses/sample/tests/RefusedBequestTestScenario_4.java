package net.sf.eclipses.sample.tests;

public class RefusedBequestTestScenario_4 {
	interface shape {
		  // output default value of name
		  void outputDefaultName();
	}
	
	// child class without Refused Bequest
	class rectangle implements shape {
		@Override
		public void outputDefaultName() {
			// TODO Auto-generated method stub
			System.out.println("Rectangle");
		}
			
		}
}
