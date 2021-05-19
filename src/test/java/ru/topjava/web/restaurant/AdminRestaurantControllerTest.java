package ru.topjava.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.topjava.RestaurantTestData;
import ru.topjava.model.Restaurant;
import ru.topjava.service.RestaurantService;
import ru.topjava.util.exception.NotFoundException;
import ru.topjava.web.AbstractControllerTest;
import ru.topjava.web.json.JsonUtil;
import ru.topjava.web.user.AdminRestController;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.topjava.RestaurantTestData.*;
import static ru.topjava.TestUtil.readFromJson;

class AdminRestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestaurantController.REST_URL+ "/";

    @Autowired
    RestaurantService service;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RestaurantTestData.RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT1));
    }

    @Test
    void createWithLocation() throws Exception {
        Restaurant newRestaraunt = RestaurantTestData.getNew();
        ResultActions actions = perform(MockMvcRequestBuilders
                .post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaraunt)));
        Restaurant created = readFromJson(actions, Restaurant.class);
        int newId = created.getId();
        newRestaraunt.setId(newId);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RestaurantTestData.RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print());
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT1_ID));

    }

    @Test
    void update() throws Exception {
        Restaurant updated = RestaurantTestData.getUpdated();
        perform(MockMvcRequestBuilders
                .put(REST_URL  + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))).andExpect(status().isOk());
        RESTAURANT_MATCHER.assertMatch(service.get(RESTAURANT1_ID), updated);
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().is(200))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT1,RESTAURANT2, RESTAURANT3, RESTAURANT4));
    }

    @Test
    void findByName() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL+ "find?name=3"))
                .andExpect(status().is(200))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(asList(RESTAURANT3)));
    }

//    @Test
//    void getWithDishes() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL + "/withDishes/" + RESTAURANT2.getId()))
//                .andExpect(status().is(200))
//                .andDo(print())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT2));
//
//    }
}