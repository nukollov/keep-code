package com.keepcode;

import com.keepcode.constant.KeepCodeConstant;
import com.keepcode.model.Result;
import com.keepcode.service.CountryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountryServiceTest {

    @Test
    void getCountryNumbersSBSuccess() {
        Result result = new CountryService(KeepCodeConstant.Url.API_URL).getCountryNumbers();

        Assertions.assertNotNull(result);
    }

    @Test
    void getCountryNumbersSBIOException() {
        Result result = new CountryService(KeepCodeConstant.Url.API_URL_IO_EXCEPTION).getCountryNumbers();

        Assertions.assertNull(result);
    }

    @Test
    void getCountryNumbersSBNotFound() {
        Result result = new CountryService(KeepCodeConstant.Url.API_URL_NOT_FOUND_RESPONSE).getCountryNumbers();

        Assertions.assertNull(result);
    }
}
