package com.example.smartjob.application.configuration;

import com.example.smartjob.adapter.output.db.UserInfoDbPortImpl;
import com.example.smartjob.adapter.output.db.converter.PhoneEntityConverter;
import com.example.smartjob.adapter.output.db.converter.UserInfoEntityConverter;
import com.example.smartjob.adapter.output.db.entity.PhoneEntity;
import com.example.smartjob.adapter.output.db.entity.UserInfoEntity;
import com.example.smartjob.adapter.output.db.repository.PhoneRepository;
import com.example.smartjob.adapter.output.db.repository.UserInfoRepository;
import com.example.smartjob.domain.port.output.UserInfoDbPort;
import com.example.smartjob.entity.Phone;
import com.example.smartjob.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Slf4j
@Configuration
public class DomainConfiguration {
    /* Output converter Beans injection */


    /* Internal Converter Beans Injection */

    @Bean
    public Function<UserInfo, UserInfoEntity> userInfoEntityConverter() {
        return new UserInfoEntityConverter();
    }

    @Bean
    public Function<Phone, PhoneEntity> phoneEntityConverter() {
        return new PhoneEntityConverter();
    }

    /* Jpa Specification Beans Injection */

    /* Port Beans Injection */
    @Bean
    public UserInfoDbPort userInfoDbPort(UserInfoRepository userInfoRepository,
                                         PhoneRepository phoneRepository,
                                         Function<UserInfo, UserInfoEntity> userInfoEntityConverter,
                                         Function<Phone, PhoneEntity> phoneEntityConverter,
                                         @Value("${validation.email-regex}") String emailRegex) {
        return new UserInfoDbPortImpl(userInfoRepository, phoneRepository, userInfoEntityConverter, phoneEntityConverter, emailRegex);
    }
}
