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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    // 리뷰는 작성 순서, 시간순 등으로 정렬이 필요할 수 있음
    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    // 댓글도 시간순 정렬이 중요
    @OneToMany(mappedBy = "user")
    private List<ReviewComment> reviewComments = new ArrayList<>();

    // 미션 참여는 참여 여부만 중요하고 중복 방지가 필요
    @OneToMany(mappedBy = "user")
    private Set<UserMission> userMissions = new HashSet<>();

    // 문의는 시간순으로 정렬이 필요
    @OneToMany(mappedBy = "user")
    private List<Inquiry> inquiries = new ArrayList<>();

    // 포인트 내역은 시간순 정렬이 중요
    @OneToMany(mappedBy = "user")
    private List<PointHistory> pointHistories = new ArrayList<>();

    // 약관 동의는 중복 없이 약관 ID로 빠르게 조회 필요
    @OneToMany(mappedBy = "user")
    private Set<UserTermsAgreement> termsAgreements = new HashSet<>();

    // 사용자당 설정은 하나만 존재해야 함
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserPreference userPreference;
}
