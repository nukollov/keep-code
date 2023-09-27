package com.keepcode.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class FreePhoneListResponse {

    @SerializedName("response")
    private final int response;

}
