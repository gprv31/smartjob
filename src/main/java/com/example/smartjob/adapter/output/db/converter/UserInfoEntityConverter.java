package com.example.smartjob.adapter.output.db.converter;

import com.example.smartjob.adapter.output.db.entity.PhoneEntity;
import com.example.smartjob.adapter.output.db.entity.UserInfoEntity;
import com.example.smartjob.adapter.output.db.util.EncryptionUtil;
import com.example.smartjob.entity.Phone;
import com.example.smartjob.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserInfoEntityConverter implements Function<UserInfo, UserInfoEntity> {



    @Override
    public UserInfoEntity apply(UserInfo userInfo) {
        if (userInfo == null) {
            return null;
        }
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setName(userInfo.getName());
        userInfoEntity.setEmail(userInfo.getEmail());
        userInfoEntity.setPassword(EncryptionUtil.encrypt(userInfo.getPassword()));
        return userInfoEntity;
    }



}
