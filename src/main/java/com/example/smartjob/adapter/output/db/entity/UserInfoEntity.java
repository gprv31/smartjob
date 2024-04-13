package com.example.smartjob.adapter.output.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "userinfo")
public class UserInfoEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "user_uuid")
    @UuidGenerator
    private UUID userUuid;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "userInfoEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PhoneEntity> phoneEntityList;
}
