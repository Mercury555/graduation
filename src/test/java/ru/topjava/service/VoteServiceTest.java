package ru.topjava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.topjava.model.Vote;
import ru.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.topjava.RestaurantTestData.RESTAURANT1_ID;
import static ru.topjava.UserTestData.USER_ID;
import static ru.topjava.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void create() {
        Vote newVote = getNew(); // user2, RESTAURANT1, LocalDate.now()
        Vote created = service.save(USER_ID + 2, RESTAURANT1_ID);
        newVote.setId(created.getId());
        VOTE_MATCHER.assertMatch(created, newVote);
    }

    @Test
    public void update() {
        Vote vote = getUpdated(); //id =100016, LocalDate.now(), RESTAURANT2
        service.update(vote, USER_ID);
        Vote actaul = service.get(100016, USER_ID);
        VOTE_MATCHER.assertMatch(actaul, vote);

    }

    @Test
    public void delete() {
        service.delete(VOTE1_ID + 1, USER_ID + 1);
        assertThrows(NotFoundException.class, () -> service.get(VOTE1_ID + 1, USER_ID + 1));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(300000, USER_ID + 1));
    }

    @Test
    public void deleteNotOwn() {
        assertThrows(NotFoundException.class, () -> service.delete(VOTE1_ID, USER_ID + 2));
    }

    @Test
    public void getNotOwn() {
        assertThrows(NotFoundException.class, () -> service.get(VOTE1_ID, USER_ID + 2));
    }

    @Test
    public void getByDate() {
        Vote actual = service.getByDate(LocalDate.of(2020,Month.MAY, 30), USER_ID+1);
        VOTE_MATCHER.assertMatch(actual, VOTE2);
    }

    @Test
    public void getBetween() {
        List<Vote> actual = service.getBetween(LocalDate.of(2021, Month.MAY, 28), LocalDate.of(2021, Month.MAY, 29), USER_ID + 3);
        VOTE_MATCHER.assertMatch(actual, VOTES_BETWEEN);
    }

    @Test
    public void getBetweenWithNullDates() {
        List<Vote> actual = service.getBetween(null, null, USER_ID + 3);
        VOTE_MATCHER.assertMatch(actual, VOTES_BETWEEN);
    }

    @Test
    public void getAllVoteByUser() {
       List<Vote> actual =  service.getAllVoteByUser(USER_ID);
        VOTE_MATCHER.assertMatch(actual, VOTES_BY_USER1);
    }
}