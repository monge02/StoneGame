package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> cards = new ArrayList<>();


    public Deck() {
        initCard();
        shuffle();
    }

    private void createMagicCard() {
        cards.add(new MagicCard("눈보라", 0, 2));
        cards.add(new MagicCard("화염구", 2, 0));
    }

    private void createMonsterCard() {
        cards.add(new MonsterCard("혈법사 탈노스", 1, 1));
        cards.add(new MonsterCard("리로이 젠킨스", 2, 2));
        cards.add(new MonsterCard("실바나스 윈드러너", 3, 3));
    }

    private void initCard() {
        for (int i = 0; i < 10; i++) {
            createMonsterCard();
        }
        for (int i = 0; i < 5; i++) {
            createMagicCard();
        }
    }

    private void shuffle() {
        Collections.shuffle(cards);
        while (cards.size() > 30) {
            cards.remove(cards.size() - 1);
        }
    }

    public Card drawCard() {
        return cards.remove(0);
    }
}
