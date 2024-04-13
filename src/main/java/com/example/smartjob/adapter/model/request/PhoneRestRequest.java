package com.example.smartjob.adapter.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhoneRestRequest {

    @Schema(description = "Phone number", example = "99999999", type = "string")
    private String number;

    @Schema(description = "Phone city code", example = "1", type = "string")
    private String citycode;

    @Schema(description = "Phone country code", example = "57", type = "string")
    private String countrycode;

}
