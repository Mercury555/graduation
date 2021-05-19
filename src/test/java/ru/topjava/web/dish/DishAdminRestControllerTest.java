package ru.topjava.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.topjava.DishTestData;
import ru.topjava.RestaurantTestData;
import ru.topjava.model.Dish;
import ru.topjava.service.DishService;
import ru.topjava.util.exception.NotFoundException;
import ru.topjava.web.AbstractControllerTest;
import ru.topjava.web.json.JsonUtil;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.topjava.DishTestData.*;
import static ru.topjava.RestaurantTestData.*;
import static ru.topjava.TestUtil.readFromJson;

class DishAdminRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishAdminRestController.REST_URL;

    @Autowired
    private DishService service;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/{restaurantId}/dishes/" + DishTestData.DISH1_ID, RESTAURANT1_ID)) ///rest/admin/restaurants/10004/dishes/10008
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(DISH1));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "/{restaurantId}/dishes/" + DishTestData.DISH1_ID, RESTAURANT1_ID)) ///rest/admin/restaurants/10004/dishes/10008
                .andExpect(status().isOk())
                .andDo(print());
        assertThrows(NotFoundException.class, () -> service.get(DISH1_ID, RESTAURANT1_ID));
    }

    @Test
    void findByRestaurant() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/{restaurantId}/dishes/", RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(DISH1, DISH4));
        ///rest/admin/restaurants/10004/dishes/

    }

    @Test
    void findByName() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/dishes?name=1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(Arrays.asList(DISH1)));
    }

    @Test
    void update() throws Exception {
        Dish updated = DishTestData.getUpdated();
        perform(MockMvcRequestBuilders
                .put(REST_URL + "/{restaurantId}/dishes/" + DISH1_ID, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))).andExpect(status().isOk());

        DISH_MATCHER.assertMatch(service.get(DISH1_ID, RESTAURANT1_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        Dish newDish = DishTestData.getNew();
        ResultActions actions = perform(MockMvcRequestBuilders
                .post(REST_URL + "/{restaurantId}/dishes/", RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)));
        Dish created = readFromJson(actions, Dish.class);
        int newId = created.getId();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(service.get(newId,RESTAURANT1_ID), newDish);
    }

//    @Test
//    void createInvalid() throws Exception{
//        Dish invalid = new Dish(null, null, null);
//        perform(post(REST_URL + "/{restaurantId}/dishes/", RESTAURANT1_ID)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.writeValue(invalid)))
//                .andExpect(status().isBadRequest())
//                .andDo(print());
//    }
}