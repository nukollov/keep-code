package com.keepcode.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.keepcode.constant.KeepCodeConstant;
import com.keepcode.dto.CountryListResponse;
import com.keepcode.dto.FreePhoneListResponse;
import com.keepcode.dto.Number;
import com.keepcode.model.CountryNumbers;
import com.keepcode.model.Result;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountryService {

    private static final Gson GSON = new GsonBuilder()
            .setDateFormat(KeepCodeConstant.GSON_DATE_FORMAT_ISO)
            .create();
    private static final Logger LOG = LoggerFactory.getLogger(CountryService.class);

    private final OkHttpClient httpClient;
    private final String apiUrl;

    public CountryService(String apiUrl) {
        this.apiUrl = apiUrl;
        this.httpClient = new OkHttpClient();
    }

    /**
     * Метод сбора номеров по странам
     */
    public Result getCountryNumbers() {
        try {
            CountryListResponse countryListResponse = getCountryList();
            FreePhoneListResponse freePhoneListResponse = getFreePhones();

            Map<Integer, List<Number>> numberMap = new HashMap<>();
            for (Number n : freePhoneListResponse.getNumbers()) {
                if (numberMap.containsKey(n.getCountry())) {
                    numberMap.get(n.getCountry()).add(n);
                } else {
                    List<Number> list = new LinkedList<>();
                    list.add(n);
                    numberMap.put(n.getCountry(), list);
                }
            }

            Result result = new Result(countryListResponse.getCountries().stream()
                    .map(e -> new CountryNumbers(e.getCountry(), e.getCountryText(), numberMap.get(e.getCountry())))
                    .collect(Collectors.toList()));

            try (PrintWriter out = new PrintWriter(new FileWriter(KeepCodeConstant.RESULT_FILE_PATH))) {
                out.write(GSON.toJson(result));
            }

            return result;
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Метод получения доступных стран
     *
     * @return список доступных стран
     */
    private CountryListResponse getCountryList() throws IOException {
        Request request = new Request.Builder()
                .url(apiUrl + KeepCodeConstant.Method.GET_FREE_COUNTRY_LIST)
                .get()
                .build();
        LOG.info("Request: {}", request);

        Response response = httpClient.newCall(request).execute();
        String body = processResponse(response);

        return parseBody(body, CountryListResponse.class);
    }

    /**
     * Метод получения доступных номеров по всем странам
     *
     * @return список всех номеров по странам
     */
    private FreePhoneListResponse getFreePhones() throws IOException {
        Request request = new Request.Builder()
                .url(apiUrl + KeepCodeConstant.Method.GET_FREE_PHONE_LIST)
                .get()
                .build();
        LOG.info("Request: {}", request);

        Response response = httpClient.newCall(request).execute();
        String body = processResponse(response);

        return parseBody(body, FreePhoneListResponse.class);
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

        LOG.info("Response: [code = '{}', body = '{}']", code, body);

        if (KeepCodeConstant.HttpCode.OK != code || body.length() == 0) {
            throw new IOException("Wrong response");
        }

        return body;
    }

    /**
     * Метод парсинга body ответа в dto
     *
     * @param body   - body ответа
     * @param tClass - класс, в который производится парсинг
     * @return dto ответа
     */
    private <T> T parseBody(String body, Class<T> tClass) throws IOException {
        try {
            return GSON.fromJson(body, tClass);
        } catch (JsonSyntaxException e) {
            throw new IOException(e.getMessage(), e);
        }
    }
}
