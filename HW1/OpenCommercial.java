/* OpenCommercial.java */

import java.net.*;
import java.io.*;

/**  A class that provides a main function to read five lines of a commercial
 *   Web page and print them in reverse order, given the name of a company.
 */

class OpenCommercial {

  /** Prompts the user for the name X of a company (a single string), opens
   *  the Web site corresponding to www.X.com, and prints the first five lines
   *  of the Web page in reverse order.
   *  @param arg is not used.
   *  @exception Exception thrown if there are any problems parsing the 
   *             user's input or opening the connection.
   */
  public static void main(String[] arg) throws Exception {

    BufferedReader keyboard;
    String inputLine;

    keyboard = new BufferedReader(new InputStreamReader(System.in));

    System.out.print("Please enter the name of a company (without spaces): ");
    System.out.flush();        /* Make sure the line is printed immediately. */
    inputLine = keyboard.readLine();

    /* Replace this comment with your solution.  */
    URL u = new URL("https://www." + inputLine + ".com/");
    // InputStream Object: read raw data from some source (here is web), but don't format the data
    InputStream ins = u.openStream();
    // InputStreamReader Object: compose the raw data into charactors
    InputStreamReader isr = new InputStreamReader(ins);
    // BufferedReader Object: compoase the characters into entire lines of text
    BufferedReader contents = new BufferedReader(isr);

    String[] headFive = new String[5];
    for (int i = 0; i < 5; i++) {
    	headFive[i] = contents.readLine();
    }
    
    for (int i = 4; i >= 0; i--) {
    	System.out.println(headFive[i]+"\n");
    }
  }
}
