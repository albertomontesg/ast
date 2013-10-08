import java.util.*;
import java.lang.Exception;
import java.io.*;

/**
 * @author Alberto Montes
 * @subject AST
 * @exercise Practica2: Stacks // Apartat 1
 * Parentesis balancejats
 */
public class ParenthesesReader {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = null;
        DataOutputStream out = null;
        
        // Create an item from our self-made class Stack
        Stack<Character> stack = new Stack<Character>();
        
        // Some auxiliar variables
        String text = "";
        String line;
        char c = (char) 0;
        boolean ok = true;
        
        try {
			// Initialize with the standard Input/OutputStream from computer: keyboard and console.
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new DataOutputStream(System.out);
            
            // Read the input text and save it into a string until End Of File
            while ((line = br.readLine()) != null) text += line;
            
        } catch (Exception e) {
        	// Catch any I/O Exception
        } finally {
        	int size = text.length();
        	try {
            	// Reads the string finding all the parentheses
	        	for (int i = 0; i < size; i++) {
	        		c = text.charAt(i);
	        		// If it is a left one, push it to the stack
	        		if (c == '[' || c == '(' || c == '{')
						stack.push(c);
	        		// If it is a right one, pop the last one and compare it
	        		else if (c == ']' || c == ')' || c == '}') {
						char d = stack.pop();
						
						// Convert the right parentheses into the left one to compare
						int aux = 0;
	    				if ( (((int)c) % 5) != 1) 
	    					aux = 1;
	    	        	char dd = (char) (c - 1 - aux);
	    	        	
						// If there is a dismatch show the message
						if ( dd != d) {
							System.out.println("Left parentheses " + d + " doesn't match with right parentheses" + c + ".");
							ok = false;
						}
					}
	        	}
        	} catch(Exception e) {
    			// Catch the Stack was empty exception
            	if (e instanceof EmptyStackException) {
    	        	// Convert the right parentheses into the left one to show which one was missing
            		int aux = 0;
    				if ( (((int)c) % 5) != 1) 
    					aux = 1;
    	        	char d = (char) (c - 1 - aux);
    	        	System.out.println("Missing left parentheses " + d + ".");
    	        	ok = false;
            	}
    		} finally {
    			// Looks if elements remain in the Stack
    			if (stack.count() > 3) System.out.println("Too many nested parentheses.");
    			else if (stack.count() != 0){ // Show the right parentheses missing
    				c = stack.pop();
    				int aux = ((int)c)%2;
    				char d = (char) (c + 1 + aux);
    				System.out.println("Missing right parentheses " + d + ".");
    			}
    			// Print OK in the case all the parentheses match
    			else if (stack.count() == 0 && ok) System.out.println("OK");
    		}
        	try {
        		// Finally close the streams
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


