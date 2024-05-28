package card;

public class MonsterCard implements Card {

    private String monsterName;
    private int hp;
    private int attack;

    MonsterCard(String name, int hp, int attack) {
        this.monsterName = name;
        this.hp = hp;
        this.attack = attack;
    }

    public String getName() {
        return monsterName;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public String toString() {
        return "하수인 카드[" +
                "카드 이름: '" + monsterName + '\'' +
                " 공격력: " + attack +
                "/ 생명력: " + hp +
                ']';
    }
}
