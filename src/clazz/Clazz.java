package clazz;

import java.util.HashMap;
import java.util.Map;

// 공통된 필드가 없기 때문에 기능만 구현하기 위해 interface를 선택
public interface Clazz {

    int useSkill();

    // 입력받은 문자가 "헌터", "로그", "워리어"가 맞으면 대사와 함께 반환, 틀리면 널오브젝트를 반환하면서 문자가 맞을때까지 반복한다
    static Clazz findClazz(String clazzName) {
        Map<String, Clazz> clazzMap = new HashMap();
        clazzMap.put("헌터", new Hunter());
        clazzMap.put("로그", new Rogue());
        clazzMap.put("워리어", new Warrior());
        clazzMap.put("헌터", new Hunter());

        clazzMap.containsKey(clazzName);
        return null;
    }
}

/*
    개선된 switch 구문으로 작성
    switch (clazzName) {
        case "헌터" -> {
            System.out.println("렉사르: 사냥을 시작하지!!!");
            System.out.println();
            return new Hunter();
        }
        case "로그" -> {
            System.out.println("발리라: 등...뒤를... 조심해");
            System.out.println();
            return new Rogue();
        }
        case "워리어" -> {
            System.out.println("가로쉬: 승리가 아니면 죽음을!!");
            System.out.println();
            return new Warrior();
        }
        default -> {
            return new NullObject();
        }
    }
*/


/*
    if 절로 작성
    if (clazzName.equals("헌터")) {
        System.out.println("렉사르: 사냥을 시작하지!!!");
        System.out.println();
        return new Hunter();
    } else if (clazzName.equals("로그")) {
        System.out.println("발리라: 등...뒤를... 조심해");
        System.out.println();
        return new Rogue();
    } else if (clazzName.equals("워리어")) {
        System.out.println("가로쉬: 승리가 아니면 죽음을!!");
        System.out.println();
        return new Warrior();
    } else {
        return new NullObject();
    }
*/

