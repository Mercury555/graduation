package ru.topjava.model.to;

import java.time.LocalDate;
import java.util.Objects;

public class VoteTo {

    private final Integer id;

    private final LocalDate date;

    private final String userEmail;

    private final Integer user_id;

    private final String restaurant_name;

    private final Integer restaurant_id;


    public VoteTo(Integer id, LocalDate date, String userEmail, Integer user_id, String restaurant_name, Integer restaurant_id) {
        this.id = id;
        this.date = date;
        this.userEmail = userEmail;
        this.user_id = user_id;
        this.restaurant_name = restaurant_name;
        this.restaurant_id = restaurant_id;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public Integer getRestaurant_id() {
        return restaurant_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteTo)) return false;
        VoteTo voteTo = (VoteTo) o;
        return Objects.equals(getId(), voteTo.getId()) &&
                Objects.equals(getDate(), voteTo.getDate()) &&
                Objects.equals(getUserEmail(), voteTo.getUserEmail()) &&
                Objects.equals(getUser_id(), voteTo.getUser_id()) &&
                Objects.equals(getRestaurant_name(), voteTo.getRestaurant_name()) &&
                Objects.equals(getRestaurant_id(), voteTo.getRestaurant_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getUserEmail(), getUser_id(), getRestaurant_name(), getRestaurant_id());
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                "id=" + id +
                ", date=" + date +
                ", userEmail='" + userEmail + '\'' +
                ", user_id=" + user_id +
                ", restaurant_name='" + restaurant_name + '\'' +
                ", restaurant_id=" + restaurant_id +
                '}';
    }
}
