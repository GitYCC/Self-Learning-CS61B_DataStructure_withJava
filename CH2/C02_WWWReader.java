import java.net.*;
import java.io.*;

public class C02_WWWReader {
	public static void main (String[] arg) throws Exception{
		URL u = new URL("https://www.google.com.tw/");
		// InputStream Object: read raw data from some source (here is web), but don't format the data
		InputStream ins = u.openStream();
		// InputStreamReader Object: compose the raw data into charactors
		InputStreamReader isr = new InputStreamReader(ins);
		// BufferedReader Object: compoase the characters into entire lines of text
		BufferedReader contents = new BufferedReader(isr);
		System.out.println(contents.readLine());
		
	}
}
