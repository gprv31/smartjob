package com.example.smartjob.adapter.model.response;

import com.example.smartjob.adapter.model.request.PhoneRestRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GetUserInformationRestResponse {
    @Schema(description = "User name", example = "Joe Smith", type = "string")
    private String name;

    @Schema(description = "User email", example = "mail@mail.com", type = "string")
    private String email;

    @Schema(description = "User phone list")
    private List<PhoneRestRequest> phoneRestRequestList;


}
