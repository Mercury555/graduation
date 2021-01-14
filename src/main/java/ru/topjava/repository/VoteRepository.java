package ru.topjava.repository;

import org.springframework.stereotype.Repository;
import ru.topjava.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VoteRepository {
    // null if not found, when updated
    Vote save(Vote vote, int restaurantId, int userId);

    Vote findOne(Integer id);

    // null if not found
    Vote getVoteToday(LocalDate date, int id);

    // null if not found
    List<Vote> getAllVoteByUser(LocalDate startDate, LocalDate endDate, int userid);

    void delete(LocalDate now, int userId);

}
