package net.sf.eclipses.sample.tests;

public class DuplicateCodeTestScenario {

	public void a() {
		int i = 1;
		int j  = 2;
		System.out.println(i + j);
	}
	
	public void b() {
		int i = 1;
		int j  = 2;
		System.out.println(i + j);
	}
}
