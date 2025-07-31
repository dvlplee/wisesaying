package com.chacha;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    Scanner scanner = new Scanner(System.in);
    int lastId = 0;
    List<WiseSaying> list = new ArrayList<>();

    void run() {
        System.out.println("==명언 앱==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                System.out.println("프로그램을 종료합니다");
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제")) { // 삭제?id=1
                actionDelete(cmd);
            } else if (cmd.startsWith("수정")) {
                actionModify(cmd);
            }
        }
    }

    void actionWrite() {
        System.out.print("명언: ");
        String content = scanner.nextLine().trim();

        System.out.print("작가: ");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying = write(author, content);
        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    WiseSaying write(String author, String content) {
        WiseSaying wiseSaying = new WiseSaying(++lastId, author, content);
        list.add(wiseSaying);
        return wiseSaying;
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (WiseSaying w : list) {
            System.out.println(w.getId() + " / " + w.getAuthor() + " / " + w.getContent());
        }
    }

    void actionDelete(String cmd) {
        int id = CmdSplitId(cmd);
        if (id < 0) return;

        WiseSaying wiseSaying = findById(id);
        if (wiseSaying == null) return;
        delete(wiseSaying);

        System.out.println(id + "번 명언이 삭제되었습니다.");
    }

    void delete(WiseSaying wiseSaying) {
        list.remove(wiseSaying); // 객체 값으로 삭제
//        boolean removed = list.removeIf(w -> w.getId() == id);
//        if (removed) System.out.println(id + "번 명언이 삭제되었습니다.");
//        else System.out.println(id + "번 명언은 존재하지 않습니다");
    }

    void actionModify(String cmd) {
        int id = CmdSplitId(cmd);
        if (id < 0) return;

        WiseSaying wiseSaying = findById(id);
        if (wiseSaying == null) return;

        System.out.println("명언(기존): " + wiseSaying.getContent());
        System.out.print("명언: ");
        String content = scanner.nextLine().trim();

        System.out.println("작가(기존): " + wiseSaying.getAuthor());
        System.out.print("작가: ");
        String author = scanner.nextLine().trim();

        modify(wiseSaying, content, author);
    }

    void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setAuthor(content);
        wiseSaying.setContent(author);
    }

    // id로 WiseSaying 객체 찾기
    WiseSaying findById(int id) {
        WiseSaying wiseSaying = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                wiseSaying = list.get(i);
            }
        }
        if (wiseSaying == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return null;
        }
        return wiseSaying;
    }

    int CmdSplitId(String cmd) {
        String[] cmdBits = cmd.split("="); // 삭제?id=1

        if (cmdBits.length < 2 || cmdBits[1].isEmpty()) {
            System.out.println("id를 입력해주세요.");
            return -1;
        }
        return Integer.parseInt(cmdBits[1]);
    }
}
