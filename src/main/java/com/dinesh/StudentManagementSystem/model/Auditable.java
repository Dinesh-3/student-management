package com.dinesh.StudentManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {

    @CreatedDate
    @Column(updatable = false)
    @JsonFormat(
            shape = JsonFormat.Shape.STRING
//            pattern = "dd-MM-yyyy hh:mm:ss"
    )
    private LocalDateTime created_at;

    @LastModifiedDate
    @JsonFormat(
            shape = JsonFormat.Shape.STRING
//            pattern = "dd-MM-yyyy hh:mm:ss"
    )
    private LocalDateTime updated_at;

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
