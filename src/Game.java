import card.Deck;

public class Game {

    public static void main(String[] args) {
        gameStart();
        Deck deck = new Deck();
        Player p1 = new Player("플레이어1");
        p1.drawCard(deck);
        p1.drawCard(deck);
        p1.drawCard(deck);
        p1.drawCard(deck);
        p1.drawCard(deck);
        p1.drawCard(deck);
        p1.showHand();
        p1.playCard(1);
        p1.showHand();
        gameEnd();
    }

    private static void gameStart() {
        System.out.println("==Start Game==");
    }

    private static void gameEnd() {
        System.out.println("==End Game==");
    }
}
