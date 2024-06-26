import card.Deck;
import clazz.Clazz;
import clazz.ClazzStore;
import clazz.Rogue;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private static Map<Integer, Runnable> actions;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // clazzStore.findClazz를 불러오기 위해 객체 생성
        // findClazz의 메서드만 쓰기 때문에 abstract클래스 static으로 생성할까 고민중
        ClazzStore clazzStore = new ClazzStore();

        // 게임시작, 닉네임 입력
        System.out.println("==게임 시작==");
        System.out.print("닉네임을 적어주세요: ");
        String nickname = input.nextLine();

        // "헌터" | "로그" | "워리어" 중 직업 입력
        Clazz clazz = getClazz(input, clazzStore);

        // 입력받은 닉네임과 직업을 플레이어에 담는다
        Player player1 = new Player(nickname, clazz);
        Player player2 = new Player("컴퓨터", new Rogue());

        // SpellCard, MinionCard를 담고 섞어서 30장의 덱을 만든다
        Deck deck = new Deck();

        // playCard 메서드 Map에 가져오기
        actions = mapInit(input, player1, player2);

        // 게임 진행
        while (true) {
            // 턴 시작시 플레이어의 마나 초기화 및 증가
            player1.startTurn();

            // 덱에서 카드를 한 장 뽑는다
            player1.drawCard(deck);

            // 현재 손에 있는 카드와 보유 마나를 보여준다
            player1.showHand();

            // [카드 선택: 1] | [스킬 사용: 2] | [턴 종료: 3] 메뉴가 등장하고 선택
            selectionMenu(input, actions);

            // player2의 hp가 0이면 종료
            if (player2.endGame()) {
                break;
            }

            // 턴 종료
            player1.endTurn();
        }
        System.out.println("==게임 종료==");
    }

    // 입력받은 값의 클래스를 가져오는 기능
    private static Clazz getClazz(Scanner input, ClazzStore clazzStore) {
        Clazz clazz;
        while (true) {
            System.out.println("직업을 선택해 주세요");
            System.out.print("[헌터] | [로그] | [워리어]: ");
            String clazzName = input.nextLine();
            // "헌터", "로그", "워리어"를 입력시 해당하는 객체를 찾아옴
            clazz = clazzStore.findClazz(clazzName);

            // 입력받은 값이 clazzStore의 Map에 존재하면 break
            if (clazz != null) {
                break;
            }
            System.out.println("잘못된 입력입니다!!! 다시 입력해주세요.");
        }
        return clazz;
    }

    // key: 메뉴 선택 번호, value: key에 해당하는 메뉴 실행
    private static Map<Integer, Runnable> mapInit(Scanner input, Player player1, Player player2) {
        Map<Integer, Runnable> actions = new HashMap<>();

        actions.put(1, () -> choiceCard(input, player1, player2));
        actions.put(2, () -> useSkill(input, player1, player2));
        actions.put(3, player1::endTurn);
        return actions;
    }

    // [카드 선택: 1] | [스킬 사용: 2] | [턴 종료: 3] 중 입력받은 숫자 실행
    private static void selectionMenu(Scanner input, Map<Integer, Runnable> actions) {
        while (true) {
            System.out.println("==[카드 선택: 1] | [스킬 사용: 2] | [턴 종료: 3]==");
            int selectNumber = input.nextInt();
            if (actions.containsKey(selectNumber)) {
                actions.get(selectNumber).run();
                break;
            }
            System.out.println("잘못된 입력입니다!!! 다시 입력해주세요.");
        }
    }

    /*
    카드를 선택하는 행동
        - 선택한 카드가 손에 없거나, 마나가 부족하면 메뉴 선택 화면으로 이동
        - 남은 마나가 2이상이면 반복 후 마나가 부족하면 턴 종료
     */
    private static void choiceCard(Scanner input, Player player1, Player player2) {
        // 선택한 카드가 손에 없거나, 마나가 부족하면 메뉴 선택 화면으로 이동
        System.out.print("선택할 카드번호를 입력하세요: ");
        int selectCardIndex = input.nextInt();

        // while 조건에 부합하지 못하면 메뉴 선택 화면으로 이동하고 3을 입력시 return
        while (!player1.activateCard(selectCardIndex, player2)) {
            if (isReturnSelectionMenu(input)) {
                return;
            }
        }

        // 남은 마나가 2이상이면 반복 후 마나가 부족하면 턴 종료
        repeatIfManaIs2More(input, player1, player2);
    }

    // 남은 마나가 2이상이면 반복 후 마나가 부족하면 턴 종료
    private static void repeatIfManaIs2More(Scanner input, Player player1, Player player2) {
        while (player1.canUseMana()) { // 남은 마나가 2이상이면 반복
            player1.showHand(); // 현재 카드와 마나를 보여준다

            System.out.print("선택할 카드번호를 입력하세요: "); // 선택한 카드가 손에 있고, 마나도 있을때 카드를 선택
            int cardIndex2 = input.nextInt();
            player1.activateCard(cardIndex2, player2); // 선택한 카드를 사용
            System.out.println();
        }
    }

    /*
     스킬을 사용하는 행동
        - 플레이어의 마나가 없으면 메뉴 선택 화면으로 이동
        - 마나가 있으면 스킬을 사용 후 메뉴 선택 화면으로 이동
     */
    private static void useSkill(Scanner input, Player player1, Player player2) {
        // 플레이어의 마나가 없으면 메뉴 선택 화면으로 이동
        while (!player1.canUseMana()) {
            System.out.println("마나가 부족합니다.");
            if (isReturnSelectionMenu(input)) {
                return;
            }
        }

        // 마나가 있으면 스킬을 사용
        player1.useSkill(player2); // 상대방에게 직업 스킬을 사용
        while (player1.canUseMana()) { // 남은 마나가 2이상이면 반복
            // 선택 창으로 다시 돌아감
            if (isReturnSelectionMenu(input)) {
                return;
            }

            if (player2.endGame()) {
                return;
            }
        }
    }

    // 조건에 부합하지 못할시 메뉴 선택 화면으로 이동
    private static boolean isReturnSelectionMenu(Scanner input) {
        System.out.println("다시 선택해 주세요");
        selectionMenu(input, actions);

        return actions.containsKey(3);
    }
}
