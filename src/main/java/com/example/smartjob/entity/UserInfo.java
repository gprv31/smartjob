package com.example.smartjob.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserInfo {
    private String name;
    private String email;
    private String password;
    private List<Phone> phoneList;
}
