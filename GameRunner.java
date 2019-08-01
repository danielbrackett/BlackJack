public class GameRunner {
//may split this up this to make it a black jack runner and a general game runner.

    public static void main(String[] args) {

        Deck deck = new Deck();
        deck.shuffle();
        deck.printDeck();
    }
}
