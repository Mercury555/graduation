package ru.topjava.repository;

import org.springframework.stereotype.Repository;
import ru.topjava.model.Vote;

import java.time.LocalDate;
import java.util.List;
@Repository
public class VoteRepImpl implements VoteRepository {
    @Override
    public Vote save(Vote vote, int restaurantId, int userId) {
        return null;
    }

    @Override
    public Vote findOne(Integer id) {
        return null;
    }

    @Override
    public Vote getVoteToday(LocalDate date, int id) {
        return null;
    }

    @Override
    public List<Vote> getAllVoteByUser(LocalDate startDate, LocalDate endDate, int userid) {
        return null;
    }

    @Override
    public void delete(LocalDate now, int userId) {

    }
}
