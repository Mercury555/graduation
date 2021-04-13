package ru.topjava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.topjava.model.Restaurant;
import ru.topjava.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertThrows;
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
}