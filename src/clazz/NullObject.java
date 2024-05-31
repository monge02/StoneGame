package clazz;

public class NullObject implements Clazz {

    @Override
    public int useSkill() {
        System.out.println("잘못된 입력입니다.");
        return 0;
    }
}
