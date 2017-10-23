package search;

public class CheckMethod {

	public static void main(String[] args) {
		
		System.out.print(getKeyWord("hEtal+..."));

	}
	
	public static String getKeyWord(String word) {
		
		char ch = 0;
		String tempWord;
		//debug statement
		//System.out.println("Word: [" + word + "]");
		tempWord = word;
		if(tempWord == null){
			//debug statement
			//System.out.println("Empty word");
			return null;
		}

		//convert to lowercase 
		//tempWord.trim().toLowerCase();
		
		tempWord = tempWord.toLowerCase();
		
		//debug statement
		//System.out.println("Word after converting to lower case: " + word + " tempWord = " + tempWord);

		//remove punctuation
		tempWord = tempWord.replaceAll("[.,?!:;]+$", "");
		//System.out.println("Word after removing punctuation: " + tempWord);

		//check if noise word
	//	if(noiseWords.containsKey(tempWord)){
			//debug statement
			//System.out.println(word + " = Noise word");
		//	return null;
	//	}

		//check if all letters
		for(int i = 0; i < tempWord.length(); i++){
			ch = tempWord.charAt(i);
			if(!(Character.isLetter(ch))){
				//debug statement
				//System.out.println("Unacceptable word");
				return null;
			}
		}
		//debug statement
		//System.out.println("Acceptable word:" + tempWord);
		return tempWord;
	}
	


}
