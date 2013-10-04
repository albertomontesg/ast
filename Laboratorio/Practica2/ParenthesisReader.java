import java.util.*;
import java.lang.Exception;
import java.io.*;

public class ParenthesisReader {
	public static void main(String[] args) throws IOException {
		Deque<Character> stack = new ArrayDeque<Character>();
		BufferedReader br = null;
        DataOutputStream out = null;
        String text = "";
        String line;
        int pos = 0;
        char c = (char) 0;
        boolean ok = true;
        
        try {
			// Initialize with the standard Input/OutputStream from computer: keyboard and console.
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new DataOutputStream(System.out);
            
            while ((line = br.readLine()) != null) text += line;
            
        } catch (Exception e) {
        	
        } finally {
        	int size = text.length();
        	try {
	        	for (int i = 0; i < size; i++) {
	        		c = text.charAt(i);
	        		if (c == '[' || c == '(' || c == '{')
						stack.push(c);
	        		else if (c == ']' || c == ')' || c == '}') {
						char d = stack.pop();
						int aux = 0;
	    				if ( (((int)c) % 5) != 1) 
	    					aux = 1;
	    	        	char dd = (char) (c - 1 - aux);
						// dismatch
						if ( dd != d) {
							System.out.println("Left parenthesis " + d + " don't match with " + c + ".");
							ok = false;
						}
					}
	        	}
        	} catch(Exception e) {
    			// catch the Stack was empty exception
            	if (e instanceof NoSuchElementException) {
    	        	int aux = 0;
    				if ( (((int)c) % 5) != 1) 
    					aux = 1;
    	        	char d = (char) (c - 1 - aux);
    	        	System.out.println("Missing left parenthsis " + d + ".");
    	        	ok = false;
            	}
    		} finally {
    			// look if elements remain in the Stack
    			if (stack.size() == 0 && ok) System.out.println("OK");
    			else if (stack.size() > 3) System.out.println("Too many nested parentheses.");
    			else if (stack.size() != 0){
    				c = stack.pop();
    				int aux = ((int)c)%2;
    				char d = (char) (c + 1 + aux);
    				System.out.println("Missing right parentheses " + d + ".");
    			}
    		}
        	try {
	        	if (br != null) {
	                br.close();
	            }
	            if (out != null) {
	                out.close();
	            }
        	} catch(Exception e) {}
        }
	}
	
}


