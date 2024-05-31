package clazz;

public class Hunter implements Clazz {

    private final int damage = 2;

    @Override
    public int useSkill() {
        System.out.println("적 영웅에게 피해를 2 줍니다.");
        return damage;
    }
}
