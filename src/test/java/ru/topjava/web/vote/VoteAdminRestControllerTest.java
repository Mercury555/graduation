package ru.topjava.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.topjava.TestMatcher;
import ru.topjava.VoteTestData;
import ru.topjava.service.VoteService;
import ru.topjava.util.VoteUtil;
import ru.topjava.util.exception.NotFoundException;
import ru.topjava.web.AbstractControllerTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.topjava.UserTestData.USER;
import static ru.topjava.UserTestData.USER_ID;
import static ru.topjava.VoteTestData.*;

class VoteAdminRestControllerTest extends AbstractControllerTest {

    @Autowired
    VoteService service;

    private final static String REST_URL = VoteAdminRestController.REST_URL;

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + VoteTestData.VOTE1_ID + "/users/100000"))
                .andExpect(status().is(200))
                .andDo(print());
        assertThrows(NotFoundException.class, () -> service.get(VoteTestData.VOTE1_ID, 100000));
    }

    @Test
    void getAllByUser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/users/100003" ))
                .andExpect(status().is(200))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(VoteUtil.get(Arrays.asList(VOTE4, VOTE6, VOTE5))));


    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().is(200))
                .andExpect(VOTE_MATCHER.contentJson(VOTES));
    }

//    @Test
//    void getWithUser() {
//    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + VOTE1_ID + "/users/" + USER_ID))
                .andExpect(status().is(200))
                .andDo(print())
                .andExpect(VOTE_TO_MATCHER.contentJson(VoteUtil.createTo(VOTE1)));
    }
}