import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

public class file_manager 
{

	public static void writeFromArray(String path, ArrayList<String> dataToWrite) throws FileNotFoundException
	{
		PrintWriter pw = new PrintWriter(path);
		for(int i = 0; i < dataToWrite.size(); i++)
		{
			pw.println(dataToWrite.get(i));
		}
		pw.close();
	}
	
	public static ArrayList<String> readToArray(String path) throws FileNotFoundException
	{
		ArrayList<String> temp = new ArrayList<String>();
		File p = new File(path);
		Scanner scan = new Scanner(p);
		while(scan.hasNextLine())
		{
			String data = scan.nextLine();
			temp.add(data);
		}
		scan.close();
		return temp;
	}
}
