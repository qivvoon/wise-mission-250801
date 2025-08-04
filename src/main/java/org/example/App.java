package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    Scanner sc = new Scanner(System.in);
    WiseSaying wiseSaying = null;
    ArrayList<WiseSaying> wiseSayings = new ArrayList<>();
    int num = 0;

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명언) ");
            String command = sc.nextLine();

            if(command.equals("등록")) {
                actionWrite();
            } else if(command.equals("목록")) {
                actionList();
            } else if(command.contains("삭제")) {
                if(command.matches("삭제\\?id=\\d+")) {
                    int deleteId = Integer.valueOf(command.replaceAll("\\D+", ""));
                    actionDelete(deleteId);
                } else {
                    System.out.println("'삭제?id=1' 형태로 입력해주세요.");
                }
            } else if(command.contains("수정")) {
                if(command.matches("수정\\?id=\\d+")) {
                    int modifyId = Integer.valueOf(command.replaceAll("\\D+", ""));
                    actionModify(modifyId);
                } else {
                    System.out.println("'수정?id=1' 형태로 입력해주세요.");
                }
            } else if(command.equals("종료")) { break; }
        }
    }

    public void actionWrite() {
        System.out.print("명언: ");
        String saying = sc.nextLine();
        System.out.print("작가: ");
        String author = sc.nextLine();
        num++;

        wiseSaying = new WiseSaying();
        wiseSaying.id = num;
        wiseSaying.saying = saying;
        wiseSaying.author = author;

        wiseSayings.add(wiseSaying);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(num));
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for(int i=wiseSayings.size()-1; i>=0; i--) {
            System.out.println("%d / %s / %s".formatted(wiseSayings.get(i).id,wiseSayings.get(i).saying, wiseSayings.get(i).author));
        }
    }

    public void actionDelete(int deleteId) {

        for(int i=0 ; i<wiseSayings.size(); i++) {
            if(wiseSayings.get(i).id == deleteId) {
                wiseSayings.remove(i);
                System.out.println("%d번 명언이 삭제되었습니다.".formatted(deleteId));
                return;
            }
        }

        System.out.println("%d번 명언은 존재하지 않습니다.".formatted(deleteId));
    }

    public void actionModify(int modifyId) {
        for(int i=0 ; i<wiseSayings.size(); i++) {
            if(wiseSayings.get(i).id == modifyId) {
                System.out.println("명언(기존): %s".formatted(wiseSayings.get(i).saying));
                System.out.print("명언: ");
                String modifySaying = sc.nextLine();

                System.out.println("작가(기존): %s".formatted(wiseSayings.get(i).author));
                System.out.print("작가: ");
                String modifyAuthor = sc.nextLine();

                wiseSayings.get(i).saying = modifySaying;
                wiseSayings.get(i).author = modifyAuthor;

                return;
            }
        }

        System.out.println("%d번 명언은 존재하지 않습니다.".formatted(modifyId));
    }
}
