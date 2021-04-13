package ru.topjava.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


//@NamedQueries(value = {
//        @NamedQuery(name = Dish.ALL_SORTED, query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restId ORDER BY d.local_date DESC"),
//        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id AND d.id=:restId"),
//        @NamedQuery(name = Dish.GET_BY_NAME, query = "SELECT d FROM Dish d " +
//                "WHERE d.name=:name ORDER BY d.local_date DESC")
////        @NamedQuery(name = Meal.UPDATE, query = "UPDATE Meal m SET m.dateTime = :datetime, m.calories= :calories," +
////                "m.description=:desc where m.id=:id and m.user.id=:userId")
//})
@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "local_date", "name"}, name = "dishes_unique_restaurant_date_name_idx")})
public class Dish extends AbstractNamedEntity {

    public static final String DELETE = "Dish.delete";
    public static final String ALL_SORTED = "Dish.getAll";
    public static final String GET_BY_NAME = "Dish.getByName";

    @JoinColumn(name = "local_date", nullable = false)
    @NotNull
    private LocalDate local_date;

    @JoinColumn(name = "price", nullable = false)
    @NotNull
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(String name, LocalDate date,  Integer price) {
        this(null, name,  date,price);
    }

    public Dish(Integer id, String name, LocalDate local_date, Integer price, Restaurant restaurant) {
        super(id, name);
        this.local_date = local_date;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Dish(Integer id,String name,  LocalDate localdate, Integer price) {
        super(id, name);
        this.local_date = localdate;
        this.price = price;
    }

    public LocalDate getLocal_date() {
        return local_date;
    }

    public void setLocal_date(LocalDate local_date) {
        this.local_date = local_date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Dish setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return null;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(java.lang.Integer price) {
        this.price = price;
    }



//    @Override
//    public String toString() {
//        return "Dish{" +
//                "id=" + getId() +
//                ", date=" + localDate +
//                ", dish=" + name +
//                ", price=" + Integer.toString(price) +
//                ", restaurant=" + restaurant +
//                '}';
//    }


    @Override
    public String toString() {
        return "Dish{" +
                "local_date=" + local_date +
                ", price=" + Integer.toString(price) +
//                ", restaurant=" + restaurant +
                ", name='" + name + '\'' +
                '}';
    }
}
