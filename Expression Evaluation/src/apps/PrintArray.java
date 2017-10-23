package apps;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class PrintArray {
    public static final String delims = " \t*+-/()[]";
    
    

	public static void main(String[] args) {
		
		ArrayList<ScalarSymbol> scalars =new ArrayList<ScalarSymbol>();  
		
		ArrayList<ArraySymbol> arrays = new ArrayList<ArraySymbol>();
		
		String expr = "arrayA[arrayA[9]*(arrayA[3]+ 2)+ 1 ]-varx*varx";
		
		for (int i = 0; i < expr.length(); i++){
			if (Character.isLetter(expr.charAt(i))) {
				String expression = "";
				

				while (i < expr.length() && Character.isLetter(expr.charAt(i))){
					expression += expr.charAt(i);
					i++;
				}
								
			
				if (i < expr.length() && expr.charAt(i) == '[') {
					ArraySymbol as = new ArraySymbol(expression);
					if(!(arrays.contains(as))){
					arrays.add(as);}
				} else {
					ScalarSymbol ss = new ScalarSymbol(expression);
					if(!(scalars.contains(ss))){
						scalars.add(ss);  }
					
				}
			}
		}

	    System.out.println("Arrays: ");

    	for(int i = 0; i < arrays.size(); i++) {   
    	    System.out.print(arrays.get(i));

    	} 
		System.out.println();

		System.out.println();

	    System.out.println("Scalars: ");

    	for(int i = 0; i < scalars.size(); i++) {   
    	    System.out.print(scalars.get(i));

    	} 
    
	}
}