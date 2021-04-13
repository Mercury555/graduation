package ru.topjava.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.topjava.model.Vote;
import ru.topjava.service.RestaurantService;
import ru.topjava.service.VoteService;
import ru.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.util.List;

public class UserVoteController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private VoteService service;
    private RestaurantService restaurantService;

    public void createOrUpdate(int restaurantId) {
        int userId = SecurityUtil.authUserId();
        Vote vote = new Vote(LocalDate.now(), restaurantService.get(restaurantId));
        log.info("create {} for restaurant {} by user {}", vote, restaurantId, userId);
        service.save(restaurantId, userId);
    }


    public void deleteByNow() {
        int userId = SecurityUtil.authUserId();
        log.info("delete vote for date {} by user {}", LocalDate.now(), userId);
//        service.deleteByDate(LocalDate.now(), userId);
    }


    public Vote getByNow() {
        int userId = SecurityUtil.authUserId();
        log.info("get vote for date {} by user {}", LocalDate.now(), userId);
        return service.getByDate(LocalDate.now(), userId);
    }


    public List<Vote> getBetween(LocalDate startDate, LocalDate endDate) {
        int userId = SecurityUtil.authUserId();
        log.info("get votes between dates({} - {}) for user {}", startDate, endDate, userId);
        return service.getBetween(startDate, endDate, userId);
    }


}
