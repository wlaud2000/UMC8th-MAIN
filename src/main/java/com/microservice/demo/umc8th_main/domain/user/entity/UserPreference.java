package com.microservice.demo.umc8th_main.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "user_preferences")
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "preference_food_categories")
    private String preferenceFoodCategories;

    @Column(name = "push_notification", nullable = false)
    private boolean pushNotification;

    @Column(name = "email_notification", nullable = false)
    private boolean emailNotification;

    @Column(name = "new_event_alert", nullable = false)
    private boolean newEventAlert;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
