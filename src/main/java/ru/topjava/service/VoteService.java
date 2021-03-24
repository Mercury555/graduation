package ru.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

import static ru.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {


    private VoteRepository voteRepository;

    private RestaurantRepository restaurantRepository;

    private UserRepository userRepository;

    public VoteService() {
    }

    public VoteService(VoteRepository voteRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public Vote save(int userId, int restaurantId) {
        Vote vote = new Vote(LocalDate.now());
        vote.setRestaurant(restaurantRepository.get(restaurantId));
        vote.setUser(userRepository.get(userId));
        return checkNotFoundWithId(voteRepository.save(vote), vote.getId());
    }


    public Vote update(Vote vote, int restaurantId) throws NotFoundException {
        Assert.notNull(vote, "vote must not be null");
        checkModificationAllowed();
        vote.setRestaurant(restaurantRepository.get(restaurantId));
        return checkNotFoundWithId(voteRepository.save(vote), vote.getId());
    }

    public void delete(int  id, int userId) {
       checkNotFoundWithId(voteRepository.delete(id, userId), id);
    }

    public Vote getByDate(LocalDate date, int userId) {
        Assert.notNull(date, "date must not be null");
       return voteRepository.getVoteToday(date, userId);
    }

    public List<Vote> getBetween(LocalDate startDate, LocalDate endDate, int userId) {
        return voteRepository.getVoteBetween(startDate, endDate, userId);
    }

    public List<Vote> getAllVoteByUser(int userId){
        return voteRepository.getAllVoteByUser(userId);
    }

    public void checkModificationAllowed() {
        if (LocalTime.now().isAfter(DateTimeUtil.getDeadlineVoteTime())) {
            throw new DataIntegrityViolationException("VOTE_MODIFICATION_RESTRICTION");
        }
    }
}
