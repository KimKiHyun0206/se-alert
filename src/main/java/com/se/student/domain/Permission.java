package com.se.student.domain;

import com.se.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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

    public Set<String> getPermissions() {
        Set<String> permissions = new HashSet<>();
        permissions.add("USER");
        if(notice) permissions.add("NOTICE");
        if(enroll) permissions.add("ENROLL");
        return permissions;
    }
}
