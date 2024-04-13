package com.example.smartjob.domain.port.input;

import com.example.smartjob.entity.UserInfo;
import com.example.smartjob.entity.response.UserInfoResponse;
import io.reactivex.rxjava3.core.Single;

public interface GetUserInfoUseCase {
    Single<UserInfo> getUserInfo(String email, String password);
    Single<UserInfo> getUserInfoByEmail(String email);
}
