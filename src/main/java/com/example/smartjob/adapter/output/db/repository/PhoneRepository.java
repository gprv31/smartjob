package com.example.smartjob.adapter.output.db.repository;

import com.example.smartjob.adapter.output.db.entity.PhoneEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhoneRepository extends CrudRepository<PhoneEntity, Long> {
}
