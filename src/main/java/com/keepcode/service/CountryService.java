package com.keepcode.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.keepcode.constant.KeepCodeConstant;
import com.keepcode.dto.CountryListResponse;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CountryService {

    private static final Gson GSON = new Gson();
    private static final Logger LOG = LoggerFactory.getLogger(CountryService.class);

    private final OkHttpClient httpClient;
    private final String apiUrl;

    public CountryService(String apiUrl) {
        this.apiUrl = apiUrl;
        this.httpClient = new OkHttpClient();
    }

    public CountryListResponse getCountryList() {
        Request request = new Request.Builder()
                .url(apiUrl + KeepCodeConstant.Method.GET_FREE_COUNTRY_LIST)
                .get()
                .build();
        LOG.info("Request: {}", request);

        try {
            Response response = httpClient.newCall(request).execute();
            String body = processResponse(response);

            return parseBody(body, CountryListResponse.class);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Метод разбора ответа внешней системы
     *
     * @param response - ответ внешней системы
     * @return body ответа внешней системы
     */
    private String processResponse(Response response) throws IOException {
        int code = response.code();
        String body = response.body().string();

        LOG.info("Response: [code = '{}', body '{}']", code, body);

        if (KeepCodeConstant.HttpCode.OK != code || body.length() == 0) {
            LOG.error("Wrong response: [code = '{}', body = '{}']", code, body);
            throw new IOException("Wrong response");
        }

        return body;
    }

    private <T> T parseBody(String body, Class<T> tClass) throws IOException {
        try {
            return GSON.fromJson(body, tClass);
        } catch (JsonSyntaxException e) {
            throw new IOException(e.getMessage(), e);
        }
    }
}
