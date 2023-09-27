package com.keepcode.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class FreePhoneListResponse {

    @SerializedName("response")
    private final int response;
    @SerializedName("numbers")
    private final List<Number> numbers;
}
