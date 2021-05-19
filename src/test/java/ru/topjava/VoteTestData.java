package ru.topjava;

import ru.topjava.model.Vote;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDate.of;
import static ru.topjava.RestaurantTestData.*;
import static ru.topjava.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Vote.class, "user", "restaurant");

    public static final int VOTE1_ID = START_SEQ + 13;

    public static final Vote VOTE1 = new Vote(VOTE1_ID, of(2020, Month.MAY, 30), RESTAURANT1);
    public static final Vote VOTE2 = new Vote(VOTE1_ID + 1, of(2020, Month.MAY, 30), RESTAURANT1);
    public static final Vote VOTE3 = new Vote(VOTE1_ID + 2, of(2020, Month.MAY, 30), RESTAURANT3);
    public static final Vote VOTE4 = new Vote(VOTE1_ID + 3, LocalDate.now(), RESTAURANT1);
    public static final Vote VOTE5 = new Vote(VOTE1_ID + 4, of(2021, Month.MAY, 28), RESTAURANT2);
    public static final Vote VOTE6 = new Vote(VOTE1_ID + 5, of(2021, Month.MAY, 29), RESTAURANT2);

    public static final List<Vote> VOTES_BETWEEN = Arrays.asList(VOTE6, VOTE5, VOTE4);
    public static final List<Vote> VOTES_BETWEEN_ASC = Arrays.asList(VOTE4, VOTE5, VOTE6);
    public static final List<Vote> VOTES_BETWEEN_DATE = Arrays.asList(VOTE6, VOTE5);
    public static final List<Vote> VOTES_BY_USER1 = Arrays.asList(VOTE1);
    public static final List<Vote> VOTES = Arrays.asList(VOTE1, VOTE2, VOTE3, VOTE4, VOTE5, VOTE6);

    public static Vote getNew() {
        Vote vote = new Vote(LocalDate.now());
        vote.setUser(UserTestData.USER2);
        vote.setRestaurant(RESTAURANT3);
        return vote;
    }

    public static Vote getUpdated(){
        Vote vote = new Vote(LocalDate.now());
        vote.setUser(UserTestData.USER3);
        vote.setId(100016);
        vote.setRestaurant(RESTAURANT2);
        return vote;
    }

}
