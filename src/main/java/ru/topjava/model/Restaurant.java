package ru.topjava.model;

import java.util.Set;


public class Restaurant extends AbstractNamedEntity {

    private String description;

    //@JsonManagedReference(value = "restaurant-votes")
    private Set<Vote> votes;


    private Set<Dish> dishes;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName(), restaurant.getDescription());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "description='" + description + '\'' +
                ", votes=" + votes +
                ", dishes=" + dishes +
                ", name='" + name + '\'' +
                '}';
    }
}
