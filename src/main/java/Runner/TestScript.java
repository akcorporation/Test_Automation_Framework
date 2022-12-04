package Runner;
import Pages.Login;

public class TestScript {
	public static String testcase_id = "Demo_20_3456";
	
	public static void main(String args[])
	{
		BaseClassImplement.PropImplementation();
//		
			try {
				Login.login();
				Login.logout();
			} catch (InterruptedException e) {
				System.out.println("Getting InterruptedException in Login Page :" + e.getMessage());
			}
			
	}
	
	
	
}