package com.keepcode.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Country {

    @SerializedName("country")
    private final int country;
    @SerializedName("country_text")
    private final String countryText;
}
