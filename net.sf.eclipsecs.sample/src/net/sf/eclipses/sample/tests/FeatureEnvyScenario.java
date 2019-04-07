package net.sf.eclipses.sample.tests;

public class FeatureEnvyScenario implements Interface1{
	
	ClassB b1;
	ClassB b2;
	ClassB b3;
	
	public void A() {
		
		b1 = new ClassB();
		b1.display();
	}
	
	public static void main(String[] args) {
		FeatureEnvyScenario a = new FeatureEnvyScenario(); 
		a.A();
	}
	

}
