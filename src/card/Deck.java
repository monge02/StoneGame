package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    // SpellCard, MinionCard를 담을 덱
    private List<Card> cards = new ArrayList<>();

    public Deck() {
        initCard(); // new Deck 생성시 SpellCard, MinionCard를 cards에 담음
        shuffle(); // cards를 섞고, index[29] 이후의 카드는 모두 remove해서 30장의 덱을 완성
        System.out.println("덱을 생성중입니다...");
        System.out.println();
    }

    private void initCard() {
        createSpellCard();
        createMinionCard();
    }

    private void createSpellCard() {
        for (int i = 0; i < 5; i++) {
            cards.add(new SpellCard(2, "눈보라", 0, 2));
            cards.add(new SpellCard(2, "화염구", 3, 0));
        }
    }

    private void createMinionCard() {
        for (int i = 0; i < 10; i++) {
            cards.add(new MinionCard(2, "혈법사 탈노스", 1, 1));
            cards.add(new MinionCard(2, "리로이 젠킨스", 2, 2));
            cards.add(new MinionCard(2, "실바나스 윈드러너", 3, 3));
        }
    }

    private void shuffle() {
        Collections.shuffle(cards);
        while (cards.size() > 30) {
            cards.remove(cards.size() - 1);
        }
    }

    // 카드를 빼서 Player의 List<Card> hand = new ArrayList<>()에 담을 예정
    public Card drawCard() {
        return cards.remove(0);
    }
}
