package ru.topjava.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.topjava.model.Vote;
import ru.topjava.model.to.VoteTo;
import ru.topjava.service.VoteService;
import ru.topjava.util.VoteUtil;
import ru.topjava.web.SecurityUtil;

import java.util.List;

import static ru.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.topjava.util.ValidationUtil.checkNew;
import static ru.topjava.web.SecurityUtil.*;

public abstract class AbstractVoteController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public List<VoteTo> getAllByUser(int userId) {
        log.info("getAll for User for admin {}", userId);
        return VoteUtil.get(service.getAllVoteByUser(userId));
    }

    public List<VoteTo> getAllByUser() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for User {}", userId);
        return VoteUtil.get(service.getAllVoteByUser(userId));
    }

    public Vote getWithUser(int id, int userId) {
        log.info("getAllWithUser for User {}", userId);
        return service.getWithUser(id, userId);
    }

    public VoteTo get(int id, int userId) {
        log.info("get vote {} for User {}", id, userId);
        return VoteUtil.createTo(service.get(id, userId));
    }

    public void delete(int id, int userId) {
        log.info("delete vote {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public Vote create(Vote vote) {
        int userId = authUserId();
        log.info("create vote for User {} and Vote {}", userId, vote);
        checkNew(vote);
        return service.create(vote, userId);
    }

    public void update(Vote vote, int id) {
        int userId = authUserId();
        log.info("update vote for User {} and Restaurant {}", authUserId());
        assureIdConsistent(vote, id);
        service.update(vote, userId);
    }

    public List<Vote> getAll() {
        log.info("get All {}");
        return service.getAll();
    }

    public VoteTo get(int id) {
        int userId = authUserId();
        log.info("get vote {} for User {}", id, userId);
        return VoteUtil.createTo(service.get(id, userId));
    }
}
