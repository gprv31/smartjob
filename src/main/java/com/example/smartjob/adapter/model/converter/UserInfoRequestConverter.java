package com.example.smartjob.adapter.model.converter;

import com.example.smartjob.adapter.model.request.PhoneRestRequest;
import com.example.smartjob.adapter.model.request.UserInformationRestRequest;
import com.example.smartjob.entity.Phone;
import com.example.smartjob.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserInfoRequestConverter implements Function<UserInformationRestRequest, UserInfo> {

    private final Function<PhoneRestRequest, Phone> phoneConverter;

    @Override
    public UserInfo apply(UserInformationRestRequest userInformationRestRequest) {
        if (userInformationRestRequest == null) {
            return null;
        }
        return UserInfo.builder()
                .name(userInformationRestRequest.getName())
                .email(userInformationRestRequest.getEmail())
                .password(userInformationRestRequest.getPassword())
                .phoneList(this.buildPhoneRequestList(userInformationRestRequest.getPhoneRestRequestList()))
                .build();
    }

    private List<Phone> buildPhoneRequestList(List<PhoneRestRequest> phoneRestRequestList) {
        if (CollectionUtils.isEmpty(phoneRestRequestList)) {
            return Collections.emptyList();
        }
        return phoneRestRequestList.stream().map(phoneConverter::apply).collect(Collectors.toList());
    }
}
