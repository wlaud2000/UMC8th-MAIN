package com.microservice.demo.umc8th_main.domain.user.entity;

import com.microservice.demo.umc8th_main.domain.inquiry.entity.Inquiry;
import com.microservice.demo.umc8th_main.domain.mission.entity.UserMission;
import com.microservice.demo.umc8th_main.domain.point.entity.PointHistory;
import com.microservice.demo.umc8th_main.domain.review.entity.Review;
import com.microservice.demo.umc8th_main.domain.review.entity.ReviewComment;
import com.microservice.demo.umc8th_main.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "gender", nullable = false, length = 10)
    private String gender;

    @Column(name = "current_point", nullable = false)
    private int currentPoint;

    @Column(name = "login_type", nullable = false, length = 20)
    private String loginType;

    @Column(name = "social_id")
    private String socialId;

    @Column(name = "is_adult", nullable = false)
    private boolean isAdult;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @Column(name = "role", nullable = false, length = 20)
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ReviewComment> reviewComments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserMission> userMissions = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Inquiry> inquiries = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PointHistory> pointHistories = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserTermsAgreement> termsAgreements = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private UserPreference userPreference;
}
