package com.example.smartjob.adapter.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserInformationRestResponse {

    @Schema(description = "User id", example = "4ee87be2-6ddc-47e5-b238-637fc614693a", type = "string")
    private String id;

    @Schema(description = "User info created date", example = "-", type = "date")
    private LocalDateTime created;

    @Schema(description = "User info modified date ", example = "-", type = "date")
    private LocalDateTime modified;

    @Schema(description = "User info last login date", example = "-", type = "date")
    private LocalDateTime lastLogin;

    @Schema(description = "User generated token", example = "-", type = "string")
    private String token;

    @Schema(description = "User is active", example = "true", type = "string")
    private boolean active;
}
