package com.example.demo.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USER_SUBSCRIPTIONS")
public class UserSubscriptionEntity {

    @EmbeddedId
    private UserSubscriptionId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @MapsId("subscriptionId")
    @JoinColumn(name = "SUBSCRIPTION_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private SubscriptionEntity subscription;

    @CreationTimestamp
    @Column(name = "SUBSCRIPTION_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date subscriptionDate;
}
