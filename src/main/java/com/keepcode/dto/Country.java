package com.keepcode.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Country {

    @SerializedName("country")
    private final String country;
    @SerializedName("country_text")
    private final String countryText;

    public Country(String country, String countryText) {
        this.country = country;
        this.countryText = countryText;
    }
}
