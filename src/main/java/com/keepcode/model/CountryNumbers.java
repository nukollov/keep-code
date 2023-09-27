package com.keepcode.model;

import com.keepcode.dto.Number;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@ToString
public class CountryNumbers {

    private final int country;
    private final String countryText;
    private final List<Number> numbers;
}
