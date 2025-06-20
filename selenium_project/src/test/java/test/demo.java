package test;

import org.testng.annotations.Test;

public class demo {
	
	@Test(groups = "Regression")
	public void test() {
		System.out.println("test");
	}
	
	@Test(groups = "Regression")
	public void test1() {
		System.out.println("test1");
	}

}
