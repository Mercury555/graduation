package ru.topjava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.topjava.model.Dish;
import ru.topjava.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static org.junit.Assert.assertThrows;
import static ru.topjava.DishTestData.*;
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
        Dish updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
        DISH_MATCHER.assertMatch(service.get(DISH1_ID, RESTAURANT1_ID), updated);
    }

    @Test
    public void get() {
        DISH_MATCHER.assertMatch(service.get(DISH1_ID, RESTAURANT1_ID), DISH1);
    }

    @Test
    public void delete() {
        service.delete(DISH1_ID, RESTAURANT1_ID);
        assertThrows(NotFoundException.class, () -> service.get(DISH1_ID, RESTAURANT1_ID));
    }

    @Test
    public void getAllByRestoran() {
        DISH_MATCHER.assertMatch((Arrays.asList(DISH1, DISH4)), service.getAllByRestoran(RESTAURANT1_ID));
        DISH_MATCHER.assertMatch((Arrays.asList(DISH2, DISH5)), service.getAllByRestoran(RESTAURANT1_ID+1));
    }

    @Test
    public void getByName() {
        DISH_MATCHER.assertMatch(Arrays.asList(DISH1), service.getByName("Dish1"));
//        DISH_MATCHER.assertMatch(Arrays.asList(DISH1), service.getByName("Dish"));
    }


    @Test
    public void testNotFoundDelete()  {
        assertThrows(NotFoundException.class,
                () -> service.delete(60000, RESTAURANT1_ID));

    }

    @Test
    public void testGetNotFound()  {
        assertThrows(NotFoundException.class,
                () -> service.get(100000, RESTAURANT1_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound()  {
        Dish updated = getUpdated();
        DISH_MATCHER.assertMatch(updated, service.get(DISH1_ID + 1, RESTAURANT1_ID));
    }

    @Test
    public void createWithException()  {
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Dish(null, "    ", LocalDate.now(), 10), RESTAURANT1_ID));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Dish(null, "CHECK", null, 300), RESTAURANT1_ID));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Dish(null, "CHECK", LocalDate.of(2020, Month.AUGUST, 2), null), RESTAURANT1_ID));
    }
}