package com.example.demo.infrastructure.repository;

import com.example.demo.infrastructure.entity.SubscriptionEntity;
import com.example.demo.infrastructure.entity.UserFriendshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserFriendshipRepository extends JpaRepository<UserFriendshipEntity, Integer> {
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END " +
            "FROM UserFriendshipEntity f " +
            "WHERE (f.user.id = :userId AND f.friend.id = :friendId) " +
            "   OR (f.user.id = :friendId AND f.friend.id = :userId)")
    boolean existsFriendshipBidirectional(@Param("userId") Long userId, @Param("friendId") Long friendId);

}
