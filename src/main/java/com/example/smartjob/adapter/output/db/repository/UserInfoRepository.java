package com.example.smartjob.adapter.output.db.repository;

import com.example.smartjob.adapter.output.db.entity.UserInfoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfoEntity, UUID> {
    List<UserInfoEntity> findByEmail(String email);

    List<UserInfoEntity> findByEmailAndPassword(String email, String password);
}
