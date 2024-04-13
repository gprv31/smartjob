package com.example.smartjob.adapter.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserInformationRestRequest {
    @Schema(description = "User name", example = "Joe Smith", type = "string")
    private String name;

    @Schema(description = "User email", example = "mail@mail.com", type = "string")
    private String email;

    @Schema(description = "password", example = "mypassword123", type = "string")
    private String password;

    @Schema(description = "User phone list")
    private List<PhoneRestRequest> phoneRestRequestList;


}
