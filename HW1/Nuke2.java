import java.io.*;

public class Nuke2 {
	public static void main(String[] arg) throws Exception {
		// System.in(InputStream object) read raw data from keyboard but doesn't format the data
		// InputStreamReader compose the raw data into characters
		// BufferedReader compose the characters into entire lines of text
		BufferedReader keybd = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Please Input Here:");
		String line = keybd.readLine();
		for (int i = 0; i < line.length(); i++) {
			if (i==1){ continue; }
			
			System.out.print(line.charAt(i));
		}
		System.out.print("\n");
	}
}