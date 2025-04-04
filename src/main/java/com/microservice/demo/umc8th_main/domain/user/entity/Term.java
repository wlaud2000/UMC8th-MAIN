package com.microservice.demo.umc8th_main.domain.user.entity;

import com.microservice.demo.umc8th_main.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "terms")
public class Term extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "is_required", nullable = false)
    private boolean isRequired;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "version", nullable = false, length = 20)
    private String version;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "effective_date", nullable = false)
    private LocalDate effectiveDate;

    @OneToMany(mappedBy = "term")
    private List<UserTermsAgreement> userAgreements = new ArrayList<>();
}

