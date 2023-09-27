package com.keepcode.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Getter
@ToString
public class Number {

    @SerializedName("number")
    private final String number;
    @SerializedName("country")
    private final int country;
    @SerializedName("updated_at")
    private final Date updatedAt;
    @SerializedName("data_humans")
    private final String dataHumans;
    @SerializedName("full_number")
    private final String fullNumber;
    @SerializedName("maxdate")
    private final Date maxDate;
    @SerializedName("status")
    private final String status;
}
