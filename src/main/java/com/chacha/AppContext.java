package com.chacha;

import com.chacha.domain.system.SystemController;
import com.chacha.domain.wiseSaying.WiseSayingController;
import com.chacha.domain.wiseSaying.WiseSayingRepository;
import com.chacha.domain.wiseSaying.WiseSayingService;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AppContext {
    public static final Scanner scanner;
    public static final DateTimeFormatter forPrintDateTimeFormatter;
    public static final WiseSayingRepository wiseSayingRepository;
    public static final WiseSayingService wiseSayingService;
    public static final WiseSayingController wiseSayingController;
    public static final SystemController systemController;

    static {
        scanner = new Scanner(System.in);
        forPrintDateTimeFormatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        wiseSayingRepository = new WiseSayingRepository();
        wiseSayingService = new WiseSayingService();
        wiseSayingController = new WiseSayingController();
        systemController = new SystemController();
    }
}


