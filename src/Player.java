import card.Card;
import card.Deck;
import card.MinionCard;
import card.SpellCard;
import clazz.Clazz;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private int hp;
    private int armor;
    private int mana;
    private int tempMana = mana;
    private Clazz clazz;
    private List<Card> hand;
    private List<Card> field;

    public Player(String name, Clazz clazz) {
        this.name = name;
        hp = 30;
        armor = 0;
        mana = 0;
        tempMana = 0;
        this.clazz = clazz;
        hand = new ArrayList<>();
        field = new ArrayList<>();
    }

    // 사용가능한 마나가 있으면 스킬을 사용한다
    public void useSkill(Player opponent) {
        if (!canUseMana()) {
            System.out.println("마나가 부족합니다.");
            return;
        }

        opponent.reduceHp(clazz.useSkill());
        System.out.println("사용가능한 마나: " + tempMana);
        tempMana -= 2;
    }

    // 턴 시작시 마나가 돌아오며, 마나의 최대치는 10이다
    public void startTurn() {
        if (mana >= 10) {
            tempMana = mana;
            System.out.println(name + "의 마나가 최대치 입니다.");
            return;
        }

        increaseMana();
        tempMana = mana;
    }

    // 덱에서 카드를 뽑는 행위
    public void drawCard(Deck deck) {
        hand.add(deck.drawCard());  // 덱에서 카드를 한장 제거하고 손에 넣는다
        System.out.println(deck.drawCard() + "를 뽑았습니다.");
        cardOverflow();
        System.out.println();
    }

    // 손에 있는 카드가 10장 이상이면 카드가 파괴 되는 기능
    private void cardOverflow() {
        if (hand.size() > 10) {
            System.out.println(hand.remove(10) + "가 파괴 되었습니다.");
        }
    }

    // 손에 카드가 있고 사용할 마나가 있으면 카드 발동
    public boolean activateCard(int cardIndex, Player opponent) {
        if (!hasCardInHand(cardIndex)) {
            System.out.println("선택한 카드는 손에 없습니다.");
            return false;   // 손에 카드가 없으면 false
        }

        Card card = hand.get(cardIndex - 1);    // 손에 카드가 있으면 입력한 숫자로 선택
        if (!canUseCard(card)) {
            System.out.println("선택한 카드를 사용할 마나가 부족합니다.");
            return false;   // 손에 카드가 있으나 사용할 마나가 부족하면 false
        }

        return isItSpellCardMinionCard(opponent, card);
    }

    private boolean isItSpellCardMinionCard(Player opponent, Card card) {
        // 주문 카드이면 바로 발동해서 적의 Hp를 깍는 행동
        if (card instanceof SpellCard spellcard) {
            useSelectedCard(opponent, spellcard);
            return true;
        }

        // 하수인 카드이면 필드에 내고 공격해서 적의 Hp를 깍는 행동
        if (card instanceof MinionCard minioncard) {    // 입력한 숫자의 카드가 하수인 카드이면 아래 진행
            if (field.size() > 7) {
                System.out.println("필드에 낼 수 있는 하수인이 가득 찼습니다.");
                return false;   // 필드에는 최대 7장의 하수인 카드를 낼 수 있다
            }

            field.add(minioncard); // 필드에 하수인 카드 추가
            System.out.println("필드에 있는 하수인 수: " + field.size() + "/7");
            useSelectedCard(opponent, minioncard);
            return true;
        }
        return false;
    }

    // 선택한 카드를 사용했을때
    private void useSelectedCard(Player opponent, Card card) {
        decreaseMana(card.getCost());   // 카드의 코스트만큼 마나 감소
        opponent.reduceHp(card.useCard());  // 카드의 데미지만큼 상대방의 체력 소모
        System.out.println();
        hand.remove(card);  // 손에서 카드 제거
    }

    // 손에 있는 카드를 필드에 낼 때 입력받은 숫자가 있는지 확인
    private boolean hasCardInHand(int cardIndex) {
        return cardIndex >= 1 && cardIndex <= hand.size();
    }

    // 카드를 사용 가능한 마나인지 확인
    private boolean canUseCard(Card card) {
        return tempMana >= card.getCost();
    }

    // 남은 마나가 2이상이어서 사용 가능한지 확인
    public boolean canUseMana() {
        return tempMana > 1;
    }

    // 손에 있는 카드를 표시하는 기능
    public void showHand() {
        System.out.println("==손에 있는 카드(" + hand.size() + "/10) / 현재 마나: " + tempMana + "==");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + hand.get(i));
        }
        System.out.println();
    }

    // 카드를 필드에 낼때 코스트만큼 마나 감소
    private void decreaseMana(int cardCost) {
        tempMana -= cardCost;
    }

    // 맞았을때 체력이 감소하는 기능
    private void reduceHp(int damage) {
        hp -= damage;
        System.out.println(name + "의 체력이 감소하였습니다. (현재 체력: " + hp + ")");
    }

    // 마나를 증가 시키는 기능
    private void increaseMana() {
        mana++;
        System.out.println(name + "의 마나가 증가하였습니다. (현재 마나: " + mana + ")");
    }

    // hp가 0이하이면 게임종료
    public boolean endGame() {
        return hp < 1;
    }

    // 턴 종료
    public void endTurn() {
        System.out.println(name + "의 턴을 종료합니다.");
        System.out.println();
    }
}
