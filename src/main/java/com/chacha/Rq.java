package com.chacha;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private final String actionName; // final은 재할당 불가
    private final Map<String, String> paramsMap;
    // 목록?searchKeywordType=content&searchKeyword=자바
    // 목록 => actionName
    // searchKeywordType=content&searchKeyword=자바 => queryString
    // {searchKeywordType=content , searchKeyword=자바} => paramsMap
    public Rq(String cmd) {
        paramsMap = new HashMap<>();

        String[] cmdBits = cmd.split("\\?", 2); // ?를 기준으로 2개로 쪼갬
        actionName = cmdBits[0];
        String queryString = cmdBits.length > 1 ? cmdBits[1].trim() : "";

        String[] queryStringBits = queryString.split("&");
        // [searchKeywordType=content, searchKeyword=자바]

        for (String queryParam : queryStringBits) {
            String[] queryParamBits = queryParam.split("=", 2);
            String key = queryParamBits[0].trim();
            String value = queryParamBits.length > 1 ? queryParamBits[1].trim() : "";

            if (value.isEmpty()) {
                continue;
            }

            paramsMap.put(key, value);
        }
    }

    public String getActionName() {
        return actionName;
    }

    // paramsMap 키를 입력받아서 값을 반환
    public String getParam(String paramName, String defaultValue) {
        if (paramsMap.containsKey(paramName)) {
            return paramsMap.get(paramName);
        } else {
            return defaultValue;
        }
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        String value = getParam(paramName, "");

        if(value.isEmpty()) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
