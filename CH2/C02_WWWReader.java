import java.net.*;
import java.io.*;

public class C02_WWWReader {
	public static void main (String[] arg) throws Exception{
		URL u = new URL("https://www.google.com.tw/");
		InputStream ins = u.openStream();
		InputStreamReader isr = new InputStreamReader(ins);
		BufferedReader contents = new BufferedReader(isr);
		System.out.println(contents.readLine());
		
	}
}
