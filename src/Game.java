import card.Deck;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("==게임 시작==");
        String nickname = input.nextLine();
        System.out.print("닉네임을 적어주세요: ");
        Player player1 = new Player(nickname);
        Player player2 = new Player("컴퓨터");
        Deck deck = new Deck();

        while (true) {
            player1.startTurn(); // 턴 시작시 마나증가 및 초기화
            player1.drawCard(deck); // 카드를 한 장 뽑는다
            player1.showHand(); // 현재 카드와 마나를 보여준다
            playCard(input, player1, player2); // 카드선택 | 직업스킬 | 턴 종료 중 고르며, 잘못 입력시 다시 playCard 실행
            if (player2.endGame()) { // player2의 hp가 0이면 종료
                break;
            }
            player1.endTurn(); // 턴 종료
        }
        System.out.println("==게임 종료==");
    }

    private static void playCard(Scanner input, Player player1, Player player2) {
        System.out.println("==[카드 선택: 1] | [직업 스킬: 2] | [턴 종료: 3]==");
        int choiceNumber = input.nextInt();
        switch (choiceNumber) {
            case 1:
                System.out.print("선택할 카드번호를 입력하세요: " );
                int cardIndex = input.nextInt();
                if (!player1.useCard(cardIndex, player2)) { // 선택한 카드가 손에 없거나, 마나가 부족해서 카드를 낼 수 없을때
                    System.out.println("다시 선택해 주세요");
                    playCard(input, player1, player2); // 다시 playCard 메서드로 돌아간다
                }
                while (player1.canUseMana()) { // 남은 마나가 2이상이면 반복
                    player1.showHand(); // 현재 카드와 마나를 보여준다
                    System.out.print("선택할 카드번호를 입력하세요: " ); // 선택한 카드가 손에 있고, 마나도 있을때 카드를 선택
                    int cardIndex2 = input.nextInt();
                    player1.useCard(cardIndex2, player2); // 선택한 카드를 사용
                    System.out.println();
                }
                break;
            case 2:
                System.out.println("직업 스킬 사용"); // 구현 중
                break;
            case 3:
                player1.endTurn(); // 턴 종료
                break;
            default:
                player1.showHand(); // 현재 카드와 마나를 보여준다
                playCard(input, player1, player2); // 잘못 입력시 다시 playCard 메서드 실행
        }
    }
}
