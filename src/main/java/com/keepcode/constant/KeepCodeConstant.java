package com.keepcode.constant;

public final class KeepCodeConstant {

    public static final class HttpCode {

        public static final int OK = 200;

        private HttpCode() {
        }
    }

    public static final class Method {

        public static final String GET_FREE_COUNTRY_LIST = "/api/getFreeCountryList";
        public static final String GET_FREE_PHONE_LIST = "/api/getFreePhoneList";

        private Method() {
        }
    }

    public static final class Url {

        public static final String API_URL = "https://onlinesim.ru/";
        public static final String API_URL_IO_EXCEPTION = "https://onlinesimaaaaaa.ru/";
        public static final String API_URL_NOT_FOUND_RESPONSE = "https://www.google.com/";
    }

    public static final String RESULT_FILE_PATH = "/keep-code/src/main/resources/result.json";
    public static final String GSON_DATE_FORMAT_ISO = "yyyy-MM-dd HH:mm:ss";

    private KeepCodeConstant() {
    }
}
