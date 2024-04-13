package com.example.smartjob.domain.interactor;

import com.example.smartjob.application.configuration.security.JwtService;
import com.example.smartjob.domain.port.input.PostUserInfoUseCase;
import com.example.smartjob.domain.port.output.UserInfoDbPort;
import com.example.smartjob.entity.UserInfo;
import com.example.smartjob.entity.response.UserInfoResponse;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PostUserInfoUseCaseImpl implements PostUserInfoUseCase {

    private final UserInfoDbPort userInfoDbPort;
    private final JwtService jwtService;

    @Override
    public Single<UserInfoResponse> saveUserInformation(UserInfo userInfo) {
        log.info("Starting PostUserInfoUseCaseImpl.saveUserInformation method");
        return userInfoDbPort.saveUserInformation(userInfo)
                .map(userInfoResponse -> {
                    //TODO: Generar el token
                    userInfoResponse.setToken(jwtService.generateToken(new org.springframework.security.core.userdetails.User(
                            userInfo.getEmail(), userInfo.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")))));
                    return userInfoResponse;
                })
                .doOnSuccess(i -> log.info("Finished PostUserInfoUseCaseImpl.saveUserInformation method successfully"))
                .doOnError(e -> log.error("Finished PostUserInfoUseCaseImpl.saveUserInformation method with error", e));
    }
}
