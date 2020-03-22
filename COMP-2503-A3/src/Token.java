import java.util.Comparator;

/** 
 * COMP 2503 Assignment 3
 * Originally written as a Word class in Winter 2016
 * by Alan Fedoruk
 * 
 * Updated Winter 2020
 * @author Maryam Elahi
 * 
 * Assignment by: Matthew Fudge
 * 
 * A Token object represents a word (string) and how many times it 
 * has occurred in a given text. 
*/

public class Token implements Comparable<Token> {

   // The word!
   private String word;
   // How many times it has occurred
   private int count; 
   
   /** Create a word with string w and count 0.
   */
   public Token( String w) {
      this.word = w;
      this.count = 0;
   }

   /** Comparator to sort from high to low frequency. 
    */
   public static Comparator<Token> CompFreqDesc = new Comparator<Token>() {

      public int compare( Token w1, Token w2) 
      {

         int f1 = w1.getCount();
         int f2 = w2.getCount();
         if ( f2 - f1 == 0) 
            return w1.compareTo( w2);
         else
            return f2 - f1; 
      }
   };

   /** Comparator to sort from  low to high frequency. 
    */
   public static Comparator<Token> CompFreqAsc = new Comparator<Token>() {

      public int compare( Token w1, Token w2) 
      {

         int f1 = w1.getCount();
         int f2 = w2.getCount();

         if ( f1 - f2 == 0) 
            return w1.compareTo( w2);
         else
            return f1 - f2; 
      }
   };

   /** Comparator to sort from high to low word length. 
    */
   public static Comparator<Token> CompLengthDesc = new Comparator<Token>() {

      public int compare( Token w1, Token w2) 
      {
    	  int length1 = w1.getWord().length();
    	  int length2 = w2.getWord().length();
    	  
    	  if (length1 - length2 == 0)
    		  return w1.compareTo(w2);
    	  else
    		  return length2 - length1;
      }
   };

   /** Return the string representing this word. 
    */
   public String getWord() { 
      return word;
   }

   /** Return the number of times this word has occurred.
   */
   public int getCount() { 
      return count;
   }

   /** Increment the counter by 1. 
    */
   public void incrCount() { 
      count++; 
   }

   /** Return the length of the word. 
    */
   public int getLength() {
      return getWord().length();
   }

   /** Return a string representation of this word. 
    */
   public String toString() { 
      return getWord() + " : " + getLength() + " : " + getCount();
   }

   public int hashCode() {
      return getWord().hashCode();
   } 

   /** Checking if two tokens are equal.
    */
   public boolean equals( Object other) {
      if ( other == this) return true;
      if ( other == null) return false;
      if (this.getClass() != other.getClass()) return false;
      Token p = (Token)other;
      return this.getWord().equals( p.getWord());
   }
  
   /** The natural ordering for Words: alphabetic. 
    */
   public int compareTo( Token o) {
      if ( this.equals( o))
         return 0;
      else
         return this.getWord().compareTo( o.getWord()); 
   }
}
