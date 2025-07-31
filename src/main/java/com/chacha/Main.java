package com.chacha;

public class Main {
    public static void main(String[] args) {
//        App app = new App();
//        app.run();

        testRq1();
    }
    private static void testRq1() {
        Rq rq = new Rq("목록?searchKeywordType=content&searchKeyword=자바&page=9");
        int page = rq.getParamAsInt("page", -1);
        int sort = rq.getParamAsInt("sort", -1);
        int id = rq.getParamAsInt("id", -1);

        System.out.println(rq.getActionName());
        System.out.println(rq.getParam("searchKeywordType", ""));
        System.out.println(rq.getParam("searchKeyword", ""));
        System.out.println(page);
        System.out.println(sort);
        System.out.println(id);

//        String page = rq.getParam("page", "");
    }
}
