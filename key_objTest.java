import java.io.FileNotFoundException;
import java.util.ArrayList;
public class key_objTest {

	public static void main(String[] args) throws FileNotFoundException 
	{
		System.out.println("Testing pulling data from file");
		ArrayList<String> testData = new ArrayList<String>();
		testData = file_manager.readToArray("stringConstructorTestData.txt");
		
		if(testData.get(0).isEmpty())
		{System.out.println("Failure");}
		else
		{
			System.out.println("Success");
			System.out.println("Test data: ");
			System.out.println(testData.get(0).toString());
		}
		
		System.out.println("------------------------------------------------");
		System.out.println("Testing object creation");
		key_obj obj = new key_obj(testData.get(0));
		
		if(obj.toString().isEmpty())		
		{System.out.println("Failure");}
		else
		{
			System.out.println("Success");
			System.out.println("Object Data:");
			System.out.println(obj.toString());
		}
		
		System.out.println("------------------------------------------------");
		System.out.println("Testing file to object symmetry");
		
		if(obj.toString().equalsIgnoreCase(testData.get(0)))
		{
			System.out.println("Success");
			System.out.println("Object contents:");
			System.out.println(obj.toString());
			System.out.println("File contents:");
			System.out.println(testData.get(0));
		}
		else{System.out.println("Failure");}
		
		System.out.println("------------------------------------------------");
		System.out.println("Testing .getXYZ methods");
		System.out.println("var# KeyID: "+obj.getKeyID());
		System.out.println("var# isPhysical: "+obj.getIsPhysicalKey());
		System.out.println("var# isMasterKey: "+obj.getIsMasterKey());
		System.out.println("var# isavailableForIssue: "+obj.getIsAvailableForIssue());
		System.out.println("var# keyType: "+obj.getKeyType());
		System.out.println("var# pin: "+ obj.getPin());
		System.out.println("var# assignedTo: "+obj.getAssignedTo());
		System.out.println("var# assignedToJobTitle: "+ obj.getAssignedToJobTitle());
		System.out.println("var# employeeID: "+obj.getEmployeeID());
		System.out.println("var# authorizedBy: "+obj.getAuthorizedBy());
		System.out.println("var# authorizedByTitle: "+obj.getAuthorizedByTitle());
		System.out.println("var# dateAssigned: "+obj.getDateAssigned());
		System.out.println("var# dateReturned: "+obj.getDateReturned());
		System.out.println("var# reasonAssigned: "+ obj.getReasonAssigned());
		System.out.println("var# entryCreationDate: "+obj.getEntryCreationDate());
		System.out.println("var# locksmithName: "+obj.getLocksmithName());
		System.out.println("var# accountedFor: " +obj.getAccountedFor());
		System.out.println("var# reasonLost: "+obj.getReasonLost());
		System.out.println("var# dateLost: "+obj.getDateLost());
		ArrayList<String> keyHistory = obj.getKeyHistory();
		for(int i = 0; i < keyHistory.size(); i++)
		{
			System.out.println("var# keyHistory["+i+"]: "+ keyHistory.get(i));
		}
		ArrayList<String> opensDoors = obj.getOpensDoors();
		for(int i = 0; i < opensDoors.size(); i++)
		{
			System.out.println("var# opensDoors["+i+"] :"+opensDoors.get(i));
		}
		
		System.out.println("----------------------------------------------");
		System.out.println("Testing core methods");
		System.out.println("Testing assign() method");
		String preToString = obj.toString();
		obj.assign("kam", "tech", "010101", "chris", "tech", "06122018", "Kam needed Key");
		String postToString = obj.toString();
		System.out.println("Test Data");
		System.out.println("DataSet 1:" + preToString);
		System.out.println("Dataset 2:" + postToString);
		
		if(preToString.equalsIgnoreCase(postToString))
		{
			System.out.println("Test failed at Assign() method");
		}
		else
		{
			System.out.println("Assign method successfully tested");
		}
		
		System.out.println("----------------------------------------------");		
		System.out.println("Testing returnKey and archive");
		int countCheck = obj.getKeyHistory().size();
		obj.returnKey();
		keyHistory = obj.getKeyHistory();
		for(int i = 0; i < keyHistory.size(); i++)
		{
			System.out.println("var# keyHistory element["+i+"] : "+keyHistory.get(i));
		}
		if(countCheck>= keyHistory.size())
		{
			System.out.println("Test Failed returnKey / archive methods");
		}
		else
		{
			System.out.println("Success");
		}
		
		System.out.println("Test Completed");
	}

}
