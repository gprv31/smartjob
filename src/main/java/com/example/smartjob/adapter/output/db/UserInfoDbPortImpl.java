package com.example.smartjob.adapter.output.db;

import com.example.smartjob.adapter.output.db.entity.PhoneEntity;
import com.example.smartjob.adapter.output.db.entity.UserInfoEntity;
import com.example.smartjob.adapter.output.db.repository.PhoneRepository;
import com.example.smartjob.adapter.output.db.repository.UserInfoRepository;
import com.example.smartjob.adapter.output.db.util.EncryptionUtil;
import com.example.smartjob.application.exception.UserAppException;
import com.example.smartjob.domain.port.output.UserInfoDbPort;
import com.example.smartjob.entity.Phone;
import com.example.smartjob.entity.UserInfo;
import com.example.smartjob.entity.response.UserInfoResponse;
import io.reactivex.rxjava3.core.Single;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class UserInfoDbPortImpl implements UserInfoDbPort {

    private final UserInfoRepository userInfoRepository;
    private final PhoneRepository phoneRepository;
    private final Function<UserInfo, UserInfoEntity> userInfoEntityConverter;
    private final Function<Phone, PhoneEntity> phoneEntityConverter;
    private String emailRegex;

    @Override
    public Single<UserInfoResponse> saveUserInformation(UserInfo userInfo) {
        log.info("Starting UserInfoDbPortImpl.saveUserInformation method");

        return Single.fromCallable(() -> {
            validateFields(userInfo);
            UserInfoEntity userInfoEntity = userInfoRepository.save(userInfoEntityConverter.apply(userInfo));
            List<PhoneEntity> phoneEntityList = userInfo.getPhoneList().stream()
                    .map(phone -> {
                        PhoneEntity phoneEntity = phoneEntityConverter.apply(phone);
                        phoneEntity.setUserInfoEntity(userInfoEntity);
                        return phoneEntity;
                    }).collect(Collectors.toList());
            phoneRepository.saveAll(phoneEntityList);

            return UserInfoResponse.builder()
                    .id(userInfoEntity.getUserUuid().toString())
                    .created(userInfoEntity.getCreationDate())
                    .modified(userInfoEntity.getUpdateDate())
                    .lastLogin(userInfoEntity.getCreationDate())//TODO: method?
                    .active(userInfoEntity.getActive())
                    .build();
        }).doOnSuccess(i -> log.info("Finished UserInfoDbPortImpl.saveUserInformation method"))
        .doOnError(e -> log.error("Finished UserInfoDbPortImpl.saveUserInformation method with error", e));
    }

    @Override
    public Single<UserInfo> getUserInformationByEmail(String email) {
        log.info("Starting UserInfoDbPortImpl.getUserInformationByEmail method");
        return Single.fromCallable(() -> {
            UserInfoEntity userInfoEntity = userInfoRepository.findByEmail(email).get(0);
            return UserInfo.builder()
                    .name(userInfoEntity.getName())
                    .email(userInfoEntity.getEmail())
                    .build();
        });
    }

    @Override
    public Single<UserInfo> getUserInformationByEmailAndPassword(String email, String password) {
        log.info("Starting UserInfoDbPortImpl.getUserInformationByEmailAndPassword method");
        return Single.fromCallable(() -> {
            List<UserInfoEntity> userInfoEntityList = userInfoRepository.findByEmailAndPassword(email,
                    EncryptionUtil.encrypt(password));
            if (CollectionUtils.isEmpty(userInfoEntityList)) {
                throw new UserAppException(new Error("Email not found"));
            }
            return UserInfo.builder()
                    .name(userInfoEntityList.get(0).getName())
                    .email(userInfoEntityList.get(0).getEmail())
                    .build();
        });
    }

    private void validateFields(UserInfo userInfo) throws UserAppException {
        if (StringUtils.isEmpty(userInfo.getName())) {
            throw new UserAppException(new Error("Name is empty"));
        }
        if (StringUtils.isEmpty(userInfo.getEmail())) {
            throw new UserAppException(new Error("Email is empty"));
        }
        if (StringUtils.isEmpty(userInfo.getPassword())) {
            throw new UserAppException(new Error("Password is empty"));
        }
        //String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
        //        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(emailRegex);
        Matcher m = p.matcher(userInfo.getEmail().trim());
        if (!m.matches()) {
            throw new UserAppException(new Error("Email format is not valid"));
        }
    }
}
