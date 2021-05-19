package ru.topjava.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.topjava.model.Vote;
import ru.topjava.service.VoteService;
import ru.topjava.web.AbstractControllerTest;
import ru.topjava.web.json.JsonUtil;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.topjava.RestaurantTestData.RESTAURANT1_ID;
import static ru.topjava.TestUtil.readFromJson;
import static ru.topjava.UserTestData.USER2;
import static ru.topjava.UserTestData.USER3;
import static ru.topjava.VoteTestData.*;

class VoteProfileRestControllerTest extends AbstractControllerTest {

    private final static String REST_URL = VoteProfileRestController.REST_URL;

    @Autowired
    VoteService service;

    @Test
    void createWithLocation() throws Exception {
        Vote newVote = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)));

        Vote created = readFromJson(action, Vote.class);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(service.get(newId, USER2.getId()), newVote);
    }

    @Test
    void update() throws Exception {
        Vote updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL+ "/" + 100016)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
        VOTE_MATCHER.assertMatch(service.get(100016, USER3.getId()), updated);

    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + VOTE1.getId()))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(VOTE_MATCHER.contentJson(VOTE1));
    }

    @Test
    void getAllByUser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(VOTE_MATCHER.contentJson(Arrays.asList(VOTE1)));
    }
}