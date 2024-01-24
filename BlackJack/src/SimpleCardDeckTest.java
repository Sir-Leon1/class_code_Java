/*
 * SimpleCardDeckTest Tests the use of 
 * the SimpleCardDeck  Class
 */

 public class SimpleCardDeckTest {
    public static void main(String args[]) {

        //create a new SimpleCardDeck object
        SimpleCardDeck deck = new SimpleCardDeck();

        //access the member variable cards
        System.out.println(deck.cards[0]);

        //call its list() method
        deck.list();
    }
 }