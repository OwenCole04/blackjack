//Owen Cole
//Hand Class
import java.util.*;
public class Hand
{
   //Uses a queue so that the first ace in a hand1 can be put at the
   //back initially while the rest of the hand1 is calculated, allowing for
   //the proper value of that ace to be calculated (since it could be 1 or 11)
   private Queue<Card> hand;
   private int value;
   
   public Hand (Card a, Card b)//constructor
   {
      hand = new LinkedList<Card>();
      add(a);
      add(b);
   }
   
   public void add(Card a)//adds a card to the hand1
   {
      hand.add(a);
      value = calc();
   }
   
   public int getValue()//returns the value of the hand
   {
      return value;
   }
   
   public Card getFirstCard()//returns the card at the front of the queue
   {
      return hand.peek();
   }
   
   //calculates the value of the hand1 using blackjack values (face cards 10, aces 1 or 11)
   private int calc()
   {  
      Queue<Card> hand1 = new LinkedList<Card>();
      //method I found to copy a queue
      //https://stackoverflow.com/a/22982325
      Iterator<Card> it = hand.iterator();
      while(it.hasNext())
      {
         hand1.add(it.next());
      }
      //end of borrowed method---------
      
      Card aceRef = null; //reference of first ace drawn of deck(so that it may be put at end of queue)
      boolean foundAce = false;
      int value = 0;
      
      while (!hand1.isEmpty())
      {
         if (hand1.peek().getRank() == 1 && foundAce == false)//moves first ace found to end of queue
         {
            aceRef = hand1.remove();
            hand1.add(aceRef);
            foundAce = true;
         }
         
         else if (hand1.peek().equals(aceRef))//this will occur once the first ace is the only card remaining
         {
            if (value + 11 > 21)
               value += 1;
            else
               value += 11;
            hand1.remove();//removes last card from deck -- loop end
         }
         
         else if (hand1.peek().getRank() > 10)//for face cards
         {
            hand1.remove();
            value += 10;
         }
         
         else
            value += hand1.remove().getRank();//for numbered cards or second aces
      }
      
      return value;
   }
   
   public String toString()//returns a string of the cards in hand
   {
      String rtn = "";
      Queue<Card> copy = new LinkedList<Card>();
      
      //method I found to copy a queue
      //https://stackoverflow.com/a/22982325
      Iterator<Card> it = hand.iterator();
      while(it.hasNext())
      {
         copy.add(it.next());
      }
      //end of borrowed method--------------
      
      while (!copy.isEmpty())
      {
         rtn += copy.remove() + ", ";
      }
      
      rtn = rtn + "| value: " + value;
      return rtn;
   }
}

         
         
         