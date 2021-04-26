package ru.topjava;

import ru.topjava.model.Dish;

import java.time.Month;

import static java.time.LocalDate.of;
import static ru.topjava.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {

    public static final TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Dish.class, "restaurant");

    public static final int DISH1_ID = START_SEQ + 8;

    public static final Dish DISH1 = new Dish(DISH1_ID, "Dish1", of(2020, Month.MARCH, 1), 1250);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, "Dish2", of(2020, Month.MARCH, 1), 1350);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, "Dish3", of(2020, Month.MARCH, 1), 20);
    public static final Dish DISH4 = new Dish(DISH1_ID + 3, "Dish4", of(2020, Month.MARCH, 1), 20);
    public static final Dish DISH5 = new Dish(DISH1_ID + 4, "Dish5", of(2020, Month.MARCH, 1), 20);
//    public static final List<Dish> DISHES = List.of(DISH5, DISH4, DISH3, DISH2, DISH1);
//    public static final List<Dish> DISHES_NOW = List.of(DISH5, DISH4);

    public static Dish getNew() {
        return new Dish(null, "New dish", of(2020, Month.MARCH, 30), 50);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "Updated dish", DISH1.getLocal_date(), 40);
    }
}
