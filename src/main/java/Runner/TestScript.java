package Runner;
import java.util.List;

import Pages.Login;
import Pages.createProfile;

public class TestScript {
	public static int Login_count = 0;
	
	public static void main(String args[]) {
		StarterFunction();
	}
	
	public static void StarterFunction() {
		
		BaseClassImplement.PropImplementation();
		List<String> TestDataSheet = BaseClassImplement.TestSheetHandler();
		
//		Fetching Test data sheet name and sending request to that page
		for(String list : TestDataSheet)
		{
			switch(list) {
			case "Create_Profile": createProfile.createprofile(); 
			break;
			case "Update_Profile": BaseClassImplement.PropImplementation();
			default: System.out.println("Sheet didn't found please check sheet name once " + list);  break;
			}
		}
		Login.logout();
	}
		
}