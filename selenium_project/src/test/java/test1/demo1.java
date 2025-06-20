package test1;

import org.testng.annotations.Test;

public class demo1 {

	@Test(groups = {"Regression","Smoke"})
	public void demo() {
		System.out.println("demo");
		String url = System.getProperty("url");
		String browser = System.getProperty("browser","chrome");
		String username = System.getProperty("username");
		String password = System.getProperty("password");
        System.out.println(url);
        System.out.println(browser);
        System.out.println(username);
        System.out.println(password);

	}
	
	@Test(groups = "Regression")
	public void demo2() {
		System.out.println("demo1");
	
}
}
