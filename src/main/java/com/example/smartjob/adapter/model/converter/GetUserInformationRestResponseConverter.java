package com.example.smartjob.adapter.model.converter;

import com.example.smartjob.adapter.model.response.GetUserInformationRestResponse;
import com.example.smartjob.entity.UserInfo;

import java.util.function.Function;

public class GetUserInformationRestResponseConverter implements Function<UserInfo, GetUserInformationRestResponse> {
    @Override
    public GetUserInformationRestResponse apply(UserInfo userInfo) {
        if (userInfo == null) {
            return null;
        }
        return GetUserInformationRestResponse.builder()
                .name(userInfo.getName())
                .email(userInfo.getEmail())
                .build();
    }
}
