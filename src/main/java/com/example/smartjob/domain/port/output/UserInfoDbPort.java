package com.example.smartjob.domain.port.output;

import com.example.smartjob.entity.UserInfo;
import com.example.smartjob.entity.response.UserInfoResponse;
import io.reactivex.rxjava3.core.Single;

public interface UserInfoDbPort {
    Single<UserInfoResponse> saveUserInformation(UserInfo userInfo);

    Single<UserInfo> getUserInformationByEmail(String email);

    Single<UserInfo> getUserInformationByEmailAndPassword(String email, String password);
}
