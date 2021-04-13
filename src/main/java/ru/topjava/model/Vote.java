package ru.topjava.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity{

    @JoinColumn(name = "local_date", nullable = false)
    @NotNull
    private LocalDate local_date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(LocalDate localDate, Restaurant restaurant) {
        this(null, localDate, restaurant);
    }

    public Vote(LocalDate localDate){
        this(null, localDate, null);
    }

    public Vote(Integer id, LocalDate date, Restaurant restaurant) {
        super(id);
        this.local_date = date;
        this.restaurant = restaurant;
    }

    public LocalDate getLocal_date() {
        return local_date;
    }

    public void setLocal_date(LocalDate local_date) {
        this.local_date = local_date;
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
                ", date=" + local_date +
                ", user=" + user +
//                ", restaurant=" + getRestaurant() +
                '}';
    }
}
