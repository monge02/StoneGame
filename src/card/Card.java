package card;

// 공통된 기능인 cost, name을 그대로 상속 받고, useCard는 구현에 맡기기 위해 abstract을 사용
public abstract class Card {

    private int cost;
    private String name;

    public Card(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    abstract public int useCard();

    @Override
    public String toString() {
        return "카드(" + cost + ")['" +
                name + '\'';
    }
}
