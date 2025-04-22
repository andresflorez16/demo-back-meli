//package com.example.demo.domain.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Objects;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class User {
//    private String id;
//    private String name;
//
//    private List<Subscription> subscriptions = new ArrayList<Subscription>();
//    private List<User> friends = new ArrayList<User>();
//
//    public List<User> getFriends() {
//        return Collections.unmodifiableList(friends);
//    }
//
//    public void addFriend(User user) {
//        if (user != null && !friends.contains(user)) {
//            friends.add(user);
//        }
//    }
//
//    public void addSubscription(Subscription trip) {
//        if (trip != null && !subscriptions.contains(trip)) {
//            subscriptions.add(trip);
//        }
//    }
//
//    public List<Subscription> getSubscriptions() {
//        return Collections.unmodifiableList(subscriptions);
//    }
//
//    public void removeSubscription(Subscription trip) {
//        if (trip != null) {
//            subscriptions.remove(trip);
//        }
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof User user)) return false;
//        return Objects.equals(id, user.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//}
