package com.chacha;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class App {

    Scanner scanner = new Scanner(System.in);
    int lastId = 0;
    List<WiseSaying> list = new ArrayList<>();

    void run() {
        System.out.println("==명언 앱==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            Rq rq = new Rq(cmd);
            switch (rq.getActionName()) {
                case "종료":
                    System.out.println("프로그램을 종료합니다");
                    return;
                case "등록":
                    actionWrite();
                    break;
                case "목록":
                    actionList();
                    break;
                case "삭제":
                    actionDelete(rq);
                    break;
                case "수정":
                    actionModify(rq);
                    break;
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
//        for (WiseSaying w : list) {
//            System.out.println(w.getId() + " / " + w.getAuthor() + " / " + w.getContent());
//        }
        IntStream.range(0, list.size())
                .map(i -> list.size() - 1 - i)
                .mapToObj(list::get)
                .forEach(ws -> System.out.println(
                        ws.getId() + " / " + ws.getAuthor() + " / " + ws.getContent()
                ));
    }

    void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        if (id == -1) {
            System.out.println("숫자를 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = findById(id);
        if (wiseSaying == null) return;

        delete(wiseSaying);

        System.out.println(id + "번 명언이 삭제되었습니다.");
    }

    void delete(WiseSaying wiseSaying) {
        list.remove(wiseSaying); // 객체 값으로 삭제
        // list.removeIf(ws -> ws.getId() == wiseSaying.getId());
    }

    void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        if (id == -1) return;

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
//        WiseSaying wiseSaying = null;
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getId() == id) {
//                wiseSaying = list.get(i);
//            }
//        }
//        if (wiseSaying == null) {
//            System.out.println(id + "번 명언은 존재하지 않습니다.");
//            return null;
//        }
//        return wiseSaying;

        // 1. 스트림 생성
        // 2. 필터링
        // 3. 첫 번째 요소 찾아서 Optional 객체로 감싸서 반환
        // (Optional 객체는 값이 없을 수도 있을 수도 있음)
        // 4. Optional 객체가 있으면 그대로 반환, 없으면 람다식 실행
        return list.stream()
                .filter(ws -> ws.getId() == id)
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("해당 아이디는 존재하지 않습니다");
                    return null;
                });
    }
}
