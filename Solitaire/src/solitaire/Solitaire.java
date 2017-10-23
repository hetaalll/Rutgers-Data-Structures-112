package solitaire;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

/**
 * This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 * 
 * @author RU NB CS112
 */
public class Solitaire {
	
	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;
	
	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	public void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}
		
		// shuffle the cards
		Random randgen = new Random();
 	        for (int i = 0; i < cardValues.length; i++) {
	            int other = randgen.nextInt(28);
	            int temp = cardValues[i];
	            cardValues[i] = cardValues[other];
	            cardValues[other] = temp;
	        }
	     
	    // create a circular linked list from this deck and make deckRear point to its last node
	    CardNode cn = new CardNode();
	    cn.cardValue = cardValues[0];
	    cn.next = cn;
	    deckRear = cn;
	    for (int i=1; i < cardValues.length; i++) {
	    	cn = new CardNode();
	    	cn.cardValue = cardValues[i];
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
	    }
	}
	
	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	public void makeDeck(Scanner scanner) 
	throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
		    cn.cardValue = scanner.nextInt();
		    cn.next = cn;
		    deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
	    	cn.cardValue = scanner.nextInt();
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
		}
	}
	
	/**
	 * Implements Step 1 - Joker A - on the deck.
	 */
	void jokerA() {
		
		CardNode prev = deckRear;
			for(CardNode current = deckRear.next; current != deckRear; current = current.next){
					//27 is second to last element
				if(current.cardValue == 27 && current.next == deckRear){
					CardNode a = current;
					CardNode head = deckRear.next;
					CardNode tail = deckRear;
					prev.next = current.next;
					tail.next = a;
					deckRear = a;
					a.next = head;
					return;
				}
					//27 is the last element
				else if(current.next.cardValue == 27 && current.next == deckRear){
					CardNode a = deckRear;
					current.next = deckRear.next;
					deckRear = current.next;
					CardNode b = deckRear.next;
					deckRear.next = a;
					a.next = b;
					return;
				}
				
				else if (current.cardValue == 27){
				CardNode a = current;
				CardNode b = current.next.next;
				prev.next = current.next;
				prev.next.next = a;
				a.next = b;
				return;
			}
				
			prev = prev.next;
		}
		  
	}
	
	/**
	 * Implements Step 2 - Joker B - on the deck.
	 */
	void jokerB() {
	   
		CardNode prev = deckRear;
		for(CardNode current = deckRear.next; current != deckRear; current = current.next){
			
				
						
				//28 is the second to last element
			if(current.cardValue == 28 && current.next == deckRear){
				int a = current.cardValue;
				int b = deckRear.cardValue;
				int c = deckRear.next.cardValue;
				current.cardValue = b;
				deckRear.cardValue = c;
				deckRear.next.cardValue = a;
				return;	
			}
			
				//28 is the last element
			else if(current.next.cardValue == 28 && current.next == deckRear){
				int a = deckRear.cardValue;
				int b = deckRear.next.cardValue;
				int c = deckRear.next.next.cardValue;
				deckRear.cardValue = b;
				deckRear.next.cardValue = c;
				deckRear.next.next.cardValue = a;
				return;	
				
			}		
			else if(current.cardValue == 28){
				int a = current.cardValue;
				int b = current.next.cardValue;
				int c = current.next.next.cardValue;
				current.cardValue = b;
				current.next.cardValue = c;
				current.next.next.cardValue = a;
				return;			
			}

			prev = prev.next;
		}
	}
	
	/**
	 * Implements Step 3 - Triple Cut - on the deck.
	 */
	void tripleCut() {
		
		CardNode prev = deckRear.next;
		CardNode current = deckRear.next.next;
		
	// no cards before the first joker
		    if(deckRear.next.cardValue == 27 || deckRear.next.cardValue == 28){
	    	for(current = deckRear.next.next; current != deckRear; current = current.next){
	    		if(deckRear.cardValue == 27 || deckRear.cardValue == 28){
	    			return;
	    		} 
	    		
	    		else if (current.cardValue == 27 || current.cardValue == 28){
	    			CardNode a = current;
	    			CardNode b = current.next;
	    			deckRear = a;
	    			deckRear.next = b;
	    			return;
	    		}
	    		else {
	    			prev = prev.next;
	    		}
	    	}
	    }
		    
	    // no cards after the second
	    else if(deckRear.cardValue == 27 || deckRear.cardValue == 28){
	    	prev = deckRear;
	    	current = deckRear.next;
	    	
	    	for(current = deckRear.next; current != deckRear; current = current.next){
	    		if(deckRear.next.cardValue == 27 || deckRear.next.cardValue == 28){
	    			return;
	    		}
	    		else if(current.cardValue == 27 || current.cardValue == 28){
	    			CardNode a = deckRear.next;
					CardNode b = current;
					CardNode tmp = prev;
					deckRear.next = a;
					deckRear = tmp;
					deckRear.next = b;
					return;
	    		}
	    		else {
	    			prev = prev.next;
	    		}
	    	}
	    }
	    
	    else {
	    	prev = deckRear;
	    	current = deckRear.next;
	    	
	    	while(current !=deckRear){
	    		if(current.cardValue == 27 || current.cardValue == 28){
	    			CardNode joker1 = current;
	    			CardNode tmp = current.next;
	    			while(tmp!=deckRear.next){
	    				if(tmp.cardValue==27 || tmp.cardValue==28){
	    					CardNode joker2 = tmp;
	    					CardNode tmp2 = tmp.next;
	    					CardNode head = deckRear.next;
	    					deckRear.next = joker1;
	    					joker2.next = head;
	    					deckRear = prev;
	    					deckRear.next = tmp2;
	    					return;
	    				}
	    				else {
	    					tmp = tmp.next;
	    				}
	    			}
	    		}
	    	
	    	else {
	    		prev = prev.next;
	    		current = current.next;
	    		}
	    	}
	    }
	}
	
	/**
	 * Implements Step 4 - Count Cut - on the deck.
	 */
	void countCut() {
		
		int last;
		int count = 1;
		if(deckRear.cardValue == 28){
			last = 27;
		}else{
			last = deckRear.cardValue;
		}
		
		CardNode head = deckRear.next;
		CardNode prev = deckRear;
		CardNode current = deckRear.next;
			
			while(count<=last){
				if(count == last){
					if(current.next == deckRear){
						return;
					}
					CardNode a = current;
					CardNode b = current.next;
					
				for(CardNode tmp = current.next; tmp!= deckRear; tmp = tmp.next){
					if(tmp.next==deckRear){
						tmp.next = head;
						a.next = deckRear;
						deckRear.next = b;
						return;
						}
					}
				}
				current = current.next;
				prev = prev.next;
				count++;
			}
		
	}
	
	/**
	 * Gets a key. Calls the four steps - Joker A, Joker B, Triple Cut, Count Cut, then
	 * counts down based on the value of the first card and extracts the next card value 
	 * as key. But if that value is 27 or 28, repeats the whole process (Joker A through Count Cut)
	 * on the latest (current) deck, until a value less than or equal to 26 is found, which is then returned.
	 * 
	 * @return Key between 1 and 26
	 */
	int getKey() {
		int key = -1;
		int firstCard = deckRear.next.cardValue;
		
		if(firstCard == 28){
			firstCard = 27;
		}
		
		CardNode current = deckRear.next;
		int count = 1;
		
		while(current != deckRear){
			if(count == firstCard){
				if(current.next.cardValue == 27 || current.next.cardValue == 28){
					jokerA();
					jokerB();
					tripleCut();
					countCut();
					current = deckRear;
					count = 0;
					
					firstCard = deckRear.next.cardValue;
					
					if(firstCard == 28){
						firstCard = 27;
					}
				}
				else {
					key = current.next.cardValue;
					return key;
				}
			}
			current = current.next;
			count++;
		}
	    return key;
	}
	
	/**
	 * Utility method that prints a circular linked list, given its rear pointer
	 * 
	 * @param rear Rear pointer
	 */
	private static void printList(CardNode rear) {
		if (rear == null) { 
			return;
		}
		System.out.print(rear.next.cardValue);
		CardNode ptr = rear.next;
		do {
			ptr = ptr.next;
			System.out.print("," + ptr.cardValue);
		} while (ptr != rear);
		System.out.println("\n");
	}

	/**
	 * Encrypts a message, ignores all characters except upper case letters
	 * 
	 * @param message Message to be encrypted
	 * @return Encrypted message, a sequence of upper case letters only
	 */
	public String encrypt(String message) {	
		String answer = "";
		String input = message.toUpperCase();
		
		for(int i = 0; i < input.length(); i++){
			
			if(!Character.isLetter(input.charAt(i)))
				continue;
			else{
			char letter = input.charAt(i);
			int alphabet = letter - 'A' +1;
			jokerA();
			jokerB();
			tripleCut();
			countCut();
			
			int key = getKey();
			int sum = alphabet + key;
			
			if(sum > 26){
				sum = sum - 26;
			}
			
			letter = (char)(sum - 1 + 'A');
			answer = answer + letter;
		}
		}
		
	    return answer;
	}
	
	/**
	 * Decrypts a message, which consists of upper case letters only
	 * 
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {	
		String answer = "";
		String input = message.toUpperCase();
		for(int i = 0; i < input.length(); i++){
			if(!Character.isLetter(input.charAt(i)))
				continue;
			else{
			
			char letter = input.charAt(i);
			int alphabet = letter - 'A' +1;
			jokerA();
			jokerB();
			tripleCut();
			countCut();
			
			int key = getKey();
			int sum = alphabet - key;
			
			if(sum<=0){
				sum = sum + 26;
			}
			letter = (char)(sum-1+'A');
			answer = answer + letter;
		}
		}
	    return answer;
	}
}
