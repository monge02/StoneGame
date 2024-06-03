package clazz;

import java.util.HashMap;
import java.util.Map;

public class ClazzStore {

    private final Map<String, Clazz> clazzMap;

    public ClazzStore() {
        this.clazzMap = initMap();
    }

    // 입력받은 문자가 "헌터", "로그", "워리어"가 맞으면 대사와 함께 반환, 틀리면 널오브젝트를 반환하면서 문자가 맞을때까지 반복한다
    public Clazz findClazz(String clazzName) {
        return clazzMap.get(clazzName);
    }

    // key: 직업명, value: key에 해당하는 직업 객체
    private Map<String, Clazz> initMap() {
        Map<String, Clazz> initmap = new HashMap<>();

        initmap.put("헌터", new Hunter());
        initmap.put("로그", new Rogue());
        initmap.put("워리어", new Warrior());
        return initmap;
    }
}
