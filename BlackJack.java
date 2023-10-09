//Owen Cole
//Blackjack class (driver class)
import java.util.*;
import java.io.*;
public class  BlackJack
{
   //uses thread.sleep to improve console output timing for the user
   public static void main(String[] args) throws InterruptedException
   {
      double balance = 1000;
      Scanner sc = new Scanner(System.in);
      System.out.println("Balance: $" + balance + "\nWould you like to play Blackjack? (type 'yes' or 'no')");
      String run = sc.next();
      CardDeck deck = new CardDeck();
      
      //main program loop
      while (run.equals("yes") && balance > 0)
      {
         //shuffles(by referencing the variable to a new deck object once there is less than 40%(21 cards) of the deck remaining
         if (deck.size() <= 21)
         {
            deck = new CardDeck();
            System.out.println("Shuffling...");
            Thread.sleep(3000);
         }
         
         System.out.println(deck.size());
         
         //asks user what they want to bet
         boolean done = false;
         System.out.println("\nPlace bet: ");
         double bet = Double.parseDouble(sc.next());
         if (bet > balance)
         {
            bet = balance;
            System.out.println("Your bet has been maxed! Bet = $" + bet);
         }
         balance -= bet;
         
         //declares and initializes hand variables
         Hand user = new Hand(deck.draw(), deck.draw());
         Hand dealer = new Hand(deck.draw(), deck.draw());
         System.out.println("Your hand: " + user + "\nDealer's face up card: " + dealer.getFirstCard() + "\n");
         
         //round ends and user or dealer wins/loses accordingly if any initial hands have blackjack
         if (user.getValue() == 21 && dealer.getValue() == 21)//tie
         {
            System.out.println(dealer + "| dealer has blackjack");
            balance += bet;
            System.out.println("Push! No loss or payout");
            done = true;
         }
         else if (user.getValue() == 21)//user wins
         {
            System.out.println("Blackjack! Payout of $" + bet*.5);
            balance += bet*1.5;
            done = true;
         }
         else if (dealer.getValue() == 21)//dealer wins
         {
            System.out.println(dealer + "| dealer has blackjack");
            System.out.println("You lost! You lost your bet of $" + bet);
            done = true;
         }
         
         //user hits for additional cards in hand
         String hit = "hit";
         while (hit.equals("hit") && done == false)
         {
            System.out.println("type 'hit' to be given another card or 'stand' to finish");
            hit = sc.next();
            if (hit.equals("hit"))
            {
               user.add(deck.draw());
               System.out.println("Your hand: " + user);
            }
            if (user.getValue() > 21)//users hand goes over 21 and loses. round ends
            {
               System.out.println("Bust! You lost your bet of $" + bet);
               done = true;
            }
         }
         
         System.out.println("\nDealer's hand: " + dealer);
         //dealer hits while hand is below 17
         while (dealer.getValue() < 17 && done == false)
         {
            Thread.sleep(500);
            System.out.println("\nDealer hits");
            dealer.add(deck.draw());
            System.out.println("Dealer's hand: " + dealer);
            //dealer busts
            if (dealer.getValue() > 21)
            {
               System.out.println("Dealer busts! You Win! Payout of $" + bet*.5);
               balance += bet*1.5;
               done = true;
            }
         }
         
         Thread.sleep(500);
         
         //compares hands of user and dealer. Higher hand wins
         if (dealer.getValue() > user.getValue() && done == false)
         {
            System.out.println("You lost! You lost your bet of $" + bet);
         }
         else if (dealer.getValue() < user.getValue() && done == false)
         {
            System.out.println("\nYou win! Payout of $" + bet*.5);
            balance += bet*1.5;
         }
         else if (dealer.getValue() == user.getValue() && done == false)
         {
            System.out.println("\nPush! No loss or payout");
            balance += bet;
         }
         
         Thread.sleep(500);
         
         System.out.println("\n\n\n\n\n\n\n\n\n");
         
         //Will stop program if the user's balance become zero or if the user responds with 'no'
         if (balance <= 0)
            System.out.println("Out of money!");
         else
         {
            System.out.println("Balance: $" + balance + "\nWould you like to play Blackjack? (type 'yes' or 'no')");
            run = sc.next();
         }
      }
      
      //displays difference between initial and final balance
      System.out.println("Winnings: $" + (balance - 1000));
   }
}
//program end