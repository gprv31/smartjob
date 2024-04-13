package com.example.smartjob.domain.interactor;

import com.example.smartjob.domain.port.input.GetUserInfoUseCase;
import com.example.smartjob.domain.port.output.UserInfoDbPort;
import com.example.smartjob.entity.UserInfo;
import com.example.smartjob.entity.response.UserInfoResponse;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetUserInfoUseCaseImpl implements GetUserInfoUseCase {

    private final UserInfoDbPort userInfoDbPort;

    @Override
    public Single<UserInfo> getUserInfo(String email, String password) {
        return userInfoDbPort.getUserInformationByEmailAndPassword(email, password)
                .doOnSuccess(i -> log.info("Finished GetUserInfoUseCaseImpl.getUserInfo method successfully"))
                .doOnError(e -> log.error("Finished GetUserInfoUseCaseImpl.getUserInfo method with error", e));
    }

    @Override
    public Single<UserInfo> getUserInfoByEmail(String email) {
        return userInfoDbPort.getUserInformationByEmail(email)
                .doOnSuccess(i -> log.info("Finished GetUserInfoUseCaseImpl.getUserInfoByEmail method successfully"))
                .doOnError(e -> log.error("Finished GetUserInfoUseCaseImpl.getUserInfoByEmail method with error", e));
    }
}
