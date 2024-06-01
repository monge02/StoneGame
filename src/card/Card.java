package card;
// 공통된 기능인 cost, name을 그대로 상속 받고, useCard는 구현에 맡기기 위해 abstract을 선택
public abstract class Card {

    private final int cost;
    private final String name;

    public Card(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public abstract int useCard();

    @Override
    public String toString() {
        return "카드(" + cost + ")['" +
                name + '\'';
    }
}
