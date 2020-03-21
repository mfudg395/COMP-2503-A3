
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/** 
* COMP 2503 Winter 2020 Assignment 3 
* 
* This program reads a text file and compiles a list of the 
* words together with the frequency of the words.
* Use a BST for storing the words. 
* Words from a standard list of stop words are then deleted.
* 
* BSTs with alternative orderings are constructed to show the
* required output.
* 
* Updated Winter 2020
*/

public class A3 {
   /* The lists (trees) of words. Alphabetic, by Frequency 
      and by length. */
   private BST<Token> wordsByNaturalOrder = new BST<Token>();
   private BST<Token> wordsByFreqDesc = new BST<Token>(Token.CompFreqDesc);
   private BST<Token> wordsByLength = new BST<Token>(Token.CompLengthDesc);
   
   // there are 103 stopwords in this list
   private String[] stopwords = { 
         "a","about","all","am","an","and","any","are","as","at", 
         "be","been","but","by","can","cannot","could", "did", "do", "does", 
         "else", "for", "from", "get", "got", "had", "has", "have", "he", "her", 
         "hers", "him", "his", "how", "i", "if", "in", "into", "is", "it", 
         "its",  "like", "more", "me", "my", "no", "now", "not", "of", "on", 
         "one", "or", "our", "out", "said", "say", "says", "she", "so", "some",
         "than", "that", "thats", "the", "their", "them", "then", "there", "these", "they", "this", 
         "to", "too", "us", "upon", "was", "we", "were", "what", "with", "when", 
         "where", "which", "while", "who", "whom", "why", "will", "you", "your", "up", "down", "left", "right",
         "man", "woman", "would", "should", "dont", "after", "before", "im", "men"
   };

   private int totalwordcount = 0;
   private int stopwordcount = 0;

   private Scanner inp = new Scanner( System.in);

   public static void main( String[ ] args) {	
      A3 a3 = new A3();
      a3.run();
   }

   private void printResults() {
       System.out.println("Total Words: " + totalwordcount);
       System.out.println("Unique Words: " + wordsByNaturalOrder.size()); 
       System.out.println("Stop Words: " + stopwordcount);
       System.out.println();
       System.out.println("10 Most Frequent");
       
       int limit = 0;
       for (Token t : wordsByFreqDesc) {
    	   if (limit < 10) {
    		   System.out.println(t);
    	   }
    	   limit++;
       }

       limit = 0;
       System.out.println();
       System.out.println("10 Longest");
       for (Token t : wordsByLength) {
    	   if (limit < 10) {
    		   System.out.println(t);
    	   }
    	   limit++;
       }

       System.out.println();
       System.out.println("The longest word is " + wordsByLength.min());
       System.out.println("The average word length is " + avgLength());
       System.out.println();        
       System.out.println("All");

       /* TODO:
        * Use an iterator to traverse the wordsByNaturalOrder in-order, and print all elements in the tree
        */
       for (Token t : wordsByNaturalOrder) {
    	   System.out.println(t);
       }

       System.out.println();
       
       System.out.println("Alphabetic Tree: (Optimum Height: " + 
             optHeight(wordsByNaturalOrder.size()) + ") (Actual Height: " 
             + wordsByNaturalOrder.height(wordsByNaturalOrder.getRoot()) + ")");
       System.out.println("Frequency Tree: (Optimum Height: " + 
             optHeight(wordsByFreqDesc.size()) + ") (Actual Height: " 
             + wordsByFreqDesc.height(wordsByFreqDesc.getRoot()) + ")");
       System.out.println("Length Tree: (Optimum Height: " + 
             optHeight(wordsByLength.size()) + ") (Actual Height: " 
             + wordsByLength.height(wordsByLength.getRoot()) + ")");
   }
   
   /* Read the file and add words to the list/tree. 
    */
   private void readFile() {
      while (inp.hasNext()) {

         String word = inp.next().toLowerCase().trim().replaceAll("[^a-z]","");

         if (word.length() > 0) { 
        	Token token = new Token(word);
        	addToBST(token, wordsByNaturalOrder);
        	totalwordcount++;
         }
      }
   }

   /* Create the frequency and length lists. */
   private void createFreqLists() {
	   for (Token t : wordsByNaturalOrder) {
		   wordsByLength.add(t);
		   if (t.getCount() > 2) {
			   wordsByFreqDesc.add(t);
		   }
	   }
   }
   
   // Completed with help from Dominic Silvestre.
   private void addToBST(Token token, BST<Token> bst) {
	   for (Token t : bst) {
		   if (token.compareTo(t) == 0) {
			   t.incrCount();
			   return;
		   }
	   }
	   token.incrCount();
	   bst.add(token);
	   
   }

   /* Calculate the average length of words stored the wordsByNaturalOrder tree*/
   private int avgLength() {
	   int average = 0;
	   for (Token t : wordsByNaturalOrder) {
		   average += t.getWord().length();
	   }
	   return average / wordsByNaturalOrder.size();
   }

   /* Remove stop words from the tree. */
   private void removeStop() {
	   List<String> stopwordsList = Arrays.asList(stopwords); 
	   for (Token t : wordsByNaturalOrder) {
		   if (stopwordsList.contains(t.getWord())) {
			   wordsByNaturalOrder.delete(t);
			   stopwordcount++;
		   }
	   }
   }

   /* Calculate the optimal height for a tree of size n. 
      Round to an int. 
    */
   private int optHeight(int n) {
	     double h = Math.log(n+1) / Math.log(2) - 1;  
	     if (Math.round(h) < h)
	    	  return (int)Math.round(h) + 1;
	     else
	    	  return (int)Math.round(h);
   }

   /* Run the program. */
   private void run() {
      readFile();
      removeStop();
      createFreqLists();
      printResults();
   }
}