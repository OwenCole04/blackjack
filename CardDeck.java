//Owen Cole
//Card Deck class
import java.util.*;
public class CardDeck
{
   private Stack<Card> deck;
   
      
   public CardDeck()//Constructor - generates a stack of 52 cards in random order
   {
      deck = new Stack<Card>();
      
      //generates a sorted arraylist of all cards to be used in the shuffled deck
      List<Card> notShuf = new ArrayList<Card>();
      String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
      for (String s : suits)
      {
         for (int i = 1; i <= 13; i++)
         {
            notShuf.add(new Card(s, i));
         }
      }

      //randommly selects cards from sorted ArrayList and pushes them to the stack
      while (notShuf.size() != 0)
      {
         deck.push(notShuf.remove((int)(Math.random() * notShuf.size())));
      }
   }
   
   public Card draw()//draws a card and removes it from the deck
   {
      return deck.pop();
   }
   
   
   public int size()//returns size of deck stack
   {
      return deck.size();
   }
   
   public String toString()//returns string of card
   {
      String rtn = "";
      Stack<Card> copy = (Stack)deck.clone();
      while (!copy.isEmpty())
      {
         rtn += copy.pop() + ", ";
      }
      
      return rtn;
   }
   
   
   
   
}