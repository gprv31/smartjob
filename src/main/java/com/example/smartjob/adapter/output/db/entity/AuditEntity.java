package com.example.smartjob.adapter.output.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class AuditEntity {
  @Column(name = "active")
  private Boolean active;

  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "update_date")
  private LocalDateTime updateDate;

  @Column(name = "updated_by")
  private String updatedBy;

  @PrePersist
  public void prePersist() {
    creationDate = LocalDateTime.now();
    active = Boolean.TRUE;
  }
}
