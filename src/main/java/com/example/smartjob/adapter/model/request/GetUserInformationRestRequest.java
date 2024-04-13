package com.example.smartjob.adapter.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetUserInformationRestRequest {
    @Schema(description = "User name", example = "Joe Smith", type = "string")
    private String name;

    @Schema(description = "User email", example = "mail@mail.com", type = "string")
    private String email;

}
