import java.io.*;

public class C02_SimpleIO {
	public static void main (String[] arg) throws Exception {
		// InputStream Object: read raw data from some source (here is System.in), but don't format the data
		// InputStreamReader Object: compose the raw data into characters
		// BufferedReader Object: compoase the characters into entire lines of text
		BufferedReader keybd = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(keybd.readLine());
	}
}
