package com.example.smartjob.adapter.output.db.converter;

import com.example.smartjob.adapter.output.db.entity.PhoneEntity;
import com.example.smartjob.entity.Phone;

import java.util.function.Function;

public class PhoneEntityConverter implements Function<Phone, PhoneEntity> {

    @Override
    public PhoneEntity apply(Phone phone) {
        if (phone == null) {
            return null;
        }
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setNumber(phone.getNumber());
        phoneEntity.setCityCode(phone.getCityCode());
        phoneEntity.setCountryCode(phone.getCountryCode());
        return phoneEntity;
    }
}
