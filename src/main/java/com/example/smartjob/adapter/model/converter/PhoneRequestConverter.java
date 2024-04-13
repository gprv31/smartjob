package com.example.smartjob.adapter.model.converter;


import com.example.smartjob.adapter.model.request.PhoneRestRequest;
import com.example.smartjob.entity.Phone;

import java.util.function.Function;

public class PhoneRequestConverter implements Function<PhoneRestRequest, Phone> {

    @Override
    public Phone apply(PhoneRestRequest phoneRestRequest) {
        if (phoneRestRequest == null) {
            return null;
        }
        return Phone.builder()
                .number(phoneRestRequest.getNumber())
                .cityCode(phoneRestRequest.getCitycode())
                .countryCode(phoneRestRequest.getCountrycode())
                .build();
    }
}
