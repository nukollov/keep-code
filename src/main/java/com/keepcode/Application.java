package com.keepcode;

import com.keepcode.service.CountryService;

public class Application {

    public static void main(String[] args) {

        CountryService service = new CountryService("https://onlinesim.ru/");
        service.getCountryList();
        System.out.println();
    }
}
