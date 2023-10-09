//Owen Cole
//Card Class
public class Card
{
   private int rank;
   private String suit;
   
   //assumes that when the constructor is called that arguments
   //precondition that the arguments passed will be rank 1-13 and suit of spades, hearts, clubs, diamonds
   public Card(String s, int r)
   {
      rank = r;
      suit = s;
   }
   
   public int getRank()//returns rank
   {
      return rank;
   }
   
   public String getSuit()//returns suit
   {
      return suit;
   }
   
   public String toString()//returns string of card
   {
      if (rank == 1)
         return ("Ace of " + suit);
      if (rank == 11)
         return ("Jack of " + suit);
      if (rank == 12)
         return ("Queen of " + suit);
      if (rank == 13)
         return ("King of " + suit);
      return (rank + " of " + suit);
   }
}