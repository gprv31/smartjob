package com.example.smartjob.adapter.input.web;

import com.example.smartjob.adapter.model.request.UserInformationRestRequest;
import com.example.smartjob.adapter.model.response.UserInformationRestResponse;
import com.example.smartjob.domain.port.input.PostUserInfoUseCase;
import com.example.smartjob.entity.UserInfo;
import com.example.smartjob.entity.response.UserInfoResponse;
import io.reactivex.rxjava3.core.Single;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "User Information POST", description = "User Information POST methods Controller")
public class UserInfoPostController {

    private final PostUserInfoUseCase postUserInfoUseCase;
    private final Function<UserInformationRestRequest, UserInfo> userInfoRequestConverter;
    private final Function<UserInfoResponse, UserInformationRestResponse> userInformationRestResponseConverter;


    @PostMapping(value = "/save", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Save user information", description = "Save user information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(schema = @Schema(implementation = UserInformationRestResponse.class))})
    })
    public Single<UserInformationRestResponse> saveUserInformation(
            @RequestBody UserInformationRestRequest userInformationRestRequest) {
        log.info("Starting UserInfoPostController.saveUserInformation method");
        return postUserInfoUseCase.saveUserInformation(userInfoRequestConverter.apply(userInformationRestRequest))
                .map(userInformationRestResponseConverter::apply)
                .doOnSuccess(i -> log.info("Finished UserInfoPostController.saveUserInformation method"))
                .doOnError(e -> log.error("Finished UserInfoPostController.saveUserInformation method with error", e));
    }
}
