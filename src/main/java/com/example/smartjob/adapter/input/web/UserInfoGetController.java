package com.example.smartjob.adapter.input.web;

import com.example.smartjob.adapter.model.response.GetUserInformationRestResponse;
import com.example.smartjob.domain.port.input.GetUserInfoUseCase;
import com.example.smartjob.entity.UserInfo;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "User information GET", description = "User information GET methods Controller")
public class UserInfoGetController {

    private final GetUserInfoUseCase getUserInfoUseCase;
    private final Function<UserInfo, GetUserInformationRestResponse> getUserInformationRestResponseConverter;

    @GetMapping(value = "/info", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Get User information", description = "Get User information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(schema = @Schema(implementation = GetUserInformationRestResponse.class))})
    })
    public Single<GetUserInformationRestResponse> getUserInformation(
            @RequestParam String email,
            @RequestParam String password) {
        log.info("Starting UserInfoGetController.getUserInformation method");
        return getUserInfoUseCase.getUserInfo(email, password)
                .map(getUserInformationRestResponseConverter::apply)
                .doOnSuccess(i -> log.info("Finished UserInfoGetController.getUserInformation method"))
                .doOnError(e -> log.error("Finished UserInfoGetController.getUserInformation method with error", e));
    }
}
