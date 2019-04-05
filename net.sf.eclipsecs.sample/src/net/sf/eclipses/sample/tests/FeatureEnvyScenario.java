package net.sf.eclipses.sample.tests;

public class FeatureEnvyScenario {
	
	ClassB b1;
	ClassB b2;
	ClassB b3;
	
	public void show() {
		b1 = new ClassB();
		b1.display();
	}
	
	public static void main(String[] args) {
		FeatureEnvyScenario a = new FeatureEnvyScenario(); 
		a.show();
	}
	

}
