package ru.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.topjava.model.Vote;
import ru.topjava.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteRepository repository;

    public Vote create(Vote vote, int restaurantId, int userId) {
        return repository.save(vote, restaurantId, userId);
    }

    public void deleteByDate(LocalDate now, int userId) {
        repository.delete(now, userId);
    }

    public Vote getByDate(LocalDate now, int userId) {
       return repository.getVoteToday(now, userId);
    }

    public List<Vote> getBetween(LocalDate startDate, LocalDate endDate, int userId) {
        return repository.getAllVoteByUser(startDate, endDate, userId);
    }
}
