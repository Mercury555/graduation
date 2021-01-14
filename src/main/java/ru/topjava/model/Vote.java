package ru.topjava.model;

import java.time.LocalDate;

public class Vote extends AbstractBaseEntity{

    private LocalDate localDate;


    private User user;


    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(LocalDate localDate) {
        this(null, localDate);
    }

    public Vote(java.lang.Integer id, LocalDate date) {
        super(id);
        this.localDate = date;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = this.localDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + getId() +
                ", date=" + localDate +
                //", user=" + user +
                //", restaurant=" + getRestaurant() +
                '}';
    }
}
