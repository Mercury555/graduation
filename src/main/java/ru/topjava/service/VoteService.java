package ru.topjava.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.topjava.model.Vote;
import ru.topjava.repository.RestaurantRepository;
import ru.topjava.repository.UserRepository;
import ru.topjava.repository.VoteRepository;
import ru.topjava.util.DateTimeUtil;
import ru.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.topjava.util.DateTimeUtil.atStartOfDayOrMin;
import static ru.topjava.util.DateTimeUtil.atStartOfNextDayOrMax;
import static ru.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    private final RestaurantRepository restaurantRepository;

    private final UserRepository userRepository;

    public VoteService(VoteRepository voteRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public Vote save(int userId, int restaurantId) {
        Vote vote = new Vote(LocalDate.now(), restaurantRepository.get(restaurantId));
        vote.setRestaurant(restaurantRepository.get(restaurantId));
        vote.setUser(userRepository.get(userId));
        return checkNotFoundWithId(voteRepository.save(vote, userId), vote.getId());
    }


    public Vote update(Vote vote, int userId) throws NotFoundException {
        Assert.notNull(vote, "vote must not be null");
        checkModificationAllowed(vote);
        return checkNotFoundWithId(voteRepository.save(vote, userId), vote.getId());
    }

    public void delete(int  id, int userId) {
       checkNotFoundWithId(voteRepository.delete(id, userId), id);
    }

    public Vote get(int  id, int userId) {
       return checkNotFoundWithId(voteRepository.get(id, userId), id);
    }

    public Vote getByDate(LocalDate date, int userId) {
        Assert.notNull(date, "date must not be null");
       return voteRepository.getVoteToday(date, userId);
    }

    public List<Vote> getBetween(@Nullable LocalDate startDate, @Nullable  LocalDate endDate, int userId) {
        return voteRepository.getVoteBetween(atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate), userId);
    }

    public List<Vote> getAllVoteByUser(int userId){
        return voteRepository.getAllVoteByUser(userId);
    }

    public void checkModificationAllowed(Vote vote) {
        LocalDate date = vote.getLocal_date();
        if (!date.isEqual(LocalDate.now()) || LocalTime.now().isAfter(DateTimeUtil.getDeadlineVoteTime())){
            throw new DataIntegrityViolationException("VOTE_MODIFICATION_RESTRICTION");
        }
    }

    public Vote getWithRestaurant(int id, int userId) {
        return voteRepository.get(id, userId);
    }
}
