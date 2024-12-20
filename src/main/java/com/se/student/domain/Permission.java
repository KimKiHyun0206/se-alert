package com.se.student.domain;

import com.se.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permission")
@NoArgsConstructor
public class Permission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String permissionName;
    private Boolean notice;
    private Boolean enroll;

    @Builder
    public Permission(String permissionName, Boolean notice, Boolean enroll) {
        this.permissionName = permissionName;
        this.notice = notice;
        this.enroll = enroll;
    }
}
