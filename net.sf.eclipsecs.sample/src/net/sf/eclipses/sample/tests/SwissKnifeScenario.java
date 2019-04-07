package net.sf.eclipses.sample.tests;

public class SwissKnifeScenario implements Interface1,Interface2,Interface3 {
	
	private int a;
	
	public void A() { a=0; }
	
	public void B() {a=10;}
	
	public void C() {
		System.out.println("a = " + a);	
	}
	
	public static void main() {
		
		SwissKnifeScenario a1 = new SwissKnifeScenario();
		a1.A();
		a1.B();
		a1.C();
	}

}
