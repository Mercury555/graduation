package ru.topjava.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.topjava.DishTestData;
import ru.topjava.model.Restaurant;
import ru.topjava.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.topjava.DishTestData.DISH1;
import static ru.topjava.DishTestData.DISH4;
import static ru.topjava.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    RestaurantService service;

    @Test
    public void create() {
        Restaurant created = service.create(getNew());
        int newId = created.getId();
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(service.get(newId), newRestaurant);
    }

    @Test
    public void get() {
        RESTAURANT_MATCHER.assertMatch(service.get(RESTAURANT1_ID), RESTAURANT1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(10));
    }

    @Test
    public void delete() {
        service.delete(100004);
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT1_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(10));
    }

    @Test
    public void update() {
        Restaurant updated = getUpdated();
        service.update(updated);
        RESTAURANT_MATCHER.assertMatch(service.get(RESTAURANT1_ID), updated);
    }
    @Test
    public void createWithException()  {
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Restaurant(null, "Pizza", "  ")));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Restaurant(null, "  ", "Italy")));
    }

    @Test
    public void getAll() {
        RESTAURANT_MATCHER.assertMatch(service.getAll(), RESTAURANTS);
    }

    @Test
    public void getByName() {
       RESTAURANT_MATCHER.assertMatch(service.getByName("Restaurant 3"), RESTAURANT3);
    }

    @Test
    public void getWithDishes(){
        Restaurant restaurant = service.getWithDishes(RESTAURANT1_ID);
        RESTAURANT_MATCHER.assertMatch(restaurant, RESTAURANT1);
        DishTestData.DISH_MATCHER.assertMatch(restaurant.getDishes(), DISH1, DISH4);
    }

    @Test
    public void getWithDishesNotFound(){
        Assertions.assertThrows(NotFoundException.class,
                () -> service.getWithDishes(1));
    }
}