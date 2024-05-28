import card.Deck;

public class Game {

    public static void main(String[] args) {
        gameStart();
        Deck deck = new Deck();
        for (int i = 0; i < 31; i++) {
            System.out.println(deck.drawCard());
        }
        gameEnd();
    }

    private static void gameStart() {
        System.out.println("==Start Game==");
    }

    private static void gameEnd() {
        System.out.println("==End Game==");
    }
}
