package card;

public class MagicCard implements Card {

    private String magicName;
    private int damageToMonster;
    private int areaOfEffect;

    public MagicCard(String magicName, int damageToMonster, int areaOfEffect) {
        this.magicName = magicName;
        this.damageToMonster = damageToMonster;
        this.areaOfEffect = areaOfEffect;
    }

    public String getMagicName() {
        return magicName;
    }

    public int getDamageToMonster() {
        return damageToMonster;
    }

    @Override
    public String toString() {
        if (damageToMonster > 0) {
            return "주문 카드{" +
                    "카드 이름: '" + magicName + '\'' +
                    " 단일공격: " + damageToMonster +
                    '}';
        } else if (areaOfEffect > 0) {
            return "주문 카드[" +
                    "카드 이름: '" + magicName + '\'' +
                    " 광역공격: " + areaOfEffect +
                    ']';
        }
        return null;
    }
}
