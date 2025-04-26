package com.microservice.demo.umc8th_main.domain.restaurant.entity;

import com.microservice.demo.umc8th_main.domain.mission.entity.Mission;
import com.microservice.demo.umc8th_main.domain.review.entity.Review;
import com.microservice.demo.umc8th_main.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "restaurants")
public class Restaurant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "latitude", nullable = false)
    private float latitude;

    @Column(name = "longitude", nullable = false)
    private float longitude;

    @Column(name = "rating")
    private float rating;

    @Column(name = "review_count")
    private int reviewCount;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "business_hours")
    private String businessHours;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    // 리뷰는 시간순, 평점순 등으로 정렬이 필요할 수 있음
    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviews = new ArrayList<>();

    // 메뉴는 순서가 중요함 (메뉴판 표시 순서)
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menus = new ArrayList<>();

    // 미션은 시작일 등으로 정렬이 필요할 수 있음
    @OneToMany(mappedBy = "restaurant")
    private List<Mission> missions = new ArrayList<>();
}
