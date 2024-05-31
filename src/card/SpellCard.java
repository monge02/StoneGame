package card;

public class SpellCard extends Card {

    private int spellDamage;
    private int areaOfEffect;

    public SpellCard(int cost, String name, int spellDamage, int areaOfEffect) {
        super(cost, name);
        this.spellDamage = spellDamage;
        this.areaOfEffect = areaOfEffect;
    }

    @Override
    public int useCard() {
        System.out.println("주문 카드 발동!!!");
        if (spellDamage > 0) {
            System.out.println(spellDamage + "의 단일 데미지를 입힙니다.");
            return spellDamage;
        } else if (areaOfEffect > 0) {
            System.out.println(areaOfEffect + "의 광역 데미지를 입힙니다.");
            return areaOfEffect;
        } else
            return 0;
    }

    @Override
    public String toString() {
        if (spellDamage > 0) {
            return "주문 " +
                    super.toString() +
                    " 단일공격: " + spellDamage +
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
