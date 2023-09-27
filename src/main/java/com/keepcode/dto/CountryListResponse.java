package com.keepcode.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class CountryListResponse {

    @SerializedName("response")
    private final int response;
    @SerializedName("countries")
    private final List<Country> countries;
}
