import card.Card;
import card.Deck;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> hand;
    private String name;
    private int hp;
    private int armor;

    public Player(String name) {
        this.name = name;
        hp = 30;
        armor = 0;
        hand = new ArrayList<>();
    }

    public void playCard(int cardIndex) {
        System.out.println(hand.get(cardIndex) + "를 필드에 냅니다.");
        hand.remove(cardIndex);
        System.out.println();
    }

    public void drawCard(Deck deck) {
        Card drawCard = deck.drawCard();
        hand.add(drawCard);
        System.out.println(drawCard + "를 뽑았습니다.");
        cardOverflow();
        System.out.println();
    }

    private void cardOverflow() {
        if (hand.size() > 10) {
            System.out.println(hand.remove(10) + "가 파괴 되었습니다.");
        }
    }

    public void showHand() {
        System.out.println("==손에 있는 카드(" + hand.size() + "/10)==");
        System.out.println();
        for (Card card : hand) {
            System.out.println(card);
        }
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getArmor() {
        return armor;
    }
}
