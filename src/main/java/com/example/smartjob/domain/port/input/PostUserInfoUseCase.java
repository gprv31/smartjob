package com.example.smartjob.domain.port.input;

import com.example.smartjob.entity.UserInfo;
import com.example.smartjob.entity.response.UserInfoResponse;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface PostUserInfoUseCase {

    Single<UserInfoResponse> saveUserInformation(UserInfo userInfo);
}
