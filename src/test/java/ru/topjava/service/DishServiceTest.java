package ru.topjava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.topjava.DishTestData;
import ru.topjava.model.Dish;

import static org.junit.Assert.*;
import static ru.topjava.DishTestData.DISH_MATCHER;
import static ru.topjava.DishTestData.getNew;
import static ru.topjava.RestaurantTestData.RESTAURANT1_ID;

public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    private DishService service;

    @Test
    public void create() {
        Dish created = service.create(getNew(), RESTAURANT1_ID);
        int newId = created.getId();
        Dish newDish = getNew();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(service.get(newId, RESTAURANT1_ID), newDish);
    }

    @Test
    public void update() {
    }

    @Test
    public void get() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getAllByRestoran() {
    }

    @Test
    public void getByName() {
    }
}