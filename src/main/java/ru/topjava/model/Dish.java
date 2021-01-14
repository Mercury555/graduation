package ru.topjava.model;

import java.time.LocalDate;


public class Dish extends AbstractNamedEntity {

    private LocalDate local_date;

    private Integer price;


    private Integer restaurant_id;

    public Dish() {
    }

    public Dish(LocalDate date, String name, Integer price) {
        this(null, date, name, price);
    }

    public Dish(Integer id, String name, LocalDate local_date, Integer price, Integer restaurant_id) {
        super(id, name);
        this.local_date = local_date;
        this.price = price;
        this.restaurant_id = restaurant_id;
    }

    public Dish(Integer id, LocalDate localdate, String name, Integer price) {
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

    public Integer getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Integer restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public java.lang.Integer getPrice() {
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
                "localDate=" + local_date +
                ", price=" + price +
                ", restaurant=" + restaurant_id +
                ", name='" + name + '\'' +
                '}';
    }
}
