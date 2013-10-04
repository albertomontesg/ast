import java.util.*;
import java.lang.Exception;
import java.io.*;

public class ParenthesisReader {
	public static void main(String[] args) throws IOException {
		Deque<Character> stack = new ArrayDeque<Character>();
		DataInputStream in = null;
        DataOutputStream out = null;
        char[] chars = new char[1024];
        int pos = 0;
        char c = (char) 0;
        
        try {
			// Initialize with the standard Input/OutputStream from computer: keyboard and console.
            in = new DataInputStream(System.in);
            out = new DataOutputStream(System.out);
            
            while((c = in.readChar()) != -1) chars[pos++] = c;
        } catch (EOFException e) {
        	
        } finally {
        	int size = pos+1;
        	try {
	        	for (int i = 0; i < size; i++) {
	        		c = chars[i];
	        		if (c == '[' || c == '(' || c == '{')
						stack.push(c);
	        		else if (c == ']' || c == ')' || c == '}') {
						char d = stack.pop();
						// dismatch
						if ( c != d) {
							System.out.println("Left parenthesis " + c + " don't match with " + d + ".");
						}
					}
	        	}
        	} catch(EmptyStackException e) {
    			// catch the Stack was empty exception
            	if (e instanceof EmptyStackException) {
    	        	int aux = ((int)c)%2;
    	        	char d = (char) (c - 1 - aux);
    	        	System.out.println("Missing left parenthsis " + d + ".");
            	}
            	if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
    		} finally {
    			// look if elements remain in the Stack
    			if (stack.size() == 0) System.out.println("OK");
    			else if (stack.size() > 3) System.out.println("Too many nested parentheses.");
    			else  {
    				c = stack.pop();
    				int aux = ((int)c)%2;
    				char d = (char) (c + 1 + aux);
    				System.out.println("Missing right parentheses " + d + ".");
    			}
    		}
        }
        
        
		
	}
	
	
}


