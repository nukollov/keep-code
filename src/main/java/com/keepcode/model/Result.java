package com.keepcode.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@ToString
public class Result {

    private final List<CountryNumbers> countries;
}
