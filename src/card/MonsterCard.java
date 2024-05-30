package card;

public class MonsterCard extends Card {

    private int hp;
    private int attack;

    public MonsterCard(int cost, String name, int hp, int attack) {
        super(cost, name);
        this.hp = hp;
        this.attack = attack;
    }

    public int getHp() {
        return hp;
    }

    public int getAttackDamage() {
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
