package com.example.smartjob.adapter.model.converter;

import com.example.smartjob.adapter.model.response.UserInformationRestResponse;
import com.example.smartjob.entity.response.UserInfoResponse;

import java.util.function.Function;

public class UserInformationRestResponseConverter implements Function<UserInfoResponse, UserInformationRestResponse> {

    @Override
    public UserInformationRestResponse apply(UserInfoResponse userInfoResponse) {
        if (userInfoResponse == null) {
            return null;
        }
        return UserInformationRestResponse.builder()
                .id(userInfoResponse.getId())
                .created(userInfoResponse.getCreated())
                .modified(userInfoResponse.getModified())
                .lastLogin(userInfoResponse.getLastLogin())
                .token(userInfoResponse.getToken())
                .active(userInfoResponse.isActive())
                .build();
    }
}
