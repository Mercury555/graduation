package ru.topjava;


import ru.topjava.model.Restaurant;

import java.util.List;

import static ru.topjava.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {

    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingIgnoringFieldsComparator("");

    public static final int RESTAURANT1_ID = START_SEQ + 4;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "restaurant1", "sea_food");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Restaurant2", "sea_food");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "Restaurant3", "sea_food");
    public static final Restaurant RESTAURANT4 = new Restaurant(RESTAURANT1_ID + 3, "Restaurant4","italiano");
    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT1);

    public static Restaurant getNew() {
        return new Restaurant(null, "New restaurant", "new_italiano");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Updated restaurant", "updated_italiano");
    }

}