package com.example.smartjob.application.configuration;

import com.example.smartjob.adapter.model.converter.GetUserInformationRestResponseConverter;
import com.example.smartjob.adapter.model.converter.PhoneRequestConverter;
import com.example.smartjob.adapter.model.converter.UserInfoRequestConverter;
import com.example.smartjob.adapter.model.converter.UserInformationRestResponseConverter;
import com.example.smartjob.adapter.model.request.PhoneRestRequest;
import com.example.smartjob.adapter.model.request.UserInformationRestRequest;
import com.example.smartjob.adapter.model.response.GetUserInformationRestResponse;
import com.example.smartjob.adapter.model.response.UserInformationRestResponse;
import com.example.smartjob.application.configuration.security.JwtAuthenticationFilter;
import com.example.smartjob.application.configuration.security.JwtService;
import com.example.smartjob.domain.interactor.GetUserInfoUseCaseImpl;
import com.example.smartjob.domain.interactor.PostUserInfoUseCaseImpl;
import com.example.smartjob.domain.port.input.GetUserInfoUseCase;
import com.example.smartjob.domain.port.input.PostUserInfoUseCase;
import com.example.smartjob.domain.port.output.UserInfoDbPort;
import com.example.smartjob.entity.Phone;
import com.example.smartjob.entity.UserInfo;
import com.example.smartjob.entity.response.UserInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Slf4j
@Configuration
public class ControllerConfiguration {

    /* UseCase Beans Injection */
    @Bean
    public PostUserInfoUseCase postUserInfoUseCase(UserInfoDbPort userInfoDbPort, JwtService jwtService) {
        return new PostUserInfoUseCaseImpl(userInfoDbPort, jwtService);
    }

    @Bean
    public GetUserInfoUseCase getUserInfoUseCase(UserInfoDbPort userInfoDbPort) {
        return new GetUserInfoUseCaseImpl(userInfoDbPort);
    }

    /* Request Converter Beans Injection */
    @Bean
    public Function<UserInformationRestRequest, UserInfo> userInfoRequestConverter(
            Function<PhoneRestRequest, Phone> phoneConverter) {
        return new UserInfoRequestConverter(phoneConverter);
    }

    @Bean
    public Function<PhoneRestRequest, Phone> PhoneRequestConverter() {
        return new PhoneRequestConverter();
    }

    /* Response Converter Beans Injection */

    @Bean
    public Function<UserInfoResponse, UserInformationRestResponse> userInformationRestResponseConverter() {
        return new UserInformationRestResponseConverter();
    }

    @Bean
    public Function<UserInfo, GetUserInformationRestResponse> getUserInformationRestResponseConverter() {
        return new GetUserInformationRestResponseConverter();
    }






}
