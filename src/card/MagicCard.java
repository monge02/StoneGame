package card;

public class MagicCard extends Card {

    private int damageToMonster;
    private int areaOfEffect;

    public MagicCard(int cost, String name, int damageToMonster, int areaOfEffect) {
        super(cost, name);
        this.damageToMonster = damageToMonster;
        this.areaOfEffect = areaOfEffect;
    }

    public int getAttackDamage() {
        return damageToMonster;
    }

    @Override
    public String toString() {
        if (damageToMonster > 0) {
            return "주문 " +
                    super.toString() +
                    " 단일공격: " + damageToMonster +
                    ']';
        } else if (areaOfEffect > 0) {
            return "주문 " +
                    super.toString() +
                    " 광역공격: " + areaOfEffect +
                    ']';
        }
        return "";
    }
}
