package card;

public class MinionCard extends Card {

    private final int hp;
    private final int attack;

    public MinionCard(int cost, String name, int hp, int attack) {
        super(cost, name);
        this.hp = hp;
        this.attack = attack;
    }

    @Override
    public int useCard() {
        System.out.println(this + "를 냅니다.");
        return attack;
    }

    @Override
    public String toString() {
        return "하수인 " +
                super.toString() +
                " 공격력: " + attack +
                "/ 생명력: " + hp +
                ']';
    }
}
