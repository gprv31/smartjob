package com.example.smartjob.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Phone {
    private String number;
    private String cityCode;
    private String countryCode;
}
