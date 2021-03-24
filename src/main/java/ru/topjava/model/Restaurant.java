package ru.topjava.model;

import javax.persistence.*;
import java.util.Set;

@Entity
//@NamedEntityGraph(name = Restaurant.GRAPH_WITH_VOTES_DISHES, attributeNodes =
//        {
//                @NamedAttributeNode("votes"),
//                @NamedAttributeNode("dishes")
//        })
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    private String description;

    //@JsonManagedReference(value = "restaurant-votes")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<Vote> votes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
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
