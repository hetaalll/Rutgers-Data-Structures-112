package apps;

import java.util.StringTokenizer;

import structures.Stack;

public class evaluate {
    public static final String delims = " \t*+-/()[]";

	
	public static void main(String args[]){
		System.out.println("Answer: " + evaluate1("4-(5+8)"));
	}
	
	
	public static float evaluate1(String expr){
		
	Stack <Float> variables = new Stack <Float>();
	Stack <Character> operations = new Stack <Character>();
	Stack <String> temp = new Stack <String>(); 
	StringTokenizer st = new StringTokenizer(expr,delims,true);
	String subexpr = "";
	float subans = 0;

	 while (st.hasMoreTokens()) {
		 String token = st.nextToken();
		 System.out.println(token);
		 
		 
		 if(token.equals("(") || token.equals("[")){
		 while (!(token.equals(")") || token.equals("]"))){
  if ((token.charAt(0) >= 'a' && token.charAt(0) <= 'z') || (token.charAt(0) >= 'A' && token.charAt(0) <= 'Z' || token.matches("[0-9]+"))){   		 
			 temp.push(String.valueOf(token.charAt(0))); }
		 }
				subexpr += temp.pop(); 
				System.out.println("subexpr: " + subexpr); 
				subans = evaluate1(subexpr);
				variables.push(subans);
		 }
		 
		 if(token.matches("[0-9]+")){
			 variables.push(Float.valueOf(token));
		 } 
			 else if ( token.charAt(0)=='+' ||token.charAt(0)=='/' ||token.charAt(0)=='*' ||token.charAt(0)=='-' ){
			 operations.push(token.charAt(0));

		 }
		 
		 if(variables.size() == 2){
			// while(!(variables.isEmpty() || operations.isEmpty())){
					float num2 = variables.pop();
					float num1 = variables.pop();
					char op = operations.pop();
					
					System.out.println("num1: " + num1);
					System.out.println("op: " + op);
					System.out.println("num2: " + num2);
					
					switch(op){
					case '+' : variables.push(num1+num2); break;
					case '-' : variables.push(num1-num2); break;
					case '/' : variables.push(num1/num2); break;
					case '*' : variables.push(num1*num2); break;
					//}
			 } 
		 }
	 }
	
	 
	return variables.pop();
	}
}
	
	

